package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);


        TextView taskDetailText=findViewById(R.id.descriptionDetail);
        String details="The description of task: "+getIntent().getExtras().get("taskBody").toString()
                + "\n" +" state of task is: " +getIntent().getExtras().get("taskState").toString();
        taskDetailText.setText(details);

        TextView taskDetailTitleLabel = findViewById(R.id.detailTitle);
        String taskTitle = getIntent().getExtras().get("taskTitle").toString();
        taskDetailTitleLabel.setText(taskTitle);

    }
}









