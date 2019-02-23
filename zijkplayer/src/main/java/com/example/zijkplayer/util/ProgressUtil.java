package com.example.zijkplayer.util;

import android.text.TextUtils;

import java.util.LinkedHashMap;

/**
 * 创建作者:zhuguoqing
 * 创建日期:2019/2/23
 * 作用: 进度条相关
 */

public class ProgressUtil {

    private static LinkedHashMap<Integer, Long> progressMap = new LinkedHashMap<>();

    public static void saveProgress(String url, long progress) {
        if (TextUtils.isEmpty(url)) return;
        progressMap.put(url.hashCode(), progress);
    }

    public static long getSavedProgress(String url) {
        return TextUtils.isEmpty(url) ? 0 : progressMap.containsKey(url.hashCode()) ? progressMap.get(url.hashCode()) : 0;
    }

    /**
     * clear all progress
     */
    public static void clearAllSavedProgress() {
        progressMap.clear();
    }

    /**
     * remove progress by url
     */
    public static void clearSavedProgressByUrl(String url) {
        progressMap.remove(url.hashCode());
    }

}
