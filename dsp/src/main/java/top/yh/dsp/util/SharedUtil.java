package top.yh.dsp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @user
 * @date
 */
public class SharedUtil {
    private static SharedUtil sharedUtil;
    private SharedPreferences sharedPreferences;
    public static SharedUtil getInstance(Context context){
        if (sharedUtil == null) {
            sharedUtil = new SharedUtil();
            sharedUtil.sharedPreferences = context.getSharedPreferences("shopping",Context.MODE_PRIVATE);
        }
        return sharedUtil;
    }
    public void writeBoolean(String key, boolean value){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(key,value);
        edit.apply();
    }
    public boolean readBoolean(String key, boolean defaultValue){
        return sharedPreferences.getBoolean(key,defaultValue);
    }
}
