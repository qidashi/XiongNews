package com.xtm.qidashi.mingnews.net;

/**
 * @author qidashi
 * @version 1.0
 * @date 2019/5/14
 * @description:
 */
public class JNI {
    static {
        System.loadLibrary("native-lib");
    }
    public static native String stringFromJNI();
}
