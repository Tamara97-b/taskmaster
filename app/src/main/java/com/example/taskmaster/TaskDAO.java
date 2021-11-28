package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDAO {


        @Query("SELECT * FROM task")
        List<Task> getAll();

        @Query("SELECT * FROM task WHERE uid IN (:taskIds)")
        List<Task> loadAllByIds(int[] taskIds);


        @Insert
        void insertAll(Task... tasks);

        @Delete
        void delete(Task task);
}

