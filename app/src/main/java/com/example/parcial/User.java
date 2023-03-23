package com.example.parcial;

import java.io.Serializable;

public class User implements Serializable {
    private final String username;
    private final String password;
    private final int id;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }


    public String getPassword() {
        return password;
    }
}
