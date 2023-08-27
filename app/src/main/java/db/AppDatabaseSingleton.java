package db;

import android.content.Context;
import androidx.room.Room;

public class AppDatabaseSingleton {
    private static AppDatabase instance = null;
    public static final String DB_NAME = "cost_data";
    private AppDatabaseSingleton(){}//コンストラクタ
    public static AppDatabase getInstance(Context context) {
        if (instance != null) {return instance;}

        instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        return instance;
    }
}