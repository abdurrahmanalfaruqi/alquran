package com.example.alquran;

import android.app.Application;
import android.content.res.Resources;

import com.example.alquran.database.DataHelper;
import com.example.alquran.t.PreferenceApp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.DatabaseMetaData;

public class App extends Application {

    private static Resources resources;

    @Override
    public void onCreate() {
        super.onCreate();

        resources =getResources();
        DataHelper.initDatabase(this);
        PreferenceApp.initPreferences(this);
    }

    public static BufferedReader getRawResources(int res){
        InputStream streamReader = resources.openRawResource(res);
        return new BufferedReader(new InputStreamReader(streamReader));
    }
}
