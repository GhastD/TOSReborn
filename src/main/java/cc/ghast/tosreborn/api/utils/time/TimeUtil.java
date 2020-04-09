package cc.ghast.tosreborn.api.utils.time;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtil {
    public enum TimeUnits {
        FIT, DAYS, HOURS, MINUTES, SECONDS, MILLISECONDS;
    }

    public static boolean hasExpired(long timestamp, long seconds) {
        return System.currentTimeMillis() - timestamp > TimeUnit.SECONDS.toMillis(seconds);
    }


    // 89
    // 40


    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_DAY = "yyyy-MM-dd";

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static long nowlong() {
        return System.currentTimeMillis();
    }

    public static String when(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return sdf.format(time);
    }

    public static long a(String a) {
        if (a.endsWith("s")) {
            return Long.valueOf(a.substring(0, a.length() - 1)) * 1000L;
        } else if (a.endsWith("m")) {
            return Long.valueOf(a.substring(0, a.length() - 1)) * 60000L;
        } else if (a.endsWith("h")) {
            return Long.valueOf(a.substring(0, a.length() - 1)) * 3600000L;
        } else if (a.endsWith("d")) {
            return Long.valueOf(a.substring(0, a.length() - 1)) * 86400000L;
        } else if (a.endsWith("m")) {
            return Long.valueOf(a.substring(0, a.length() - 1)) * 2592000000L;
        } else if (a.endsWith("y")) {
            return Long.valueOf(a.substring(0, a.length() - 1)) * 31104000000L;
        }

        return -1;
    }

    public static String date() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String getTime(int time) {
        Date timeDiff = new Date(); // compensate for 1h in millis
        timeDiff.setTime(time * 1000);
        SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");

        return timeFormat.format(timeDiff);
    }


    public static boolean elapsed(long from, long required) {
        return System.currentTimeMillis() - from > required;
    }

    public static long elapsed(long starttime) {
        return System.currentTimeMillis() - starttime;
    }

    public static long left(long start, long required) {
        return (required + start) - System.currentTimeMillis();
    }

    public static long differenceTimeMillis(long a, long b){
        return Math.abs(b - a);
    }
}
