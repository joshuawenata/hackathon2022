package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String userKey;
    private String userName;
    private String userNomor;
    private String userPass;

    public User(String userKey, String userName, String userNomor, String userPass) {
        this.userKey = userKey;
        this.userName = userName;
        this.userNomor = userNomor;
        this.userPass = userPass;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserNomor() {
        return userNomor;
    }

    public String getUserPass() {
        return userPass;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("nama", userName);
        data.put("nomor", userNomor);
        data.put("password", userPass);
        return data;
    }
}
