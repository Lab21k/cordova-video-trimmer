package com.snapimov;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.net.Uri;
import android.app.Activity;

import java.util.ArrayList;
import java.util.Date;
import java.io.IOException;

public class VideoCrop extends CordovaPlugin {
	private static final String TAG = "VideoCrop";
	private CallbackContext callback;

	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);

		Log.d(TAG, "Initializing VideoCrop");
	}

	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		if (action.equals("trimVideo")) {
			String srcPath = args.getString(0);
			String dstPath = args.getString(1);
			long startMs = args.getLong(2);
			long endMs = args.getLong(3);

			Log.d(TAG, "SRC PATH" + srcPath);
			Log.d(TAG, "DST PATH" + dstPath);
			Log.d(TAG, "startMS" + startMs);
			Log.d(TAG, "endMS" + endMs);

            callback = callbackContext;

			Context context = this.cordova.getActivity().getApplicationContext();
			Intent intent = new Intent(context, CropActivity.class);
			intent.putExtra("video_path", srcPath);
			this.cordova.startActivityForResult(this, intent, 0);

		} else if (action.equals("getPreview")) {
		  String path = args.getString(0);

		  try {
		  } catch (Error e) {
			e.printStackTrace();
		  }
		}

		return true;
	  }

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String uri = data.getStringExtra("uri");
        Log.d(TAG, uri);

        final PluginResult result = new PluginResult(PluginResult.Status.OK, uri);
        callback.sendPluginResult(result);

        /*if (requestCode == -1) {
            if (resultCode == Activity.RESULT_CANCELED) {
            }
        }*/

	}
}
