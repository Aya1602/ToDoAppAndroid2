package com.example.todoappandroid2.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.todoappandroid2.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    List<Task> getAll();

    @Insert
    void insert (Task task);
}
