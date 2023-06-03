package top.yh.dsp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import top.yh.dsp.entity.CartInfo;
import top.yh.dsp.entity.GoodsInfo;

/**
 * @user
 * @date
 */
public class ShoppingDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "shopping.db";
    /**
     * 商品信息表
     */
    private static final String TABLE_NAME_GOODS_INFO = "goods_info";
    /**
     * 购物车信息表
     */
    private static final String TABLE_NAME_CART_INFO = "cart_info";
    private static final int DB_VERSION = 1;
    private static ShoppingDBHelper ud = null;
    /**
     * 读实例
     */
    private SQLiteDatabase rsd = null;
    /**
     * 写实例
     */
    private SQLiteDatabase wsd = null;

    private ShoppingDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static ShoppingDBHelper getInstance(Context context) {
        if (ud == null) {
            ud = new ShoppingDBHelper(context);
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
        //商品信息表
        String sql = "create table if not exists " + TABLE_NAME_GOODS_INFO +
                "(_id integer primary key autoincrement not null," +
                "name varchar not null," +
                "description varchar not null," +
                "price float not null," +
                "pic_path varchar not null);";
        sqLiteDatabase.execSQL(sql);
        //购物车表
        sql = "create table if not exists " + TABLE_NAME_CART_INFO +
                "(_id integer primary key autoincrement not null," +
                "goods_id varchar not null," +
                "count integer not null);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * 添加多条信息
     *
     * @param list
     */
    public void insertGoodsInfo(List<GoodsInfo> list) {
        try {
            wsd.beginTransaction();
            for (GoodsInfo goodsInfo : list) {
                ContentValues values = new ContentValues();
                values.put("name", goodsInfo.getName());
                values.put("description", goodsInfo.getDescription());
                values.put("price", goodsInfo.getPrice());
                values.put("pic_path", goodsInfo.getPicPath());
                wsd.insert(TABLE_NAME_GOODS_INFO, null, values);
            }
            wsd.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wsd.endTransaction();
        }
    }

    public List<GoodsInfo> queryAllGoodsInfo() {
        List<GoodsInfo> list = new ArrayList<>();
        Cursor cursor = rsd.rawQuery("select * from " + TABLE_NAME_GOODS_INFO, null);
        while (cursor.moveToNext()) {
            list.add(new GoodsInfo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getFloat(3),
                    cursor.getString(4)
            ));
        }
        cursor.close();
        return list;
    }

    public void insertCartInfo(int goodsInfoId) {
        //如果购物车中不存在该商品，添加一条信息
        CartInfo cartInfo = queryCartInfoByGoodsId(goodsInfoId);
        ContentValues values = new ContentValues();
        values.put("goods_id", goodsInfoId);
        if (cartInfo == null) {
            values.put("count", 1);
            wsd.insert(TABLE_NAME_CART_INFO, null, values);
        } else {
            values.put("_id", cartInfo.getId());
            values.put("count", cartInfo.getCount() + 1);
            wsd.update(TABLE_NAME_CART_INFO, values, "_id=?", new String[]{String.valueOf(cartInfo.getId())});
        }
    }

    /**
     * 根据商品id查询购物车信息
     *
     * @param goodsInfoId
     * @return
     */
    private CartInfo queryCartInfoByGoodsId(int goodsInfoId) {
        Cursor cursor = rsd.query(TABLE_NAME_CART_INFO, null, "goods_id=?", new String[]{String.valueOf(goodsInfoId)}, null, null, null);
        CartInfo cartInfo = null;
        if (cursor.moveToNext()) {
            cartInfo = new CartInfo(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            );
        }
        cursor.close();
        return cartInfo;
    }

    /**
     * 统计购物车中商品的总数量
     */
    public int countCartInfo() {
        int count = 0;
        String sql = "select sum(count) from " + TABLE_NAME_CART_INFO;
        Cursor cursor = rsd.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            count = cursor.getInt(0);
        }
        return count;
    }

    /**
     * 查询购物车中所有的信息列表
     *
     * @return
     */
    public List<CartInfo> queryAllCartInfo() {
        List<CartInfo> list = new ArrayList<>();
        Cursor cursor = rsd.query(TABLE_NAME_CART_INFO, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            list.add(new CartInfo(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getInt(2)
            ));
        }
        cursor.close();
        return list;
    }

    public GoodsInfo queryGoodsInfoById(int goodsId) {
        GoodsInfo goodsInfo = null;
        Cursor cursor = rsd.query(TABLE_NAME_GOODS_INFO, null, "_id=?", new String[]{String.valueOf(goodsId)}, null, null, null);
        if (cursor.moveToNext()) {
            goodsInfo = new GoodsInfo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getFloat(3),
                    cursor.getString(4)
            );
        }
        cursor.close();
        return goodsInfo;
    }

    public void deleteCartInfoByGoodsId(CartInfo cartInfo) {
        wsd.delete(TABLE_NAME_CART_INFO, "goods_id=?", new String[]{String.valueOf(cartInfo.getGoodsId())});
    }

    public void deleteAllCartInfo() {
        wsd.delete(TABLE_NAME_CART_INFO, "1=1", null);
    }
}
