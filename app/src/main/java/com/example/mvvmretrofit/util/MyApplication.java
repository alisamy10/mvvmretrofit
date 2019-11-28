package com.example.mvvmretrofit.util;

import android.app.Application;

import com.example.mvvmretrofit.roomdatabase.MyDataBase;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyDataBase.init(this);
    }
}
