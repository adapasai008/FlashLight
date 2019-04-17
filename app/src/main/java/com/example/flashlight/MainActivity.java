package com.example.flashlight;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private ImageView imageView;
    private ImageView imageView2;
    private static final int CAMERA_REQUEST = 50;
    private boolean flashLightStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]
                {Manifest.permission.CAMERA},CAMERA_REQUEST);

        final boolean hasFlashLight = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (hasFlashLight){
                 if (flashLightStatus){
                     flashLightOff();
                 }else
                     flashLightOn();
                  //imageView2.animate().alpha(0).setDuration(1000);
                  //imageView.animate().alpha(1).setDuration(1000);
             }else {
                 Toast.makeText(MainActivity.this,"no flash available on your device",Toast.LENGTH_SHORT)
                         .show();
             }
            }
        });
    }

    private void flashLightOn(){
        CameraManager cameraManager =(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,true);
            flashLightStatus = true;
            imageView2.setImageResource(R.drawable.slice_0_1);
        } catch (CameraAccessException e) {

        }


    }
    private void flashLightOff(){
        CameraManager cameraManager =(CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            String cameraId = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraId,false);
            flashLightStatus = false;
            imageView2.setImageResource(R.drawable.slice_0_0);
        } catch (CameraAccessException e) {

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   imageView2.setEnabled(true);
                }else {
                    Toast.makeText(MainActivity.this, "Permission Denied for the Camera", Toast.LENGTH_SHORT).show();
                }
                break;

            }
        }
    }









































































































