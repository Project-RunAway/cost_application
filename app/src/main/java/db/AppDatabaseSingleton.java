package db;

import android.content.Context;
import androidx.room.Room;

public class AppDatabaseSingleton {
    private static AppDatabase instance = null;

    private AppDatabaseSingleton(){}//コンストラクタ
    public static AppDatabase getInstance(Context context) {
        if (instance != null) {return instance;}

        instance = Room.databaseBuilder(context, AppDatabase.class, "cost_data").build();
        return instance;
    }
}