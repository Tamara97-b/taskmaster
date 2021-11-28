package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Task;
import com.amplifyframework.datastore.generated.model.Team;

public class AddTask extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button button2 = (Button) findViewById(R.id.button2);


//        Team item1 = Team.builder()
//                .name("team1")
//                .build();
//        Amplify.DataStore.save(
//                item1,
//                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                error -> Log.e("Amplify", "Could not save item to DataStore", error)
//        );
//
//        Team item2 = Team.builder()
//                .name("team2")
//                .build();
//        Amplify.DataStore.save(
//                item2,
//                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                error -> Log.e("Amplify", "Could not save item to DataStore", error)
//        );
//
//
//
//        Team item3 = Team.builder()
//                .name("tem3")
//                .build();
//        Amplify.DataStore.save(
//                item3,
//                success -> Log.i("Amplify", "Saved item: " + success.item().getId()),
//                error -> Log.e("Amplify", "Could not save item to DataStore", error)
//        );

        Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.items, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        EditText title = findViewById(R.id.editTitleId);
        EditText body = findViewById(R.id.editBodyId);
        EditText state = findViewById(R.id.editStateId);


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

                String team =  s.getSelectedItem().toString();

                Amplify.DataStore.query(
                        Team.class,Team.NAME.contains(team),
                        items -> {
                            while (items.hasNext()) {
                                Team item = items.next();
                                Task item1 = Task.builder().title(titleText).body(bodyText).state(stateText).teamId(item.getId()).build();
                                Amplify.DataStore.save(
                                        item1,
                                        success -> Log.i("COMO", "Saved item: "),
                                        error -> Log.e("Amplify", "Could not save item to DataStore", error)
                                );
                                Log.i("EMAM", "Id was stored " );
                                Log.i("Amplify", "Id " + item.getId());
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );

             finish();




            }
        });
    }
}



