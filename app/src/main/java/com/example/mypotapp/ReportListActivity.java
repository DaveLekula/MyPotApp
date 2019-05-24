package com.example.mypotapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pojo.CustomListview;
import com.example.pojo.ListData;
import com.example.pojo.Report;

import java.util.ArrayList;

public class ReportListActivity extends AppCompatActivity {

    private ArrayList<Report>repots;

    String[] data = new String[50];
    String[] num =new String[50];
    int [] image = new int[50];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        ListData listData = new ListData();
        ArrayList<Report> reportlist = ListData.getReportList();
        if(reportlist.size()> 0){
            for(int x = 0; x<reportlist.size(); x++){

                data[x] = reportlist.get(x).getStatus();
                num[x] = reportlist.get(x).getDate().toString();
                //image[x]
            }

        }


        //final ListAdapter  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        ListView theListView =(ListView) findViewById(R.id.theListViewLV);
        CustomListview customListview = new CustomListview(this,data,num,image);
        theListView.setAdapter(customListview);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String clicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ReportListActivity.this,clicked,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ReportListActivity.this,DetailedReport.class);
                intent.putExtra("Position",position);
                startActivity(intent);
            }
        });

    }



}
