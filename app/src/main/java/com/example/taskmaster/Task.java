
//package com.example.taskmaster;
//
//public class Task {
//    public String title;
//    public String body;
//    public String state;
//
//    public Task(String title, String body, String state) {
//        this.title = title;
//        this.body = body;
//        this.state = state;
//    }
//}

package com.example.taskmaster;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity

public class Task {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "task_title")
    public String title;
    @ColumnInfo(name = "task_body")
    public String body;
    @ColumnInfo(name = "task_state")
    public String state;


    @Override
    public String toString() {
        return "Task{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public Task(String title, String body, String state) {
        this.title = title;
        this.body = body;
        this.state = state;

    }
}

