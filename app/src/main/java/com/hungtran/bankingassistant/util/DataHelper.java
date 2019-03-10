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

    public static String formatMoney(long money) {
        if (money == 0) return "0";
        String str = String.format(String.valueOf(money));
//        String[] arrStr = str.split("\\.");
//        if (arrStr.length == 1) return arrStr[0];
        int index = 0;
        List<String> list = new ArrayList<>();
        for (int i = str.length() - 1; i >= 0; i--) {
            if ((index + 1) % 3 == 0) {
                String sub = "" + str.substring(i,i + 3);
                list.add(0, sub);
                if (i != 0) {
                    list.add(0, ".");
                }
            }
            if (i == 0 && (index + 1) % 3 != 0) {
                list.add(0, str.substring(i,index % 3 + 1));
            }
            index += 1;
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        return result;
    }

    public static List<Long> calculateInterestRate(long initialMoney, double interestRate, int term, int timeSaving, int typeWithdraw){
        if (timeSaving < term || term == 0) return new ArrayList<>();
        List<Long> listInterestRate = new ArrayList<>();
        while (timeSaving/term > 0) {
            long interestMoney =  (long)( initialMoney * (interestRate / 100));
            listInterestRate.add(interestMoney);
            timeSaving -= term;
            if (typeWithdraw == Constant.TYPE_SAVING_WITHDRAW) {

            } else {
                initialMoney += interestMoney;
            }
        }
       return listInterestRate;
    }
}
