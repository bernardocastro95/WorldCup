package com.example.worldcup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    Button button;
    EditText et;
    TextView tv;
    WorldCup wc;
    String cup_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.submitButton);
        et = findViewById(R.id.yearEditText);
        tv = findViewById(R.id.worldCupText);
        db = FirebaseFirestore.getInstance();
        wc = new WorldCup();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.getText();
                checkInput();
                //emptyInput();
                checkOlympicYear();
                checkWW2Year();

            }
        });
    }

    private void getInfo(){
        DocumentReference dr = db.collection("worldcup").document(cup_id);
        dr.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tv.setText("The World Cup of " + value.getString("year") + " was hosted at "
                + value.getString("host") + ". It was won by " + value.getString("champion")
                + "and the runner up was " + value.getString("runnerup") + ". The striker was " +
                        value.getString("striker"));
            }
        });
    }

    private void checkInput(){
         int inputValue = Integer.parseInt(et.getText().toString());
         /*if(inputValue % 4 != 0){
             Toast.makeText(this, "There was no World Cup in this year", Toast.LENGTH_SHORT).show();
         }*/
         if(inputValue < 1930){
             Toast.makeText(this, "There were no World Cups before 1930", Toast.LENGTH_SHORT).show();
         }
         if(inputValue >= 2023){
             Toast.makeText(this, "Upcoming World Cups won't be displayed yet", Toast.LENGTH_SHORT).show();
         }


    }

    /*private void emptyInput(){
        if(TextUtils.isEmpty(et.getText().toString())){
            Toast.makeText(this, "You must insert an input", Toast.LENGTH_SHORT).show();
        }
    }*/
    private void checkOlympicYear(){
        int inputValue = Integer.parseInt(et.getText().toString());
        if(inputValue == 1932 || inputValue == 1936
                || inputValue == 1952 || inputValue == 1956
                || inputValue == 1960 || inputValue == 1964
                || inputValue == 1968 || inputValue == 1972
                || inputValue == 1976 || inputValue == 1980
                || inputValue == 1984 || inputValue == 1988
                || inputValue == 1992 || inputValue == 1996
                || inputValue == 2000 || inputValue == 2004
                || inputValue == 2008 || inputValue == 2012
                || inputValue == 2016 || inputValue == 2020){
            Toast.makeText(this, "This year had the Olympic Games", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkWW2Year(){
        int inputValue = Integer.parseInt(et.getText().toString());
        if(inputValue == 1942 || inputValue == 1946){
            Toast.makeText(this, "Due to WW2, there was no World Cup", Toast.LENGTH_SHORT).show();
        }
    }
}