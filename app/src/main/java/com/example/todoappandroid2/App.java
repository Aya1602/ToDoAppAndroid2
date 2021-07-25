package com.example.todoappandroid2;

import android.app.Application;

import androidx.room.Room;

import com.example.todoappandroid2.room.AppDataBase;

public class App extends Application {

    private static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        new Prefs(this);
        appDataBase = Room
                .databaseBuilder(this, AppDataBase.class, "database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }
}
