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

    public static String mapError(String code) {
        switch (code) {
            case "E50001":
                break;
            case "E00001":
                return "Email đã tồn tại";
        }
        return "Error";
    }

    public static AppErrors mapAppErrors(Throwable e) {
        ResponseBody responseBody = ((HttpException) e).response().errorBody();
        Gson gson = new Gson();
        assert responseBody != null;
        AppErrors appErrors = null;
        try {
            appErrors = gson.fromJson(responseBody.string(), AppErrors.class);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return appErrors;
    }

    public static String mapFirstError(AppErrors appErrors) {
        List<String> listError = new ArrayList<>();

        if (appErrors != null && appErrors.getErrors() != null) {
            for (AppError error : appErrors.getErrors()
                    ) {
                listError.add(AppError.mapError(error.getCode()));
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
}
