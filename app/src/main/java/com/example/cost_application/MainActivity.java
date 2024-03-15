package com.example.cost_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView t;
    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add button listener
        findViewById(R.id.register_button).setOnClickListener(this);
        findViewById(R.id.delete_button).setOnClickListener(this);
        findViewById(R.id.compare_button).setOnClickListener(this);
        findViewById(R.id.manual_button).setOnClickListener(this);
        t= findViewById(R.id.title_text);
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            //register button push process
            startActivity(new Intent(this, Register.class));
        } else if (v.getId() == R.id.delete_button) {
            //delete button push process
            startActivity(new Intent(this, Delete.class));
        } else if (v.getId() == R.id.compare_button) {
            //compare button push process
            startActivity(new Intent(this, Compare.class));
        }else if (v.getId() == R.id.manual_button) {
            //compare button push process
            startActivity(new Intent(this, Manual.class));
        }
        //名残
        Snackbar.make(v, "ボタンが押されました", Snackbar.LENGTH_SHORT).show();
        //
    }
}