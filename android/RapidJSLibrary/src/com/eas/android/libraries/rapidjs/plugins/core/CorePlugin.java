package com.eas.android.libraries.rapidjs.plugins.core;

import android.graphics.Canvas;

import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewPlugin;
import com.eas.android.libraries.rapidjs.pluginmanager.AcceleratedWebViewRequests;
import com.eas.android.libraries.rapidjs.pluginmanager.JSTools;

public class CorePlugin extends AcceleratedWebViewPlugin {

	private String coreJS;
	
	@Override
	public void init(JSTools pluginApplyer, AcceleratedWebViewRequests requests) {
		
		coreJS = requests.getRapidJSAsset("core.js");
		
	}

	@Override
	public void onDraw(Canvas canvas, int left, int top, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageFinishedLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageStartedLoading() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPluginJS() {
		// TODO Auto-generated method stub
		return null;
	}

}
