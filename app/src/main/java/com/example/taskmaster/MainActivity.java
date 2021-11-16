package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonAddTask = (Button) findViewById(R.id.goToAddTask);

        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                // to start another activity, we will use intent
                Intent goToOtherActivityIntent = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToOtherActivityIntent);
            }
        });
        Button buttonAllTask = (Button) findViewById(R.id.goToAllTask);

        buttonAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                // to start another activity, we will use intent
                Intent goToOtherActivityIntent = new Intent(MainActivity.this, AllTask.class);
                startActivity(goToOtherActivityIntent);
            }
        });
//     Button runButton = (Button) findViewById(R.id.runButton);
//     Button swimButton=(Button) findViewById(R.id.swimButton);
//     Button walkButton =(Button) findViewById(R.id.walkButton);
     Button settingButton=(Button) findViewById(R.id.settingButton);
//     runButton.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View view) {
//             String buttonForRunTask = runButton.getText().toString();
//             Intent goToDetailPage = new Intent(MainActivity.this,TaskDetail.class);
//             goToDetailPage.putExtra("task detail",buttonForRunTask);
//             startActivity(goToDetailPage);
//
//         }
//
//     });
//        swimButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String buttonForSwimTask = swimButton.getText().toString();
//                Intent goToDetailPage = new Intent(MainActivity.this,TaskDetail.class);
//                goToDetailPage.putExtra("task detail",buttonForSwimTask);
//                startActivity(goToDetailPage);
//
//            }
//        });
//        walkButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String buttonForWalkTask = walkButton.getText().toString();
//                Intent goToDetailPage = new Intent(MainActivity.this,TaskDetail.class);
//                goToDetailPage.putExtra("task detail",buttonForWalkTask);
//                startActivity(goToDetailPage);
//
//            }
//        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this,Settingspage.class);
                startActivity(goToSettingPage);

            }

        });
        List<Task> allTask = new ArrayList<Task>();
        allTask.add(new Task("task1","task body", "new"));
        allTask.add(new Task("task2","task body","in progress"));
        allTask.add(new Task("task3","task body","complete"));
        allTask.add(new Task("task4","task body","new"));
        allTask.add(new Task("task5","task body","in progress"));
        allTask.add(new Task("task5","task body","complete"));
        allTask.add(new Task("task5","task body","new"));
        allTask.add(new Task("task6","task body","in progress"));



        RecyclerView recyclerView = findViewById(R.id.allTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTask));


    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName","the user didn't add a name yet!");
        Toast.makeText(this, userName,Toast.LENGTH_LONG).show();
        TextView textView=findViewById(R.id.textView);
        textView.setText(userName);
    }




}