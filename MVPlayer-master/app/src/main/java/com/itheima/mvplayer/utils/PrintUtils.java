package com.itheima.mvplayer.utils;

import android.database.Cursor;
import android.util.Log;

public class PrintUtils {
    public static final String TAG = "PrintUtils";

    public static void printCursor(Cursor cursor) {
        if (cursor == null) {
            return;
        }
        Log.d(TAG, "printCursor: data size" + cursor.getCount());
        while (cursor.moveToNext()) {
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                Log.d(TAG, "printCursor: " + cursor.getColumnName(i) + ":" + cursor.getString(i));
            }
        }
    }
}
