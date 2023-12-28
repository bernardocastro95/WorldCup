package com.example.worldcup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    Button button;
    EditText et;

    WorldCup wc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.submitButton);
        et = findViewById(R.id.yearEditText);
        db = FirebaseFirestore.getInstance();
        wc = new WorldCup();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et.getText();
            }
        });
    }

    private void getInfo(){
        db.collection("worldcup")
                .whereEqualTo("year", wc.getYear())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                        }
                        else {

                        }
                    }
                });
    }
}