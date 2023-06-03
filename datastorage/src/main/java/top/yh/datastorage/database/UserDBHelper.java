package top.yh.datastorage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.time.chrono.IsoEra;
import java.util.ArrayList;
import java.util.List;

import top.yh.datastorage.entity.User;

/**
 * @user
 * @date
 */
public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "user.db";
    private static final String TABLE_NAME = "user_info";
    private static final int DB_VERSION = 1;
    private static UserDBHelper ud = null;
    /**
     * 读实例
     */
    private SQLiteDatabase rsd = null;
    /**
     * 写实例
     */
    private SQLiteDatabase wsd = null;

    private UserDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static UserDBHelper getInstance(Context context) {
        if (ud == null) {
            ud = new UserDBHelper(context);
        }
        return ud;
    }

    /**
     * 打开数据库的读连接
     *
     * @return
     */
    public SQLiteDatabase openReadLink() {
        if (rsd == null || !rsd.isOpen()) {
            rsd = ud.getReadableDatabase();
        }
        return rsd;
    }

    /**
     * 打开数据库的写连接
     *
     * @return
     */
    public SQLiteDatabase openWriteLink() {
        if (wsd == null || !wsd.isOpen()) {
            wsd = ud.getWritableDatabase();
        }
        return wsd;
    }

    public void closeLink() {
        if (rsd != null && rsd.isOpen()) {
            rsd.close();
            rsd = null;
        }
        if (wsd != null && wsd.isOpen()) {
            wsd.close();
            wsd = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table if not exists " + TABLE_NAME + "(_id integer primary key autoincrement not null," +
                "name varchar not null," +
                "age integer not null," +
                "tall integer not null," +
                "weight float not null," +
                "wed integer not null);";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "alter table " + TABLE_NAME + " add column phone varchar;";
        sqLiteDatabase.execSQL(sql);

    }

    public long insert(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("tall", user.getTall());
        values.put("weight", user.getWeight());
        values.put("wed", user.isWed());
        return wsd.insert(TABLE_NAME, null, values);
    }

    public long deleteByName(String name) {
        return wsd.delete(TABLE_NAME, "name=?", new String[]{name});
    }

    public long update(User user) {
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("age", user.getAge());
        values.put("tall", user.getTall());
        values.put("weight", user.getWeight());
        values.put("wed", user.isWed());
        return wsd.update(TABLE_NAME, values, "name=?", new String[]{user.getName()});
    }

    public List<User> queryAll() {
        List<User> list = new ArrayList<>();
        Cursor cursor = rsd.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            User u = new User();
            u.setId(cursor.getInt(0));
            u.setName(cursor.getString(1));
            u.setAge(cursor.getInt(2));
            u.setTall(cursor.getInt(3));
            u.setWeight(cursor.getFloat(4));
            u.setWed(cursor.getInt(5) != 0);
            list.add(u);
        }
        return list;
    }

    public List<User> queryByName(String name) {
        List<User> list = new ArrayList<>();
        Cursor cursor = rsd.query(TABLE_NAME, null, "name=?", new String[]{name}, null, null, null);
        while (cursor.moveToNext()) {
            User u = new User();
            u.setId(cursor.getInt(0));
            u.setName(cursor.getString(1));
            u.setAge(cursor.getInt(2));
            u.setTall(cursor.getInt(3));
            u.setWeight(cursor.getFloat(4));
            u.setWed(cursor.getInt(5) != 0);
            list.add(u);
        }
        return list;
    }
}
