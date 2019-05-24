package com.example.pojo;

import java.util.ArrayList;

public class ListData {

    private static ArrayList<Report> reportList = new ArrayList<Report>();

    public ListData() {
    }

    public static ArrayList<Report> getReportList() {
        return reportList;
    }

    public void addReport(Report rep){

        reportList.add(rep);

    }


}
