package com.world.bolandian.implicitintents;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CameraIntent extends AppCompatActivity {

    private Button btnPic,btnVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_intent);

        btnPic = (Button)findViewById(R.id.btnPic);
        btnVideo = (Button)findViewById(R.id.btnVideo);


        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    capturePhoto();
                }catch (Exception e){
                    Toast.makeText(CameraIntent.this, "Not found place to save data", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    captureVideo();
                }catch (Exception e){
                    Toast.makeText(CameraIntent.this, "Not found place to save data", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void capturePhoto() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,1);
        }
    }


    public void captureVideo() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_VIDEO_CAMERA);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent,2);
        }
    }
}
