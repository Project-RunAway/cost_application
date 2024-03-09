package com.example.cost_application;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cost_application.db.AppDatabase;
import com.example.cost_application.db.AppDatabaseSingleton;
import com.google.android.material.snackbar.Snackbar;
import com.example.cost_application.db.User;
import com.example.cost_application.db.UserDAO;
//import db from "../";

public class Register extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    EditText shop_name_data,category_data,name_data,cost_data,date_data,unit_data,remark_data;
    AppDatabase db;
    UserDAO userDAO;
    User user;
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
        db = AppDatabaseSingleton.getInstance(getApplicationContext());//データベースのインスタンス
        userDAO = db.userDAO();
        user = new User("ion","cucumber","cucumber",321,230926,"7","ai");


    }

    //    @Override
    //button push process
    public void onClick(View v) {
//        UserDAO userDAO = new UserDAO();
//        User user = new User("ion","cucumber","cucumber",321,230926,"7","ai");
        if (v.getId() == R.id.regTomain_button) {
            //register button push process
            startActivity(new Intent(this, MainActivity.class));
        }else if (v.getId() == R.id.done_button) {
            //register button push process
            //書き込まれた内容をインスタンス変数に入れていく
            user.shopName_str=shop_name_data.getText().toString();
            user.category_str=category_data.getText().toString();
            user.name_str=name_data.getText().toString();
            user.cost_num=Integer.parseInt(cost_data.getText().toString());
            user.date_num=Integer.parseInt(date_data.getText().toString());;
            user.unit_str=unit_data.getText().toString();
            //もし空なら空白を代入する
            if(remark_data.getText().toString().isEmpty())user.remark_str=" ";
            else user.remark_str=remark_data.getText().toString();
            //作成したuserを非同期でデータベースに挿入する
            new InsertUserAsyncTask(userDAO).execute(user);
            Log.d("Register", "Item ID: " + user.shopName_str);

            startActivity(new Intent(this, Complete.class));
        }
        //名残
        Snackbar.make(v, "ボタンが押されました", Snackbar.LENGTH_SHORT).show();
        //
    }

    //非同期に挿入するための関数
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        public UserDAO userDAO;

        private InsertUserAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(User... items) {
            userDAO.insert(items[0]);
            return null;
        }
    }
}

