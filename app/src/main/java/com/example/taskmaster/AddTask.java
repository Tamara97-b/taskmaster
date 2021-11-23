package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Task Added",Toast.LENGTH_LONG).show();
                EditText taskTitle = findViewById(R.id.formOfTitle);
                EditText taskBody = findViewById(R.id.formOfBody);
                EditText taskState = findViewById(R.id.formOfstate);



                String titleText = taskTitle.getText().toString();
                String bodyText = taskBody.getText().toString();
                String stateText = taskState.getText().toString();

                Task t=Task.builder().title(titleText).body(bodyText).state(stateText).build();

                Amplify.API.mutate(
                        ModelMutation.create(t),
                        response -> Log.i("TaskMaster", "Added Task with id: " + response.getData().getId()),
                        error -> Log.e("TaskMaster", "Create failed", error)
                );

            }
        });
    }
}