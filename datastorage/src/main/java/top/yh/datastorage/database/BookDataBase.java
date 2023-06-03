package top.yh.datastorage.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import top.yh.datastorage.dao.BookDao;
import top.yh.datastorage.entity.BookInfo;

/**
 * @user
 * @date
 * entities表示该数据库有那些表，version表示数据库的版本号
 * exportSchema表示是否导出数据库信息的Json串，建议设为false 若设为true还需指定json文件的保存路径
 */
@Database(entities = {BookInfo.class}, version = 1, exportSchema = true)
public abstract class BookDataBase extends RoomDatabase {
    /**
     * 获取该数据库中某张表的持久化对象
     * @return
     */
    public abstract BookDao bookDao();
}
