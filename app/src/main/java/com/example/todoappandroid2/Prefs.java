package com.example.todoappandroid2;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {

    private SharedPreferences preferences;
    private Prefs instance;

    public Prefs getInstance() {
        return instance;
    }

    public Prefs(Context context) {
        instance = this;
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

    }
    public void saveBoardsState(){
        preferences.edit().putBoolean("isShown",true).apply();
    }

    public boolean isBoardShown(){
        return preferences.getBoolean("isShown",false);
    }
}
