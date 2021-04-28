[![](https://jitpack.io/v/Amondo/AmondoSDK-Android.svg)](https://jitpack.io/#Amondo/AmondoSDK-Android)

# AmondoSDK
The AmondoSDK provides access to the Amondo Imprint database, and allows all of the Imprint functionality to be used within your app, with one single ```openImprint``` method.

## Setup

The first step is to make sure you have the [JitPack](https://jitpack.io) repositories included in the `build.gradle` file in the root of your project.

```Groovy
allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
}
```

Next add a dependency in the `build.gradle` file of your app module. The following will add a dependency to the full AmondoSDK library:

```Groovy
implementation 'com.github.Amondo:AmondoSDK-Android:1.4.2'
```

The last step is to enable Java 1.8 compiling if it isn't already enabled for your app. Add the following in the `build.gradle` file of your app module:

```Groovy
compileOptions {
        sourceCompatibility 1.8
        targetCompatibility 1.8
}
```

## Usage

### Connecting to the SDK

To connect to the AmondoSDK, you need to call the `init` method, which (among other things) gets an access token from our servers, to authenticate your future requests. The initial SDK initialisation is done _asynchronously_.

```java
AmondoSDK.init(context, <YOUR_APP_ID>, <YOUR_SECRET_KEY>);
```


If you ever need to disconnect from the SDK, you can use:

```java
AmondoSDK.deinitialise()
```

### Loading Imprints

To connect to the Amondo database, and load Imprints associated with your account, use:

```Java

AmondoSDK.getImprints(context, int count, int skip, new GetImprintsCallback() {

        @Override
        public void onImprintsLoaded(List<Imprint> imprints) {

        }

        @Override
        public void onError(@AmondoConstants.ErrorCodes int errorCode) {

        }

});
```

`count` refers to how many Imprints do you want to load.

`skip` as it's name says, it is used for skipping initial amount of imprints.

#### Loading Imprints by ids

Another way to load Imprints is by inputting the relevant IDs:
```Java

AmondoSDK.getImprintsById(context, int[] ids, new GetImprintsCallback() {

        @Override
        public void onImprintsLoaded(List<Imprint> imprints) {

        }

        @Override
        public void onError(@AmondoConstants.ErrorCodes int errorCode) {

        }

});
```

### Opening an Imprint

An Imprint is opened in a stand alone activity.

```java
AmondoSDK.openImprint(context, imprint);
```
Once an Imprint has been opened, all functionality is contained within that Activity, and it's children activities. When a user closes the Imprint, activity is dismissed and removed from the activity stack.

## Customization


To do any customization of AmondoSDK, it has to be initialized before you change a theme.


### Using light theme

To use the light Amondo theme, call:

```java
AmondoSDK.useLightTheme(true);
```
You can switch between themes any time during the application's lifecycle.


### Load Grid only

To use the Amondo SDK just as grid (without cover), call:

```java
AmondoSDK.setGridOnly(true);
```


### Custom theme

You can do additional customization by extending `AmondoSdkTheme.Dark` 
or `AmondoSdkTheme.Light` in *style.xml*. There you can set these values:

```xml
<style name="AmondoSdkTheme.Dark.Custom" parent="AmondoSdkTheme.Dark">
    <item name="amdTileBackgroundColor">color</item>
    <item name="amdHeaderTitleFont">font</item>
    <item name="amdHeaderTitleFontSize">dimen</item>
    <item name="amdHeaderTitleFontLarge">font</item>
    <item name="amdHeaderTitleFontLargeSize">dimen</item>
    <item name="amdHeaderInfoFont">font</item>
    <item name="amdHeaderInfoSize">dimen</item>
    <item name="amdTileUsernameFont">font</item>
    <item name="amdTileUsernameSize">dimen</item>
    <item name="amdTileInfoFont">font</item>
    <item name="amdTileInfoSize">dimen</item>
    <item name="amdTileDescriptionFont">font</item>
    <item name="amdTileDescriptionSize">dimen</item>
    <item name="amdButtonActionFont">font</item>
    <item name="amdButtonActionSize">dimen</item>
</style>
```

After, in your code before you call `AmondoSDK.openImprint()`, call:

```java
AmondoSDK.setCustomTheme(R.style.AmondoSdkTheme_Dark_Custom);
```


## Requirements

Min supported Android version is 4.4.