package top.yh.datastorage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import top.yh.datastorage.entity.LoginInfo;
import top.yh.datastorage.entity.User;

/**
 * @user
 * @date
 */
public class LoginDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "login.db";
    private static final String TABLE_NAME = "login_info";
    private static final int DB_VERSION = 1;
    private static LoginDBHelper ud = null;
    /**
     * 读实例
     */
    private SQLiteDatabase rsd = null;
    /**
     * 写实例
     */
    private SQLiteDatabase wsd = null;

    private LoginDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static LoginDBHelper getInstance(Context context) {
        if (ud == null) {
            ud = new LoginDBHelper(context);
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
                "phone varchar not null," +
                "password integer not null," +
                "remember integer not null);";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "alter table " + TABLE_NAME + " add column phone varchar;";
        sqLiteDatabase.execSQL(sql);

    }

    public void save(LoginInfo loginInfo) {
        //存在则先删除再添加
        try {
            wsd.beginTransaction();
            delete(loginInfo);
            insert(loginInfo);
            wsd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wsd.endTransaction();
        }
    }

    public long delete(LoginInfo loginInfo) {
        return wsd.delete(TABLE_NAME, "phone=?", new String[]{loginInfo.getPhone()});
    }

    public long insert(LoginInfo loginInfo) {
        ContentValues values = new ContentValues();
        values.put("phone", loginInfo.getPhone());
        values.put("password", loginInfo.getPassword());
        values.put("remember", loginInfo.isRemember());
        return wsd.insert(TABLE_NAME, null, values);
    }

    public LoginInfo queryTop() {
        LoginInfo loginInfo = null;
        String sql = "select * from " + TABLE_NAME + " where remember = 1 order by _id desc limit 1";
        Cursor cursor = rsd.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            loginInfo = new LoginInfo();
            loginInfo.setId(cursor.getInt(0));
            loginInfo.setPhone(cursor.getString(1));
            loginInfo.setPassword(cursor.getString(2));
            loginInfo.setRemember(cursor.getInt(3) != 0);
        }
        return loginInfo;
    }

    public LoginInfo queryByPhone(String phone) {
        LoginInfo loginInfo = null;
        Cursor cursor = rsd.query(TABLE_NAME, null, "phone=? and remember=1", new String[]{phone}, null, null, null);
        if (cursor.moveToNext()) {
            loginInfo = new LoginInfo();
            loginInfo.setId(cursor.getInt(0));
            loginInfo.setPhone(cursor.getString(1));
            loginInfo.setPassword(cursor.getString(2));
            loginInfo.setRemember(cursor.getInt(3) != 0);
        }
        return loginInfo;
    }
}
