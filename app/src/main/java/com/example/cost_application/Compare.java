package com.example.cost_application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.cost_application.db.AppDatabase;
import com.example.cost_application.db.AppDatabaseSingleton;
import com.example.cost_application.db.User;
import com.example.cost_application.db.UserDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Compare extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    AppDatabase db;
    UserDAO userDAO;
    User user;

    private List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        //add button listener
        findViewById(R.id.comTomain_button).setOnClickListener(this);
        t= findViewById(R.id.title_compare_text);
//        db = AppDatabaseSingleton.getInstance(getApplicationContext());//データベースのインスタンス
//        userDAO = db.userDAO();
        //一時的にここに取得するものをかく
        load_db();
//        new GetItemsAsyncTask(userDAO, (GetItemsAsyncTask.OnTaskCompleted) this).execute();
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.comTomain_button) {
            //register button push process

            startActivity(new Intent(this, MainActivity.class));
        }
        //名残
        Snackbar.make(v, "ボタンが押されました", Snackbar.LENGTH_SHORT).show();
        //
    }

    //database読み込み
    private void load_db(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            @Override
            public void run(){
                db = AppDatabaseSingleton.getInstance(getApplicationContext());
                userDAO =db.userDAO();

                list = userDAO.getAll();

                if(list.size() > 0){
                    for(int number=0;number<list.size();number++) {
                        User entity = list.get(number);
                        String sn = entity.getShop_name();
                        String n = entity.get_Name();
                        String cn = entity.get_Name();
                        double co = entity.getCost();
                        int da = entity.getDate();
                        String u = entity.getUnit();
                        String r = entity.getRemark();

                        Log.d("database_test", sn + ":" + n + ":" + cn+ ":" + co+ ":" + da+ ":" + u+ ":" + r);
                    }
                }else {
                    Log.d("no_data","no data");
                }
            }
        });
    }

    //非同期に挿入するための関数
    private void write_db(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            @Override
            public void run() {
                try {
                    db = AppDatabaseSingleton.getInstance(getApplicationContext());
                    userDAO = db.userDAO();
                    //add insert
                    userDAO.insert(user);
                    Log.d("data_test_write","complete insert");
                    load_db();
                } catch(Exception e){
                    Log.d("data_test","error");
                }
            }
        });
    }

//    @Override
//    public void onTaskCompleted(List<User> items) {
//        // ここで取得したアイテムリストを処理
//        // 例: ログに出力
//        for (User item : items) {
//            Log.d("Database", "Item ID: " + item.id);
//        }
//    }

//    private static class GetItemsAsyncTask extends AsyncTask<Void, Void, List<User>> {
//        private UserDAO userDAO;
//        private OnTaskCompleted listener;
//
//        GetItemsAsyncTask(UserDAO userDAO, OnTaskCompleted listener) {
//            this.userDAO = userDAO;
//            this.listener = listener;
//        }
//
//        @Override
//        protected List<User> doInBackground(Void... voids) {
//            return userDAO.getAll();
//        }
//
//        @Override
//        protected void onPostExecute(List<User> items) {//itemsのなかに取得したUserのリストが入っている
//            super.onPostExecute(items);
//            for(User item : items){
//                Log.d("compare","Item shopname : " + item.shopName_str);
//            }
//            listener.onTaskCompleted(items);
//        }
//
//        public interface OnTaskCompleted {
//            void onTaskCompleted(List<User> items);
//        }
//    }
}