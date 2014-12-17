woobi-phonegap
===================

> Woobi plugin for Android. This allows you to show the Woobi Offerwall on you app.

## Preparation:
Before you can begin using this plugin, you need to set up a Woobi account. When you do so, you will obtain an appKey. Start by going to the [Woobi](http://woobi.com//) site and click on the **Sign Up** button.

## Installation:

### Using Woobi without PhoneGap Build

From your main project directory, run:

```bash
phonegap local plugin add https://github.com/SeVeNDuS/woobi-phonegap
```

This will download the Woobi plugin and add it to your project. (Note that only Android PhoneGap projects are supported.)

### Using Woobi with PhoneGap Build

TODO

## Initialize the Woobi SDK

Initialize Woobi from your `onDeviceReady:` function like this:

```javascript
window.plugins.woobi.initialize({ appKey: 'WOOBI_APP_KEY', userId: 'APP_USERID' });
```

** For Android projects, it is mandatory that you include the Google Play Services SDK.**

### Offerwall

To show Woobi Offerwall you must execute the following code:

```javascript
window.plugins.woobi.showOfferwall();
```
