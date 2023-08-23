package com.example.cost_application;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class Register extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //add button listener
        findViewById(R.id.regTomain_button).setOnClickListener(this);
        t= findViewById(R.id.title_register_text);
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.regTomain_button) {
            //register button push process
            startActivity(new Intent(this, MainActivity.class));
        }
        //名残
        Snackbar.make(v, "ボタンが押されました", Snackbar.LENGTH_SHORT).show();
        //
    }
}

