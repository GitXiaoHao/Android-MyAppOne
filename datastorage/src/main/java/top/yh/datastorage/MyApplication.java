package top.yh.datastorage;

import android.content.res.Configuration;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Room;

import java.util.HashMap;
import java.util.Map;

import top.yh.datastorage.database.BookDataBase;

/**
 * @user
 * @date
 */
public class MyApplication extends android.app.Application {
    private static MyApplication application;
    public Map<String, String> map = new HashMap<>();
    /**
     * 声明一个书籍数据库实例
     */
    private BookDataBase bookDataBase;
    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        bookDataBase = Room.databaseBuilder(this,BookDataBase.class,"bookinfo")
                //允许迁移数据库（发生数据库变更时，room默认删除原数据库在创建新数据库,如此原来的记录会丢失，故而改为迁移方式
                .addMigrations()
                //允许在主线程中操作数据库（ROOM默认不能在主线程中操作数据库）
                .allowMainThreadQueries()
                        .build();
        Log.d("yu", "在App启动时调用");
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("yu", "在配置改变时调用，例如竖屏变为横屏");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d("yu", "在App终止时调用");
    }

    /**
     * 获取书籍数据库的实例
     * @return
     */
    public BookDataBase getBookDataBase(){
        return bookDataBase;
    }
}
