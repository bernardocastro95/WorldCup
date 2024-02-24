package com.example.worldcup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    Button button, back, clear;
    EditText et;
    TextView title, year, host, champion, runnerup, striker, goals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.submitButton);
        back = findViewById(R.id.returnButton);
        clear = findViewById(R.id.clearButton);
        et = findViewById(R.id.yearEditText);
        title = findViewById(R.id.title);
        year = findViewById(R.id.year);
        host = findViewById(R.id.host);
        champion = findViewById(R.id.champion);
        runnerup = findViewById(R.id.runnerup);
        striker = findViewById(R.id.striker);
        goals = findViewById(R.id.goals);
        db = FirebaseFirestore.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                et.getText();
                checkInput();
                inBetween();
                checkOlympicYear();
                checkWW2Year();
                getInfo();

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText(R.string.title);
                year.setText("");
                host.setText("");
                champion.setText("");
                runnerup.setText("");
                striker.setText("");
                goals.setText("");
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.VISIBLE);
                et.setVisibility(View.VISIBLE);
                back.setVisibility(View.INVISIBLE);
                clear.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void getInfo(){


        if(!et.getText().toString().isEmpty()){
            DocumentReference dr = db.collection("allWorldCups").document(et.getText().toString());
            dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if(!year.getText().toString().isEmpty()
                    && !host.getText().toString().isEmpty()
                    && !champion.getText().toString().isEmpty()
                    && !runnerup.getText().toString().isEmpty()
                    && !striker.getText().toString().isEmpty()
                    && !goals.getText().toString().isEmpty()){
                        title.setText(R.string.title);
                        year.setText("");
                        host.setText("");
                        champion.setText("");
                        runnerup.setText("");
                        striker.setText("");
                        goals.setText("");
                        String cupYear = documentSnapshot.getString("year");
                        String cupHost = documentSnapshot.getString("host");
                        String cupChampion = documentSnapshot.getString("champion");
                        String cupRunnerUp = documentSnapshot.getString("runnerup");
                        String cupStriker = documentSnapshot.getString("striker");
                        String strikerGoals = documentSnapshot.getString("goals");
                        year.setText(getResources().getString(R.string.year) + cupYear);
                        host.setText(getResources().getString(R.string.host) + cupHost);
                        champion.setText(getResources().getString(R.string.champion) + cupChampion);
                        runnerup.setText(getResources().getString(R.string.runnerup) + cupRunnerUp);
                        striker.setText(getResources().getString(R.string.striker) + cupStriker);
                        goals.setText(" - " + strikerGoals);
                        et.getText().clear();
                        et.setVisibility(View.INVISIBLE);
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                        button.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        clear.setVisibility(View.VISIBLE);
                    }
                    else {
                        String cupYear = documentSnapshot.getString("year");
                        String cupHost = documentSnapshot.getString("host");
                        String cupChampion = documentSnapshot.getString("champion");
                        String cupRunnerUp = documentSnapshot.getString("runnerup");
                        String cupStriker = documentSnapshot.getString("striker");
                        String strikerGoals = documentSnapshot.getString("goals");

                        year.setText(getResources().getString(R.string.year) + cupYear);
                        host.setText(getResources().getString(R.string.host) + cupHost);
                        champion.setText(getResources().getString(R.string.champion) + cupChampion);
                        runnerup.setText(getResources().getString(R.string.runnerup) + cupRunnerUp);
                        striker.setText(getResources().getString(R.string.striker) + cupStriker);
                        goals.setText("- " + strikerGoals);
                        et.getText().clear();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
                        et.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.INVISIBLE);
                        back.setVisibility(View.VISIBLE);
                        clear.setVisibility(View.VISIBLE);

                    }
                }
            });
        }


    }

    private void checkInput(){
        int inputValue = Integer.parseInt(et.getText().toString());
        if (inputValue < 1930){
            Toast.makeText(getApplicationContext(), "There were no world cups before 1930", Toast.LENGTH_SHORT).show();
        }
        else if (inputValue > 2023){
            Toast.makeText(getApplicationContext(), "Upcoming World Cups will not be displayed yer", Toast.LENGTH_SHORT).show();
        }
    }
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
    private void inBetween(){
        int inputValue = Integer.parseInt(et.getText().toString());
        if(inputValue == 1931 || inputValue == 1933
                || inputValue == 1935 || inputValue == 1937
                || inputValue == 1939 || inputValue == 1941
                || inputValue == 1943 || inputValue == 1945
                || inputValue == 1946 || inputValue == 1947
                || inputValue == 1948 || inputValue == 1949
                || inputValue == 1951 || inputValue == 1953
                || inputValue == 1955 || inputValue == 1957
                || inputValue == 1959 || inputValue == 1961
                || inputValue == 1963 || inputValue == 1965
                || inputValue == 1967 || inputValue == 1969
                || inputValue == 1971 || inputValue == 1973
                || inputValue == 1975 || inputValue == 1977
                || inputValue == 1979 || inputValue == 1981
                || inputValue == 1983 || inputValue == 1985
                || inputValue == 1987 || inputValue == 1993
                || inputValue == 1995 || inputValue == 1997
                || inputValue == 1999 || inputValue == 2001
                || inputValue == 2003 || inputValue == 2005
                || inputValue == 2007 || inputValue == 2009
                || inputValue == 2011 || inputValue == 2013
                || inputValue == 2015 || inputValue == 2017
                || inputValue == 2019 || inputValue == 2021){
            Toast.makeText(this, "There was no World Cup this year", Toast.LENGTH_SHORT).show();


        }
    }
}