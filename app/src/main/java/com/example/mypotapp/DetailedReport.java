package com.example.mypotapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pojo.ListData;
import com.example.pojo.Report;

import java.util.ArrayList;

public class DetailedReport extends AppCompatActivity {

    private Button btnUpdate;
    private Button btnReturn;
    private ImageView myImageView;
    private TextView tvLongitude;
    private TextView tvLatitude;
    private TextView tvStatus;
    private TextView tvDescr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_report);

        myImageView = findViewById(R.id.ReportPotImageView);
        btnReturn = findViewById(R.id.returnBtn2);
        btnUpdate= findViewById(R.id.Updatebtn);
        tvLongitude = findViewById(R.id.DetailLongiTV);
        tvLatitude = findViewById(R.id.detailLatiTV);
        tvStatus = findViewById(R.id.DetaiStatusTV);
        tvDescr  = findViewById(R.id.DetaiDescrTV);

        Intent intent = getIntent();
        int p = intent.getIntExtra("Position",-1);

        String clicked = "You selected " + String.valueOf(p);
        Toast.makeText(DetailedReport.this,clicked,Toast.LENGTH_SHORT).show();

        ArrayList<Report> rep = ListData.getReportList();
        Report report = rep.get(p);

        if((report.getImage().exists()) ||(report.getImage() != null)){

            Bitmap b = BitmapFactory.decodeFile(report.getImage().getAbsolutePath());
            myImageView.setImageBitmap(b);
            String clicke = "If statement true ";
            Toast.makeText(DetailedReport.this,clicke,Toast.LENGTH_SHORT).show();
        }
        tvLongitude.setText(String.valueOf(report.getLongitude()));
        tvLatitude.setText(String.valueOf(report.getLatitude()));
        tvDescr.setText(report.getDescription());
        tvStatus.setText(report.getStatus());


    }
}
