package com.jilong.search.domain.user;

/**
 * @author jilong.qiu
 * @date 2017/5/9.
 */
public class UserRegister {

    private String phoneNumber;
    private String validCode;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }
}
