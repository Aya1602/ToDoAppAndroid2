package com.example.todoappandroid2.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.todoappandroid2.model.Task;

@Database(entities = {Task.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract TaskDao taskDao();
}
