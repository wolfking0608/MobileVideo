package com.itheima.mvplayer.utils;

import com.itheima.mvplayer.model.LyricBean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LyricParser {
    public static final String TAG = "LyricParser";

    /**
     *
     * @param path Lyric file path
     * @return the parse result.
     */
    public static List<LyricBean> parseLyric(String path) {
        List<LyricBean> lyricBeanList = new ArrayList<LyricBean>();
        if (path == null) {
            return lyricBeanList;
        }

        File file = new File(path);
        if (!file.exists()) {
            file = new File(path.replace(".lrc", ".txt"));
            if (!file.exists()) {
                return lyricBeanList;
            }
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String readLine = reader.readLine();
            while (readLine != null) {
                List<LyricBean> lyrics = parseLyricLine(readLine);
                lyricBeanList.addAll(lyrics);
                readLine = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(lyricBeanList, new Comparator<LyricBean>() {
            @Override
            public int compare(LyricBean o1, LyricBean o2) {
                return o1.getTimestamp() - o2.getTimestamp();
            }
        });
        return lyricBeanList;
    }

    private static List<LyricBean> parseLyricLine(String readLine) {
        List<LyricBean> lyrics = new ArrayList<LyricBean>();
        //[01:22.04][02:35.04]寂寞的夜和谁说话
        String[] arrays = readLine.split("]");
        //[01:22.04    [02:35.04    寂寞的夜和谁说话
        for (int i = 0; i < arrays.length - 1; i++) {
            LyricBean lyricBean = new LyricBean();
            lyricBean.setTimestamp(parseTimeStamp(arrays[i]));
            lyricBean.setLyric(arrays[arrays.length - 1]);
            lyrics.add(lyricBean);
        }
        return lyrics;
    }

    private static int parseTimeStamp(String time) {
        //[01:22.04
        String[] array1 = time.split(":");
        //[01  22.04
        String minute = array1[0].substring(1);//01
        //22.04
        String[] array2 = array1[1].split("\\.");
        // 22 04
        String second  = array2[0];
        String millis = array2[1];

        return Integer.parseInt(minute) * 60 * 1000 + Integer.parseInt(second) * 1000 + Integer.parseInt(millis);
    }
}
