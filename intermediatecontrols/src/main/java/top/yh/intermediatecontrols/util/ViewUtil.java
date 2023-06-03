package top.yh.intermediatecontrols.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * @user
 * @date
 */
public class ViewUtil {
    public static void hideOneInputMethod(Activity ac, View v){
        //从系统服务中获取输入法管理器
        InputMethodManager imm = (InputMethodManager) ac.getSystemService(Context.INPUT_METHOD_SERVICE);
        //关闭软硬盘
        imm.hideSoftInputFromWindow(v.getWindowToken(),0);
    }
}
