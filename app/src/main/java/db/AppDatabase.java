package db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import db.UserDAO;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();//UserDAO
}