package com.example.cost_application;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import db.User;
import db.UserDAO;

public class Register extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    EditText shop_name_data,category_data,name_data,cost_data,date_data,unit_data,remark_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //add button listener
        findViewById(R.id.regTomain_button).setOnClickListener(this);
        findViewById(R.id.done_button).setOnClickListener(this);
        t= findViewById(R.id.title_register_text);
        shop_name_data= findViewById(R.id.edit_shopname);
        category_data= findViewById(R.id.edit_category);
        name_data= findViewById(R.id.edit_name);
        cost_data= findViewById(R.id.edit_cost);
        date_data= findViewById(R.id.edit_date);
        unit_data= findViewById(R.id.edit_unit);//unit_data.getText().toString();で取得可能
        remark_data= findViewById(R.id.edit_remark);
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        UserDAO userDAO = new UserDAO();
        User user = new User("ion","cucumber","cucumber",321,230926,"7","ai");
        if (v.getId() == R.id.regTomain_button) {
            //register button push process
            startActivity(new Intent(this, MainActivity.class));
        }else if (v.getId() == R.id.done_button) {
            //register button push process

            startActivity(new Intent(this, Complete.class));
        }
        //名残
        Snackbar.make(v, "ボタンが押されました", Snackbar.LENGTH_SHORT).show();
        //
    }
}

