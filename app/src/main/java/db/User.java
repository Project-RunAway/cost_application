package db;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName="cost_data")
public class User {
    @PrimaryKey(autoGenerate=true)
    public int id;

    @ColumnInfo(name = "shop_name")
    public String shopName_str;

    @ColumnInfo(name = "category")
    public String category_str;

    @ColumnInfo(name = "name")
    public String name_str;

    @ColumnInfo(name = "cost")
    public double cost_num;

    @ColumnInfo(name = "date")
    public int date_num;

    @ColumnInfo(name = "unit")
    public String unit_str;

    @ColumnInfo(name = "remark")
    public String remark_str;

    public User(String shopName_str,String category_str,String name_str,double cost_num,int date_num,String unit_str,String remark_str){
        this.shopName_str=shopName_str;
        this.category_str=category_str;
        this.name_str=name_str;
        this.cost_num=cost_num;
        this.date_num=date_num;
        this.unit_str=unit_str;
        this.remark_str=remark_str;
    }

    public String getShop_name(){
        return this.shopName_str;
    }

    public String getCategory(){
        return this.category_str;
    }

    public String get_Name(){
        return this.name_str;
    }

    public double getCost(){
        return this.cost_num;
    }

    public int getDate(){
        return this.date_num;
    }

    public String getUnit(){
        return this.unit_str;
    }

    public String getRemark(){
        return this.remark_str;
    }

}
