package com.example.mypotapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pojo.Report;

import java.io.File;
import java.util.Date;

public class PicActivity extends AppCompatActivity {

    private Button btnUpload;
    private Button btnReturn;
    private ImageView myImageView;
    private TextView tvCoordinates;
    private TextView tvValidation;
    private double logitude;
    private double latitude;
    private String pathToFile;

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);



        btnUpload = (Button) findViewById(R.id.Uploadbtn);
        btnReturn = (Button) findViewById(R.id.returnBtn);
        myImageView = findViewById(R.id.potImageView);
        tvCoordinates =(TextView) findViewById(R.id.CoorditesTV);
        Intent intent = getIntent();
        pathToFile = (String) intent.getExtras().get("MyImage");

        Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
        myImageView.setImageBitmap(bitmap);
        startLocationListener();

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PicActivity.this,ClientHomeActivity.class);
                startActivity(intent);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Report report = new Report();
                report.setDate(new Date());
                report.setLongitude(logitude);
                report.setLatitude(latitude);
                report.setStatus("Sent Request to Admin");
                report.setImage(new File(pathToFile));

                Bundle bundle = new Bundle();
                bundle.putSerializable("MyPojoReport",report);
                Intent intent = new Intent(PicActivity.this,ClientHomeActivity.class);
                intent.putExtra("ReportClass",bundle);
                Toast toast = Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_LONG);
                toast.show();
                startActivity(intent);
            }
        });

    }

    private void startLocationListener() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Toast toast = Toast.makeText(getApplicationContext(),"Location Found 8",Toast.LENGTH_LONG);
                toast.show();
                tvCoordinates.append("\n "+location.getLatitude() + "  " + location.getLongitude());
                latitude = location.getLatitude();
                logitude = location.getLongitude();

            }



            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }


            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                },10);

               Toast  toast = Toast.makeText(getApplicationContext(),"IF Statement Run",Toast.LENGTH_LONG);
                toast.show();
            }
            //locationManager.requestLocationUpdates("gps", 5000, 5, locationListener);
            locationManager.requestSingleUpdate("gps",locationListener,null);
            return;
        }else{
           // locationManager.requestLocationUpdates("gps", 5000, 5, locationListener);
            locationManager.requestSingleUpdate("gps",locationListener,null);
            Toast toast = Toast.makeText(getApplicationContext(),"Else Statement Run",Toast.LENGTH_LONG);
            toast.show();
        }



    }
}
