package top.yh.datastorage.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import top.yh.datastorage.entity.BookInfo;

/**
 * @user
 * @date
 */
@Dao
public interface BookDao {
    @Insert
    void insert(BookInfo... bookInfo);
    @Delete
    void delete(BookInfo... books);
    @Query("delete from bookinfo")
    void deleteAll();
    @Update
    int update(BookInfo... books);
    @Query("select * from bookinfo")
    List<BookInfo> queryAll();
    @Query("select * from bookinfo where name = :name order by id desc limit 1")
    BookInfo queryByName(String name);
}
