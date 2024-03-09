package com.example.cost_application;
import android.os.Handler;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.appcompat.app.AppCompatActivity;
//package
import com.example.cost_application.db.UserDAO;
import com.example.cost_application.db.User;
import com.example.cost_application.db.AppDatabase;
import com.example.cost_application.db.AppDatabaseSingleton;

//databaseを扱うためのメソッドの定義をここに書く
public class Dataaccess extends AppCompatActivity {
    private AppDatabase mDb;
    private final UserDAO mDao;
    private List<User> m_UserList;
    private List<User> mLad = null;
    final Handler mHandler = new Handler();             //ハンドラー

    //コンストラクタ
    public Dataaccess() {
        mDb = AppDatabaseSingleton.getInstance(getApplicationContext());//データベースの生成重複なし
        mDao = mDb.userDAO();//DAOの生成
    }

    /**DBからデータ取得してセット*/
    public void setUserLocationList() {
        // Roomは非同期でアクセスしないと、エラーを出すので注意
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //mCoordinateList = mDao.getCoordinateList();
                    // UI変更など

                } catch (Exception e) {
                    // エラー処理
                }
            }
        });
    }

    /**DBへデータ追加*/
    public void insert(User user){

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    mDao.insert(user);
                } catch (Exception e) {
                    // エラー処理
                }
            }
        });
    }

    /**DBのデータを削除*/
    public void delete(User user){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    try {
                        mDao.delete(user);//特定のuserオブジェクトしか消せない
                        //リストをたっぷして消せるようにしたいねDelete.javaを改良してものを判定させるようにしたい
                        //例えばたっぷしたリストの位置から商品名と日付を取得してクエリを使って探索してuserを返すものでdelete
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } catch (Exception e) {
                    // エラー処理
                }
            }
        });
    }

    public void getData() {
        //ExecutorServiceを取得
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //ExecutorServiceでタスクを実行（非同期処理）
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //mLad = mDao.getData();  //DBからデータを取得
                } catch (Exception e) {
                    //データ取得エラー時の処理
                }

                //データ取得成功時の処理
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //データを更新
                        //画面に表示させる
                    }
                });
            }
        });
    }
}
//activityにおける使い方
//User user = new User(入れたいデータ引数);
//Dataaccess dataaccess = new Dataaccess();
//coordinateHelper.insert(user); // データの追加
