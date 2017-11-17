package com.itheima.mvplayer.model;

public class LyricBean {
    public static final String TAG = "LyricBean";

    private int timestamp;
    private String lyric;

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }
}
