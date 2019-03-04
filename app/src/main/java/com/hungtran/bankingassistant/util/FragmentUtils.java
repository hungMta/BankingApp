package com.hungtran.bankingassistant.util;

public class FragmentUtils {
    public static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }
}
