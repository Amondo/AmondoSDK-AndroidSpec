package com.amondo.amondosdksample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amondo.sdk.AmondoSDK;
import com.amondo.sdk.entities.Imprint;
import com.amondo.sdk.helpers.GetImprintsCallback;
import com.amondo.sdk.preferences.PrefsManager;
import com.bumptech.glide.Glide;

import java.util.List;

public class SampleActivity extends AppCompatActivity {

    private List<Imprint> imprints;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        RecyclerView recyclerView = findViewById(R.id.as_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ImprintsAdapter();
        recyclerView.setAdapter(adapter);
        AmondoSDK.getAllImprints(this, new GetImprintsCallback() {
            @Override
            public void onImrintsLoaded(List<Imprint> imprints) {
                SampleActivity.this.imprints = imprints;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int errorCode) {
                Toast.makeText(SampleActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ImprintsAdapter extends RecyclerView.Adapter<ImprintViewHolder> {

        @NonNull
        @Override
        public ImprintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(SampleActivity.this).inflate(R.layout.item_imprint, parent, false);
            return new ImprintViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ImprintViewHolder holder, int position) {
            Imprint imprint = imprints.get(position);
            holder.tvTitle.setText(imprint.getArtist());
            Glide.with(SampleActivity.this).load(imprint.getCoverUrl()).into(holder.ivImage);
            holder.itemView.setTag(imprint);
        }

        @Override
        public int getItemCount() {
            return imprints != null ? imprints.size() : 0;
        }
    }

    private class ImprintViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivImage;
        private TextView tvTitle;


        public ImprintViewHolder(View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ii_image);
            tvTitle = itemView.findViewById(R.id.ii_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Imprint imprint = (Imprint) itemView.getTag();
            AmondoSDK.openImprint(SampleActivity.this, imprint);
        }
    }
}
