# AmondoSDK
The AmondoSDK provides access to the Amondo Imprint database, and allows all of the Imprint functionality to be used within your app, with one single ```openImprint``` method.

## Setup

The first step to getting started is to make sure you have the [JitPack](https://jitpack.io) repositories included in the `build.gradle` file in the root of your project.

```Groovy
repositories {
        jcenter()
        maven { url "https://jitpack.io" }
}
```

Next add a dependency in the `build.gradle` file of your app module. The following will add a dependency to the full AmondoSDK library:

```Groovy
implementation 'com.github.Amondo.AmondoSdk-Android:1.0.0-beta'
```

The last step is to enable Java 1.8 compling if it isn't already enabled for your app. Add the followin in the `build.gradle` file of your app module:

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
AmondoSDK.init(contex, <YOUR_APP_ID>, <YOUR_SECRET_KEY>);
```

>The best place for calling `init` is in your `Application` class `onCreate` method.


If you ever need to disconnect from the SDK, you can use:

```java
AmondoSDK.deinitialise()
```

### Loading imprints

To connect to the Amondo database, and load all Imprints associated with your account, use:

```Java

AmondoSDK.getAllImprints(context, new GetImprintsCallback() {

        @Override
        public void onImrintsLoaded(List<Imprint> imprints) {

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
Once an imprint has been opened, all functionality is contained within that Activity, and it's children activities. When a user closes the imprint, activity is dismissed and removed from the activity stack.