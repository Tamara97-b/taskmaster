package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
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
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSDataStorePlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());

            Amplify.configure(getApplicationContext());
            Log.i("Tutorial", "Initialized Amplify");
        } catch (AmplifyException failure) {
            Log.e("Tutorial", "Could not initialize Amplify", failure);
        }


        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );

        Amplify.Auth.fetchAuthSession(
                result -> Log.i("AmplifyQuickstart", result.toString()),
                error -> Log.e("AmplifyQuickstart", error.toString())
        );


        Amplify.DataStore.observe(Task.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );
        Amplify.DataStore.observe(Team.class,
                started -> Log.i("Tutorial", "Observation began."),
                change -> Log.i("Tutorial", change.item().toString()),
                failure -> Log.e("Tutorial", "Observation failed.", failure),
                () -> Log.i("Tutorial", "Observation complete.")
        );

        RecyclerView recyclerView = findViewById(R.id.allTask);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {

            @Override
            public boolean handleMessage(@NonNull Message message) {
                recyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });


        List<Task> allTask = new ArrayList<Task>();
//        Amplify.API.query(
//                ModelQuery.list(com.amplifyframework.datastore.generated.model.Task.class),
//                response -> {
//                    for (Task task : response.getData()) {
//                        allTask.add(task);
//                    }
//                    handler.sendEmptyMessage(1);
//                },
//                error -> Log.e("TaskMaster", error.toString(), error)
//        );
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String team = sharedPreferences.getString("team", "team");
        Amplify.DataStore.query(
                Team.class, Team.NAME.contains(team),
                items -> {
                    while (items.hasNext()) {
                        Team item = items.next();

                        Amplify.DataStore.query(
                                Task.class, Task.TEAM_ID.eq(item.getId()),
                                itemss -> {
                                    allTask.clear();
                                    while (itemss.hasNext()) {
                                        Task item1 = itemss.next();
                                        allTask.add(item1);
                                        Log.i("DUCK", "list " + item1.getTeamId());

                                    }
                                    handler.sendEmptyMessage(1);
                                },
                                failure -> Log.e("Amplify", "Could not query DataStore", failure)
                        );
                        Log.i("Amplify", "Id " + item.getId());
                    }
                    handler.sendEmptyMessage(1);
                },
                failure -> Log.e("Amplify", "Could not query DataStore", failure)
        );

        Button buttonAddTask = (Button) findViewById(R.id.goToAddTask);


        Button buttonAllTask = (Button) findViewById(R.id.goToAllTask);
        buttonAllTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG).show();
                Intent goToOtherActivityIntent = new Intent(MainActivity.this, AllTask.class);
                startActivity(goToOtherActivityIntent);
            }
        });

        Button settingButton = (Button) findViewById(R.id.settingButton);


        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToSettingPage = new Intent(MainActivity.this, Settingspage.class);
                startActivity(goToSettingPage);

            }

        });



        Button buttonSignOut = (Button) findViewById(R.id.signout);

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "signed out", Toast.LENGTH_LONG).show();
                Amplify.Auth.signOut(
                        () -> Log.i("AuthQuickstart", "Signed out successfully"),
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TaskAdapter(allTask));

    }



    }




    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName", "the user didn't add a name yet!");
        Toast.makeText(this, userName, Toast.LENGTH_LONG).show();
        TextView textView = findViewById(R.id.textView);
        textView.setText(userName);
    }
}


