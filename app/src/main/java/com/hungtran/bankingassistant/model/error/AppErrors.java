package com.hungtran.bankingassistant.model.error;

import com.google.gson.annotations.SerializedName;
import com.hungtran.bankingassistant.model.base.BaseResponse;

import java.util.List;

/**
 * Created by hungtd on 5/14/19.
 */

public class AppErrors extends BaseResponse {

    @SerializedName("data")
    private List<AppError> errors;


    public List<AppError> getErrors() {
        return errors;
    }

    public void setErrors(List<AppError> errors) {
        this.errors = errors;
    }
}
