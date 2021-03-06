package org.bridgejs.android.phonebridge.library.plugins.acceleratedCanvas2D;

import java.util.Hashtable;
import java.util.Iterator;

import org.bridgejs.android.phonebridge.library.pluginmanager.PluginRequests;


import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;

public class NativeCanvases {

	public CanvasUnifier canvasUnifier;

	private Hashtable<String, NativeCanvas> nativeCanvasesByID;

	public void mLog(String str) {
		Log.d("AWV", str);
	}

	public NativeCanvases(PluginRequests requests) {
		nativeCanvasesByID = new Hashtable<String, NativeCanvas>();
		canvasUnifier = new CanvasUnifier(nativeCanvasesByID, requests);
	}

	public void draw(Canvas page, int x, int y, float scale){
		Iterator<NativeCanvas> canvases = nativeCanvasesByID.values().iterator();




		while (canvases.hasNext()){
			NativeCanvas nativeCanvas = canvases.next();

			Matrix matrix = new Matrix();

			matrix.setTranslate(x + nativeCanvas.x, y + nativeCanvas.y);

			matrix.postScale(scale, scale);

			if (nativeCanvas.bitmap != null){
				page.drawBitmap(nativeCanvas.bitmap, matrix, null);
			}
		}
	}
}
