package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class AddTask extends AppCompatActivity {

    String fileName;
    Uri dataUri;

    @SuppressLint("IntentReset")
    private void pickFile(){
        @SuppressLint("IntentReset") Intent selectedFile = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectedFile.setType(("*/*"));
        selectedFile=Intent.createChooser(selectedFile,"select file");
        startActivityForResult(selectedFile,1234);
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode , @Nullable Intent data){
        assert data != null;
        dataUri=data.getData();
        File file =new File(dataUri.getPath());
        fileName=file.getName();
        Log.i("0000000000",fileName);

        super.onActivityResult(requestCode,resultCode,data);
    }

        private void uploadFile(){
        File exampleFile = new File(getApplicationContext().getFilesDir(), "ExampleKey");

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(exampleFile));
            writer.append("Example file contents");
            writer.close();
        } catch (Exception exception) {
            Log.e("MyAmplifyApp", "Upload failed", exception);
        }

        Amplify.Storage.uploadFile(
                "ExampleKey",
                exampleFile,
                result -> Log.i("tamara", "Successfully uploaded: " + result.getKey()),
                storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
        );
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Button button2 = (Button) findViewById(R.id.button2);
        Button choose =(Button) findViewById(R.id.choose);
        Button submit =(Button) findViewById(R.id.submit);

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
                                Log.i("tamara", "Id was stored " );
                                Log.i("Amplify", "Id " + item.getId());
                            }
                        },
                        failure -> Log.e("Amplify", "Could not query DataStore", failure)
                );
             finish();
            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "file choosed",Toast.LENGTH_LONG).show();

                pickFile();


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();




            }
        });
    }
}
