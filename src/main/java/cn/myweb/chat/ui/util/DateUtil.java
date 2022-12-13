package cn.myweb.chat.ui.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String simpleDate(Date talkDate) {
        boolean today = isToday(talkDate);
        if(today) {
            return new SimpleDateFormat("HH:mm").format(talkDate);
        }
        return new SimpleDateFormat("yy/mm/dd").format(talkDate);
    }
    private static boolean isToday(Date date) {
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        Calendar now = Calendar.getInstance();
        if(now.get(Calendar.YEAR) == cl.get(Calendar.YEAR) &&
        now.get(Calendar.MONTH) == cl.get(Calendar.MONTH) &&
        now.get(Calendar.DAY_OF_MONTH) == cl.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }
}
