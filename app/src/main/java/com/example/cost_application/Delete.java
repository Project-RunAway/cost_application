package com.example.cost_application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cost_application.db.AppDatabase;
import com.example.cost_application.db.AppDatabaseSingleton;
import com.example.cost_application.db.User;
import com.example.cost_application.db.UserDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Delete extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    AppDatabase db;
    UserDAO userDAO;
    User user,entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        //add button listener
        findViewById(R.id.delTomain_button).setOnClickListener(this);
        findViewById(R.id.del_alldelete_button).setOnClickListener(this);
        t= findViewById(R.id.title_delete_text);
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.delTomain_button) {
            //register button push process
            startActivity(new Intent(this, MainActivity.class));
        }else if(v.getId() == R.id.del_alldelete_button){
            //全てデータを消す
            delete_all_db();
            Snackbar.make(v, "全てのデータが削除されました", Snackbar.LENGTH_SHORT).show();
        }
    }

    //全てのデータベースを削除する関数
    private void delete_all_db(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            @Override
            public void run() {
                try {
                    db = AppDatabaseSingleton.getInstance(getApplicationContext());
                    userDAO = db.userDAO();
                    //add insert
                    userDAO.deleteAll();
                } catch(Exception e){
//                    Log.d("data_delete_test","error");
                }
            }
        });
    }
}

