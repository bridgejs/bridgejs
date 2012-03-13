package org.bridgejs.android.phonebridge.library.plugins.button.handlers;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;
import org.bridgejs.android.phonebridge.library.pluginmanager.activitymodifiers.ButtonRunnable;

import android.view.KeyEvent;

public class BackButtonHandler extends ButtonHandler {

	private PluginRequests requests;
	
	public BackButtonHandler(PluginRequests requests) {
		super(requests, KeyEvent.KEYCODE_BACK);
		
		this.requests = requests;
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		requests.addToOnBackButtonModifier(new ButtonRunnable() {

			public boolean run(KeyEvent event) {
				return BackButtonHandler.super.callback(event);
			}
			
		});
	}

}
