package com.example.cost_application;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.os.Bundle;

public class ListViewAdapter extends SimpleAdapter {

    private LayoutInflater inflater;
    //1つのリストのなかには同じstring型のキーは存在してはならない。しかし複数のリストの中では可能つまり、
    //今回ではstring(text1),string(text2)をキーとしてリストに格納していき、そのなかに任意のデータ型を入れる
    // list---> key(text1) 値,key(text2) 値,key(text3) 値のように格納していく。
    private List<? extends Map<String, ?>> listData;
    private Activity activity;

    // 各行が保持するデータ保持クラス
    public class ViewHolder {
        TextView text1;//店名
        TextView text2;//商品名
        TextView text3;//商品の値段
    }

    public ListViewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to,Activity activity) {
        super(context, data, resource, from, to);
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.listData = data;
        this.activity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        ViewHolder holder1;

        // ビューを受け取る
        View view = convertView;

        // 画面起動時にViewHolderを作成する
        view = inflater.inflate(R.layout.row, parent, false);

        holder1 = new ViewHolder();
        holder1.text1 = (TextView) view.findViewById(R.id.text1);
        holder1.text2 = (TextView) view.findViewById(R.id.text2);
        holder1.text3 = (TextView) view.findViewById(R.id.text3);

        view.setTag(holder1);
        if(view != null){
            // 行選択時などは既に作成されているものを取得する
            holder1 = (ViewHolder) view.getTag();
        }

        holder = holder1;
        if(holder==null){Log.d("Holder == null","passed");}
        if(holder.text1==null){Log.d("Holder.text1 == null","passed");}
        if(holder.text2==null){Log.d("Holder.text2 == null","passed");}
        if(holder.text3==null){Log.d("Holder.text3 == null","passed");}
        String text1 = ((HashMap<?, ?>) listData.get(position)).get("text1").toString();//店名
        String text2 = ((HashMap<?, ?>) listData.get(position)).get("text2").toString();//商品名
        String text3 = ((HashMap<?, ?>) listData.get(position)).get("text3").toString();//値段
        String category = ((HashMap<?, ?>) listData.get(position)).get("category").toString();//値段
        String date = ((HashMap<?, ?>) listData.get(position)).get("date").toString();//値段
        String unit = ((HashMap<?, ?>) listData.get(position)).get("unit").toString();//値段
        String remark = ((HashMap<?, ?>) listData.get(position)).get("remark").toString();//値段
        holder.text1.setText(text1);
        holder.text2.setText(text2);
        holder.text3.setText(text3);

        // セル上にあるボタンの処理
        Button btn = (Button) view.findViewById(R.id.rowbutton);
        btn.setTag(position);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // 選択したセルの文字を赤色にする
                holder.text1.setTextColor(Color.RED);
                holder.text2.setTextColor(Color.RED);
                holder.text3.setTextColor(Color.RED);
                //かくぼたんのクリック処理をここに書く、、ここにpop windowの処理をかく
                PopWindow popWindow = new PopWindow(arg0,activity);
                popWindow.displayPopup(arg0);
            }

        });

        return view;
    }

//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        final ViewHolder holder;
//
//        // ビューを受け取る
//        View view = convertView;
//
//        if (view == null) {
//            // 画面起動時にViewHolderを作成する
//            view = inflater.inflate(R.layout.row, parent, false);
//
//            holder = new ViewHolder();
//            holder.text1 = (TextView) view.findViewById(android.R.id.text1);
//            holder.text2 = (TextView) view.findViewById(android.R.id.text2);
//            holder.text3 = (TextView) view.findViewById(R.id.text3);
//
//            view.setTag(holder);
//        } else {
//            // 行選択時などは既に作成されているものを取得する
//            holder = (ViewHolder) view.getTag();
//        }
//
//        String text1 = ((HashMap<?, ?>) listData.get(position)).get("text1").toString();
//        String text2 = ((HashMap<?, ?>) listData.get(position)).get("text2").toString();
//        String text3 = ((HashMap<?, ?>) listData.get(position)).get("text3").toString();
//        holder.text1.setText(text1);
//        holder.text2.setText(text2);
//        holder.text3.setText(text3);
//
//        // セル上にあるボタンの処理
//        Button btn = (Button) view.findViewById(R.id.rowbutton);
//        btn.setTag(position);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View arg0) {
//                // 選択したセルの文字を赤色にする
//                holder.text1.setTextColor(Color.RED);
//            }
//        });
//
//        return view;
//    }
}
