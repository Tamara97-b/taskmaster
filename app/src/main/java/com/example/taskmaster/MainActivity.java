package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.configure(getApplicationContext());

            Log.i("TaskMaster", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("TaskMaster", "Could not initialize Amplify", error);
        }

        RecyclerView recyclerView = findViewById(R.id.allTask);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        List<Task> allTask = new ArrayList<Task>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
                response -> {
                    for (Task task : response.getData()) {
                        allTask.add(task);
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("TaskMaster", error.toString(), error)
        );

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

     Button settingButton=(Button) findViewById(R.id.settingButton);


        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this,Settingspage.class);
                startActivity(goToSettingPage);

            }

        });

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