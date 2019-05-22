package com.example.mypotapp;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class ClientHomeActivity extends AppCompatActivity {


    private ImageButton camBTN;
    private ImageButton viewBTN;
    private ImageButton settingsBTN;
    String pathToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);
        viewBTN = (ImageButton) findViewById(R.id.homeViewListBTN);
        settingsBTN = (ImageButton) findViewById(R.id.settingsHomeBtn);
        camBTN =(ImageButton) findViewById(R.id.camButton);
        if(Build.VERSION.SDK_INT >= 23){
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);

        }
        camBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchPictureTakerAction();
            }
        });

        settingsBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ClientHomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        viewBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClientHomeActivity.this, ReportListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void dispatchPictureTakerAction() {
        Intent takepic =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takepic.resolveActivity(getPackageManager()) != null){
            File photoFile = null;
            photoFile = createPhotoFile();
            if(photoFile != null) {
                pathToFile = photoFile.getAbsolutePath();
                Uri photoURI = FileProvider.getUriForFile(ClientHomeActivity.this,"com.example.mypotapp.fileprovider",photoFile);
                takepic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takepic,1);

            }
        }

    }

            private void sendData(String f){
                Intent intent = new Intent(ClientHomeActivity.this,PicActivity.class);
                intent.putExtra("MyImage", f);
                startActivity(intent);
            }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode == 1){
                sendData(pathToFile);
                // Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                System.out.println("############ PATH TO FILE :" + pathToFile);
                //imageView.setImageBitmap(bitmap);

            }
        }
    }

    private File createPhotoFile() {

        String name = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir = getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;
        try {
             image = File.createTempFile(name,".jpg", storageDir);
        } catch (IOException e) {
            Log.d("mylog","Excep : " + e.toString());
        }

        return image;
    }
}
