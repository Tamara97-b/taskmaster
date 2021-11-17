package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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


        AppDatabase db=Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "tasksDatabase")
                .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        List<Task> taskList = db.taskDAO().getAll();
        System.out.println(taskList);


        RecyclerView recyclerView = findViewById(R.id.allTask);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(taskList));


        Button buttonAddTask = (Button) findViewById(R.id.goToAddTask);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                Intent goToOtherActivityIntent = new Intent(MainActivity.this, AddTask.class);
                startActivity(goToOtherActivityIntent);
            }
        });



        Button buttonAllTask = (Button) findViewById(R.id.goToAllTask);
        buttonAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                Intent goToOtherActivityIntent = new Intent(MainActivity.this, AllTask.class);
                startActivity(goToOtherActivityIntent);
            }
        });

     Button settingButton=(Button) findViewById(R.id.settingButton);


        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this,Settingspage.class);
                startActivity(goToSettingPage);

            }

        });

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