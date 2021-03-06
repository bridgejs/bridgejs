package org.bridgejs.android.phonebridge.library.plugins.geolocation;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;

import android.app.Activity;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class GeolocationJS {

	private PluginRequests requests;
	
	private final GeolocationListener networkListener;
	private final GeolocationListener gpsListener;
	
	private final LocationManager locationManager;
	
	private AtomicBoolean isEnabled;
	private AtomicBoolean isPaused;
	private AtomicLong minTimeBetweenUpdates;
	
	public GeolocationJS(PluginRequests requests) {
		this.requests = requests;
		isEnabled = new AtomicBoolean(false);
		isPaused = new AtomicBoolean(false);
		minTimeBetweenUpdates = new AtomicLong(100);
		
		networkListener = new GeolocationListener();
		gpsListener = new GeolocationListener();
		
		locationManager = (LocationManager) requests.getSystemService(Activity.LOCATION_SERVICE);
		
		requests.addToOnPause(new Runnable() {
			public void run() {
				if (isEnabled.get())
					turnOffLocationService();
				isPaused.set(true);
			}
		});
		
		requests.addToOnResume(new Runnable() {
			public void run() {
				if (isEnabled.get())
					turnOnLocationService(minTimeBetweenUpdates.get());
				isPaused.set(false);
			}
		});
	}
	
	public void getCurrentPosition(final int onSuccess, final int onError) {
		
		new CheckGPSTask(gpsListener, 10000, isPaused, new CurrentLocationCallback() {
			
			public void onCurrentLocation(Location currentLocation) {
				final long timestamp = System.currentTimeMillis();
				String javascriptCallback;
				
				if (currentLocation == null) {
					javascriptCallback = "__notGotCurrentLocation(" + onError + ");";
				}
				else {
					javascriptCallback = "__gotCurrentLocation(" + onSuccess + "," + currentLocation.getLatitude() + 
					"," + currentLocation.getLongitude() + "," + currentLocation.getAccuracy() + 
					", " + currentLocation.getAltitude() + ", " + currentLocation.getBearing() +
					", \"" + currentLocation.getProvider() + "\", " + currentLocation.getSpeed() +
					", " + currentLocation.getTime() + ", " + timestamp +");";
				}
				
				requests.postJavascript(javascriptCallback, this);
			}
			
		});
	}
	
	public boolean isEnabled() {
		return isEnabled.get();
	}
	
	public void enableLocationService(long minTimeBetweenUpdates) {
		this.minTimeBetweenUpdates.set(minTimeBetweenUpdates);
		turnOnLocationService(minTimeBetweenUpdates);
		this.isEnabled.set(true);
	}
	
	public void disableLocationService() {
		turnOffLocationService();
		isEnabled.set(false);
	}
	
	private void turnOffLocationService() {
		locationManager.removeUpdates(networkListener);
		locationManager.removeUpdates(gpsListener);
	}
	
	private void turnOnLocationService(long minTimeBetweenUpdates) {
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTimeBetweenUpdates, 0, networkListener);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTimeBetweenUpdates, 0, gpsListener);
	}
	
}
