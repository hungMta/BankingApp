package com.hungtran.bankingassistant.util;

import java.util.ArrayList;
import java.util.List;

public class DataHelper {


    public static String formatMoney(double money) {
        if (money == 0) return "0";
        String str = money + "";
        String[] arrStr = str.split("\\.");
        if (arrStr.length == 1) return arrStr[0];

        int index = 0;
        List<String> list = new ArrayList<>();
        for (int i = arrStr[0].length() - 1; i >= 0; i--) {
            if ((index + 1) % 3 == 0) {
                String sub = "" + arrStr[0].substring(i,i + 3);
                list.add(0, sub);
                if (i != 0) {
                    list.add(0, ".");
                }
            }
            if (i == 0 && (index + 1) % 3 != 0) {
                list.add(0, arrStr[0].substring(i,index % 3 + 1));
            }
            index += 1;
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        result += "," + arrStr[1];
        return result;
    }
}
