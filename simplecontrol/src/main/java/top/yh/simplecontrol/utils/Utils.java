package top.yh.simplecontrol.utils;

import android.content.Context;

/**
 * @user
 * @date
 */
public class Utils {
    /**
     * 根据手机的分辨率从dp的单位转成px（像素）
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue){
        //获取当前手机的像素密度（1个dp对应几个px）
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5f);
    }
}
