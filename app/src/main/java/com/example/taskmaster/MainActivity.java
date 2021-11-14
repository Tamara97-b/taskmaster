package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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


    }




}