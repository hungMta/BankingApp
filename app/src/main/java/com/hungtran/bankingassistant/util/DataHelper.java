package com.hungtran.bankingassistant.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

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
                String sub = "" + arrStr[0].substring(i, i + 3);
                list.add(0, sub);
                if (i != 0) {
                    list.add(0, ".");
                }
            }
            if (i == 0 && (index + 1) % 3 != 0) {
                list.add(0, arrStr[0].substring(i, index % 3 + 1));
            }
            index += 1;
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        if (arrStr[1].equals("0")) {

        } else {
            result += "," + arrStr[1];
        }

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
                String sub = "" + str.substring(i, i + 3);
                list.add(0, sub);
                if (i != 0) {
                    list.add(0, ".");
                }
            }
            if (i == 0 && (index + 1) % 3 != 0) {
                list.add(0, str.substring(i, index % 3 + 1));
            }
            index += 1;
        }
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            result += list.get(i);
        }
        return result;
    }

    // term should be 12 month
    // timeSaving should be month
    public static List<Long> calculateInterestRate(long initialMoney, double interestRate, int term, int timeSaving, int typeWithdraw) {
        if (timeSaving < term || term == 0) return new ArrayList<>();
        List<Long> listInterestRate = new ArrayList<>();
        while (timeSaving / term > 0) {
            long interestMoney = (long) (initialMoney * (interestRate / 100 / 12));
            listInterestRate.add(interestMoney);
            timeSaving -= term;
            if (typeWithdraw == Constant.TYPE_SAVING_WITHDRAW) {

            } else {
                initialMoney += interestMoney;
            }
        }
        return listInterestRate;
    }

    public static long calculateSavingInterestRate(long initalMoney, double interestRate, int term, String createDateString) {
        long totalMoney = initalMoney;
        if (!isDueDateSaving(createDateString, term)) { // no term
            int diffDays = getDiffDays(createDateString);
            long interestMoney = (long) ((initalMoney * interestRate / 100 / (term * 30)) * diffDays);
            totalMoney += interestMoney;
        } else { // with term
            long interestMoney = (long) ((initalMoney * interestRate / 100 / 12) * term);
            totalMoney += interestMoney;
        }
        return totalMoney;
    }

    public static Date getDateFromString(String string) {
        if (string == null) return null;
        try {
            String[] subArr = string.split("\\.");
            if (subArr.length > 0) {
                Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH).parse(subArr[0]);
                return date;
            } else {
                return null;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateFromString(String string, String format) {
        if (string == null) return null;
        if (format == null) {
            return getDateFromString(string);
        }
        try {
            Date date = new SimpleDateFormat(format).parse(string);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String getTime(String time, int type) {
        Date date = DataHelper.getDateFromString(time);
        if (date == null) return "";
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (type == 0) {
            String[] splitTime = getHourMinTime(time);
            int hour = 0;
            int min = 0;
            if (splitTime.length > 2) {
                hour = Integer.parseInt(splitTime[0]);
                min = Integer.parseInt(splitTime[1]);
            }

            String _hour = hour + "";
            String _min = min + "";
            if (hour < 10) {
                _hour = "0" + hour;
            }
            if (min < 10) {
                _min = "0" + min;
            }
            return _hour + ":" + _min;
        } else {
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DATE);
            String _month = month + "";
            String _day = day + "";

            if (month < 10) {
                _month = "0" + month;
            }

            if (day < 10) {
                _day = "0" + day;
            }

            return "" + _day + "/" + _month;
        }
    }


    private static String[] getHourMinTime(String strDate) {
        String[] split = strDate.split(" ");
        if (split.length >= 2) {
            String stringTime = split[1];
            String[] splitTime = stringTime.split(":");
            return splitTime;
        }
        return new String[]{"00", "00", "00"};
    }


    private int getTimeSaving(Date createDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        Calendar createCal = Calendar.getInstance();
        calendar.setTime(createDate);

        int createMonth = createCal.get(Calendar.MONTH);
        int createYear = createCal.get(Calendar.YEAR);


        int year = currentYear - createYear;
        int month = currentMonth - createMonth + year * 12;

        return month;
    }

    public static String getTimeFormatFromInterval(long interval) {
        String string = "";
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(interval);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH) + 1;
        int year = c.get(Calendar.YEAR);
        string = day + "/" + month + "/" + year;
        return string;
    }

    // delete all non-digit in a String
    public static String deletAllNonDigit(String input) {
        return input.replaceAll(",", "");
    }

    public static int getDiffDays(String createDate) {
        Date date = DataHelper.getDateFromString(createDate, Constant.DATE_FORMAT);
        Date currentDate = new Date();

        long diffMillies = Math.abs(currentDate.getTime() - date.getTime());
        long diff = TimeUnit.DAYS.convert(diffMillies, TimeUnit.MILLISECONDS);

        return (int) diff;
    }

    public static boolean isDueDateSaving(String createDateString, int term) {
        Date createDate = DataHelper.getDateFromString(createDateString, Constant.DATE_FORMAT);
        Date currentDate = new Date();

        Calendar createCalendar = Calendar.getInstance();
        createCalendar.setTime(createDate);
        Calendar currentCalendar = Calendar.getInstance();
        currentCalendar.setTime(currentDate);

        createCalendar.add(Calendar.MONTH, term);

        int compare = currentDate.compareTo(createCalendar.getTime());
        if (compare <= 0) {
            return false;
        } else {
            return true;
        }
    }
}
