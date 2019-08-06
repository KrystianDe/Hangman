package com.example.asus.myapplication;



public class DataHolder {

    private static WordsDatabaseHelper data;

    public static WordsDatabaseHelper getDate() {
        return data;
    }

    public static void setData(WordsDatabaseHelper data) {
        DataHolder.data = data;
    }


}
