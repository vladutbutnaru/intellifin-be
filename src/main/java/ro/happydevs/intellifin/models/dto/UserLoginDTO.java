package ro.happydevs.intellifin.models.dto;

import ro.happydevs.intellifin.models.User;

import java.util.ArrayList;

public class UserLoginDTO {

    private String token;
    private String message;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

