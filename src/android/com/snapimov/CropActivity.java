package com.snapimov;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.res.Resources;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

//import com.example.SimpleActivity.R;
import life.knowledge4.videotrimmer.K4LVideoTrimmer;
import life.knowledge4.videotrimmer.interfaces.OnK4LVideoListener;
import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;

public class CropActivity extends AppCompatActivity implements OnTrimVideoListener, OnK4LVideoListener {

    private K4LVideoTrimmer mVideoTrimmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.trim_activity);

        String package_name = getApplication().getPackageName();
        Resources resources = getApplication().getResources();

        setContentView(resources.getIdentifier("trim_activity", "layout", package_name));

        Intent extraIntent = getIntent();
        String path = "";

        if (extraIntent != null) {
            path = extraIntent.getStringExtra("video_path");
        }

        mVideoTrimmer = ((K4LVideoTrimmer) findViewById(resources.getIdentifier("timeLine", "id", package_name)));

        if (mVideoTrimmer != null) {
            mVideoTrimmer.setMaxDuration(10);
            mVideoTrimmer.setOnTrimVideoListener(this);
            mVideoTrimmer.setOnK4LVideoListener(this);
            /*mVideoTrimmer.setOnTrimVideoListener(this);
            mVideoTrimmer.setOnK4LVideoListener(this);*/
            //mVideoTrimmer.setDestinationPath("/storage/emulated/0/DCIM/CameraCustom/");
            mVideoTrimmer.setVideoURI(Uri.parse(path));
            mVideoTrimmer.setVideoInformationVisibility(true);
        }
    }

    @Override
    public void onTrimStarted() {
    }

    @Override
    public void getResult(final Uri uri) {
        // put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("uri", uri.toString());
        Log.d("WAT", "" + RESULT_OK);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void cancelAction() {
        finish();
    }

    @Override
    public void onError(final String message) {
    }

    @Override
    public void onVideoPrepared() {
    }
}
