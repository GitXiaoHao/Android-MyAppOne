package top.yh.dsp;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.yh.dsp.database.ShoppingDBHelper;
import top.yh.dsp.entity.GoodsInfo;
import top.yh.dsp.util.FileUtil;
import top.yh.dsp.util.SharedUtil;


/**
 * @user
 * @date
 */
public class MyApplication extends android.app.Application {
    private static MyApplication application;
    public Map<String, String> map = new HashMap<>();
    public int goodsCount = 0;
    public static MyApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        //初始化商品信息
        initGoodsInfo();
        Log.d("yu", "在App启动时调用");
    }

    private void initGoodsInfo() {
        //获取共享参数保存的是否首次打开
        SharedUtil sharedUtil = SharedUtil.getInstance(this);
        String first = "first";
        String directory = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + File.separatorChar;
        if(sharedUtil.readBoolean(first,true)){
            //模拟网络图片下载
            List<GoodsInfo> l = GoodsInfo.getDefaultList();
            for (GoodsInfo goodsInfo : l) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), goodsInfo.getPic());
                String path = directory + goodsInfo.getId() + ".jpg";
                FileUtil.saveImage(path,bitmap);
                //回收
                bitmap.recycle();
                goodsInfo.setPicPath(path);
            }
            //打开数据库，把商品信息插入到表中
            ShoppingDBHelper helper = ShoppingDBHelper.getInstance(this);
            helper.openWriteLink();
            helper.insertGoodsInfo(l);
            helper.closeLink();
            //把是否首次打开写入共享参数
            sharedUtil.writeBoolean(first,false);
        }
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

}
