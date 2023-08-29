package org.example.Model;

import org.example.Utils.Constant.UserRole;

public class User {
    String username, password;
    UserRole role;

    public User(){

    }
    public User(String username, String password, UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }
}
