package com.example.cost_application;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.cost_application.db.AppDatabase;
import com.example.cost_application.db.AppDatabaseSingleton;
import com.example.cost_application.db.User;
import com.example.cost_application.db.UserDAO;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.view.inputmethod.InputMethodManager;


public class Compare extends AppCompatActivity implements View.OnClickListener{
    TextView t;
    EditText search_bar_edit;
    AppDatabase db;
    UserDAO userDAO;
    User user,entity;
    String search_category;

    //追加項目
    //Map<string,string>は<key,value>という意味合いを持つ
    public static Map<String, String> data;
    public static List<Map<String, String>> dataList;
    public static ListView listView;
    public static ListViewAdapter adapter;

    private List<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        //add button listener
        findViewById(R.id.comTomain_button).setOnClickListener(this);
        findViewById(R.id.compare_done_button).setOnClickListener(this);
        findViewById(R.id.compare_all).setOnTouchListener((v, event) -> {
            hideKeyboard_0();
            return false;
        });
        search_bar_edit = findViewById(R.id.search_bar);
        load_db();
    }

    //    @Override
    //button push process
    public void onClick(View v) {
        if (v.getId() == R.id.comTomain_button) {
            //register button push process
            //画面遷移
            startActivity(new Intent(this, MainActivity.class));
        }else if(v.getId() == R.id.compare_done_button){
            //keyboardをたたむ
            hideKeyboard(search_bar_edit);
            //search button push process
            //textviewの読み込み
            if(search_bar_edit.getText().toString().isEmpty()){
                load_db();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                //sort();//ここで値段順ソートする
                //dataのlistへの格納と表示
                dataList = new ArrayList<Map<String, String>>();
                // ListViewに表示するためのDataを作成する
                for (int i = 0; i < list.size(); i++) {
                    data = new HashMap<String, String>();
                    User entity_instant = list.get(i);
                    entity = list.get(i);
                    String sn2 = entity_instant.getShop_name();
                    String n2 = entity_instant.get_Name();
                    String cn2 = entity_instant.getCategory();
                    double co2 = entity_instant.getCost();
                    int da2 = entity_instant.getDate();
                    String u2 = entity_instant.getUnit();
                    String r2 = entity_instant.getRemark();
                    int id = entity_instant.getId();
                    data.put("text1", sn2);//店名
                    data.put("text2", n2);//商品名
                    data.put("text3", String.valueOf(co2));//値段
                    data.put("category", cn2);//category
                    data.put("date", String.valueOf(da2));//date
                    data.put("unit", u2);//unit
                    data.put("remark", r2);//remark
                    data.put("id", String.valueOf(id));
                    dataList.add(data);
                }
                // アダプターにデータを渡す
                adapter = new ListViewAdapter(
                        this,
                        dataList,
                        R.layout.row,
                        new String[]{"text1", "text2", "text3"},
                        new int[]{android.R.id.text1,
                                android.R.id.text2,
                                R.id.text3},
                        Compare.this,
                        this,
                        list);

                // ListViewにアダプターをSETする
                listView = (ListView) findViewById(R.id.data_list);
                listView.setAdapter(adapter);
                listView.setTextFilterEnabled(false);
            }
            else {
                search_category = search_bar_edit.getText().toString();
                //listにcategoryで検索されたUserのオブジェクトを入れる
                search_category_db(search_category);
                //ここで本来なら処理を分岐させて最後に結合するが難しいので処理を強制停止する200ms
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //dataのlistへの格納と表示
                dataList = new ArrayList<Map<String, String>>();
                // ListViewに表示するためのDataを作成する
                for (int i = 0; i < list.size(); i++) {
                    data = new HashMap<String, String>();
                    User entity_instant = list.get(i);
                    entity = list.get(i);
                    String sn2 = entity_instant.getShop_name();
                    String n2 = entity_instant.get_Name();
                    String cn2 = entity_instant.getCategory();
                    double co2 = entity_instant.getCost();
                    int da2 = entity_instant.getDate();
                    String u2 = entity_instant.getUnit();
                    String r2 = entity_instant.getRemark();
                    int id = entity_instant.getId();
                    data.put("text1", sn2);//店名
                    data.put("text2", n2);//商品名
                    data.put("text3", String.valueOf(co2));//値段
                    data.put("category", cn2);//category
                    data.put("date", String.valueOf(da2));//date
                    data.put("unit", u2);//unit
                    data.put("remark", r2);//remark
                    data.put("id", String.valueOf(id));
                    dataList.add(data);
                }
                // アダプターにデータを渡す
                adapter = new ListViewAdapter(
                        this,
                        dataList,
                        R.layout.row,
                        new String[]{"text1", "text2", "text3"},
                        new int[]{android.R.id.text1,
                                android.R.id.text2,
                                R.id.text3},
                        Compare.this,
                        this,
                        list);

                // ListViewにアダプターをSETする
                listView = (ListView) findViewById(R.id.data_list);
                listView.setAdapter(adapter);
                listView.setTextFilterEnabled(false);
            }
        }
        //名残
        Snackbar.make(v, "リストを表示", Snackbar.LENGTH_SHORT).show();
        //
    }

    //listを値段順に並べる
    private void sort(List<User> l){

    }

    private void hideKeyboard_0() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Compare.this.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void swift(User a,User b){
        User instant;
        instant=a;
        a=b;
        b=instant;
    }

    //databaseからcategory検索してlistに格納
    private void search_category_db(String search){//sはcategory名
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            @Override
            public void run(){
                db = AppDatabaseSingleton.getInstance(getApplicationContext());
                userDAO =db.userDAO();
                //カテゴリで絞って取得
                list = userDAO.getSearch_db(search);
                //listの値段順にソートする

                if(list.size() > 0){
                    for(int number=0;number<list.size();number++) {
                        User entity = list.get(number);
                        String sn = entity.getShop_name();
                        String n = entity.get_Name();
                        String cn = entity.getCategory();
                        double co = entity.getCost();
                        int da = entity.getDate();
                        String u = entity.getUnit();
                        String r = entity.getRemark();
                    }
                }else {}
            }
        });
    }

    //database全ての読み込み
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
//                        Log.d("database_test", sn + ":" + n + ":" + cn+ ":" + co+ ":" + da+ ":" + u+ ":" + r);
                    }
                }else {
//                    Log.d("no_data","no data");
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
                    load_db();
                } catch(Exception e){}
            }
        });
    }

    public void delete_db(int id){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable(){
            @Override
            public void run() {
                try {
                    db = AppDatabaseSingleton.getInstance(getApplicationContext());
                    userDAO = db.userDAO();
                    //add insert
                    userDAO.delete_id(id);
                    load_db();
                } catch(Exception e){
//                    Log.d("data_delete_test","error");
                }
            }
        });
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Compare.this.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}