package com.itheima.mvplayer.utils;

public class StringUtils {

    private static final int HOUR = 60 * 60 * 1000;
    private static final int MINUTE = 60 * 1000;
    private static final int SECOND = 1000;

    public static String formatDuration(int duration) {
        int hour = duration / HOUR;
        int minute = duration % HOUR / MINUTE;
        int second = duration % HOUR % MINUTE / SECOND;

        if (hour == 0) {
            return String.format("%02d:%02d", minute, second);
        } else {
            return String.format("%02d:%02d:%02d", hour, minute, second);
        }
    }
}
