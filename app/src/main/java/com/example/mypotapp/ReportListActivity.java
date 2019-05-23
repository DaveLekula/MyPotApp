package com.example.mypotapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pojo.CustomListview;
import com.example.pojo.Report;

import java.util.ArrayList;

public class ReportListActivity extends AppCompatActivity {

    private ArrayList<Report>repots = new ArrayList<>();

    String[] data = {"Pollo rally","Killer"};
    String[] num ={"1","2"};
    int [] image = {R.drawable.camera,R.drawable.settings};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);

        //final ListAdapter  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        ListView theListView =(ListView) findViewById(R.id.theListViewLV);
        CustomListview customListview = new CustomListview(this,data,num,image);
        theListView.setAdapter(customListview);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String clicked = "You selected " + String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ReportListActivity.this,clicked,Toast.LENGTH_SHORT).show();
            }
        });

    }



}
