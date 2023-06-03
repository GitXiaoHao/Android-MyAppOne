package top.yh.mybutton.util;

import java.time.LocalTime;

/**
 * @user
 * @date
 */
public class DateUtil {
    public static String getNowTime() {
        return LocalTime.now().toString();
    }
}
