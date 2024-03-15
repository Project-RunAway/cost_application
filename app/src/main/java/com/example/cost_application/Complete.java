package com.example.cost_application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class Complete extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        //add button listener
        findViewById(R.id.completeTomain_button).setOnClickListener(this);
        findViewById(R.id.completeTocom_button).setOnClickListener(this);
        findViewById(R.id.completeToregister_button).setOnClickListener(this);
        t= findViewById(R.id.title_complete_text);
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.completeTomain_button) {
            //register button push process
            startActivity(new Intent(this, MainActivity.class));
        } else if (v.getId() == R.id.completeTocom_button) {
            //register button push process
            startActivity(new Intent(this, Compare.class));
        }else if (v.getId() == R.id.completeToregister_button) {
            //register button push process
            startActivity(new Intent(this, Register.class));
        }
        //名残
        Snackbar.make(v, "ボタンが押されました", Snackbar.LENGTH_SHORT).show();
        //
    }
}