package com.hungtran.bankingassistant.model.error;

import com.google.gson.Gson;
import com.hungtran.bankingassistant.util.Constant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by hungtd on 5/14/19.
 */

public class AppError {

    private String code;

    private String field;

    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static String mapErrorCode(String code) {
        switch (code) {
            case "E50001":
                break;
            case "E00001":
                return "Email đã tồn tại";
            case "E00002":
                return "Mã OTP không hợp lệ hoặc đã hết hạn";
            case "E00004":
                return "Tài khoản nhận không chính xác, vui lòng kiểm tra lại";
            case "E00008":
                return "Email không tồn tại";
        }
        return Constant.ERROR_UNKNOWN;
    }

    public static AppErrors mapAppErrors(Throwable e) {
        AppErrors appErrors = null;
        try {
            String error = ((HttpException) e).response().errorBody().string().toString();
            Gson gson = new Gson();
            appErrors = gson.fromJson(error, AppErrors.class);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
//        ResponseBody responseBody = ((HttpException) e).response().errorBody();
        Gson gson = new Gson();
//        assert responseBody != null;

//        try {
//            appErrors = gson.fromJson(responseBody.string(), AppErrors.class);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        return appErrors;
    }

    public static String mapFirstError(AppErrors appErrors) {
        List<String> listError = new ArrayList<>();

        if (appErrors != null && appErrors.getErrors() != null) {
            for (AppError error : appErrors.getErrors()
                    ) {
                listError.add(AppError.mapErrorCode(error.getCode()));
            }
        }

        if (listError.size() == 0) {
            listError.add(Constant.ERROR_UNKNOWN);
        }
        return listError.get(0);
    }

    public static String mapError(Throwable e) {
        AppErrors appErrors = mapAppErrors(e);
        String firstError = mapFirstError(appErrors);
        return firstError;
    }

    public static String mapError(String error) {
        AppErrors appErrors = mapAppErrors(error);
        String firstError = mapFirstError(appErrors);
        return firstError;
    }

    public static AppErrors mapAppErrors(String error) {
        AppErrors appErrors = null;
        Gson gson = new Gson();
        appErrors = gson.fromJson(error, AppErrors.class);
        return appErrors;
    }

//    public static String mapError(ResponseBody responseBody){
//        Gson gson = new Gson();
//        assert responseBody != null;
//        AppErrors appErrors = null;
//        try {
//            appErrors = gson.fromJson(responseBody.string(), AppErrors.class);
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        String firstError = mapFirstError(appErrors);
//        return firstError;
//    }
}
