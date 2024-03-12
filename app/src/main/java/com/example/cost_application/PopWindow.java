package com.example.cost_application;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.cost_application.db.AppDatabaseSingleton;
import com.example.cost_application.db.User;


public class PopWindow extends AppCompatActivity implements View.OnClickListener{

    PopupWindow popupWindow;
    View popupView;
    View view_receive;
//    LinearLayout line;

    private Activity activity;
    //ボタンのオブジェクトを貰い受ける
    public PopWindow(View view, Activity activity) {
        view_receive = view;
        this.activity = activity;
//        line = this.activity.findViewById(R.id.rowlayout_);
        popupWindow = new PopupWindow(this.activity);
//        popupWindow.setWi
        if(popupWindow==null)Log.d("popupWindow == null　29","passed");
        if (!activity.isFinishing() && !activity.isDestroyed()) {
            // アクティビティは生存しており、安全にContextを使用できます。
            Log.d("Compare.this alive in Popwindow","passed");
        }
        // 背景設定
        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(this.activity,R.drawable.popup_background));
        // 使用するレイアウトを指定する
        popupView = this.activity.getLayoutInflater().inflate(R.layout.popup_layout, null);
        if(popupView==null)Log.d("popupView == null　34","passed");
        popupWindow.setContentView(popupView);

        // popup表示時に、他のButtonなどを無効にする
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // 表示サイズを設定
//        if(findViewById(R.id.rowlayout_)==null)Log.d("findViewById(R.id.rowlayout_)==null","passed");
        popupWindow.setWidth(this.activity.findViewById(R.id.row_lay_sub).getWidth() / 10 * 9);
//        popupWindow.setWidth(800);
        popupWindow.setHeight(960);

        // PopupWindow内のボタンにクリック時の処理を定義する
        popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 背景設定
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));
        // 使用するレイアウトを指定する
        popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        if(popupView==null)Log.d("popupView == null　41","passed");
        popupWindow.setContentView(popupView);

        // popup表示時に、他のButtonなどを無効にする
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // 表示サイズを設定
        popupWindow.setWidth(findViewById(R.id.rowlayout_).getWidth() / 10 * 9);
        popupWindow.setHeight(480);

        // PopupWindow内のボタンにクリック時の処理を定義する
        popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
//        setContentView(R.layout.activity_register);
        //add button listener
//        findViewById(R.id.regTomain_button).setOnClickListener(this);
//        findViewById(R.id.done_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onResume() {
        // 省略

        super.onResume();
        popupWindow = new PopupWindow(this.activity);

        // 背景設定
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.popup_background));

        // 使用するレイアウトを指定する
        popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        if(popupView==null)Log.d("popupView == null　41","passed");
        popupWindow.setContentView(popupView);

        // popup表示時に、他のButtonなどを無効にする
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);

        // 表示サイズを設定
        popupWindow.setWidth(findViewById(R.id.rowlayout_).getWidth() / 10 * 9);
        popupWindow.setHeight(480);

        // PopupWindow内のボタンにクリック時の処理を定義する
        popupView.findViewById(R.id.close_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    // PopupWindow内のボタン押下時
    public void displayPopup(View v) {
        if(popupView==null)Log.d("popupView == null","passed");
        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
    }
}
