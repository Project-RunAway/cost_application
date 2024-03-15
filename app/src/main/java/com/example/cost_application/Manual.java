package com.example.cost_application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Manual extends AppCompatActivity implements View.OnClickListener{

    TextView t;
//        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        //add button listener
        findViewById(R.id.manual_Tomain_button).setOnClickListener(this);
//        t= (TextView) findViewById(R.id.manual_title_text);
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.manual_Tomain_button) {
            //register button push process
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
