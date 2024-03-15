package com.example.cost_application.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM cost_data WHERE category =:category ORDER BY date DESC,cost ASC ")
    List<User> getSearch_db(String category);
    //categoryが一致するもののカラムを抽出してlistで返す一旦消すことにするエラーのため

    @Query("SELECT id FROM cost_data WHERE name =:name AND date =:date")
    int getSearch_id(String name,int date);//商品名と日付によってそのidを返すクエリ

    @Query("SELECT * FROM cost_data WHERE name =:name AND date =:date")
    User getSearch_object(String name,int date);//商品名と日付によってそのobjectを返すクエリ

    @Query("SELECT * FROM cost_data")
    List<User> getAll();

    //all delete
    @Query("DELETE FROM cost_data")
    void deleteAll();

    //id=xのものを削除する
    @Query("DELETE FROM cost_data WHERE id = :x")
    void delete_id(int x);



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
