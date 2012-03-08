# The bridgeJS Project #

[bridgeJS](http://www.bridgejs.com) aims to bridge the gap between apps on phones and the web. While PhoneGap was a step in the right direction, we yearn for more: 

We want the ability to access to native APIs of devices directly from any website that can run on a device.

This is why we decided to create [bridgeJS](http://www.bridgejs.com)



## bridge.js

A javascript library that bridges the gap between different APIs (Webkit, PhoneGap, and PhoneBridge).
See [bridgeJS](http://www.bridgejs.com) for an API reference

## PhoneBridge

An application that runs on the Android platform that acts as a web browser but a bit different:

* Use the official native bridgeJS API
* Render HTML5's canvas at least as efficient or more efficiently than the stock browser


PhoneBridge will open immediately from any Android browser with:

`bridge.android.forceNative()`

An `alert` will pop up with:

`bridge.android.recommendNative()`

## TODO

* Implement a permission system similar to Android's for potentially dangerous native API calls
* Simplify the plugin framework with Java's annotations
* Support *ALL* of PhoneGap's APIs so any existing PhoneGap code will be able to run directly from a web browser.
* Add more plugins (at least implement all of PhoneGap's plugiins)
* Add more example applications and plugins


## Warning 

The API is *experimental* and subject to change
Do not deploy production code with [bridgeJS](http://www.bridgejs.com) yet. 
Instead use PhoneGap's APIs -- we will eventually support all PhoneGap API calls.

