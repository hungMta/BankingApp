package com.hungtran.bankingassistant.model.register;

import java.io.Serializable;

/**
 * Created by hungtd on 5/14/19.
 */

public class RegisterRequest implements Serializable {

    private String email;

    private String name;

    private String password;

    private String phone;

    private int otp;

    public RegisterRequest() {
    }

    public RegisterRequest(String email, String name,  String password, String phone, int otp) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.otp = otp;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }
}
