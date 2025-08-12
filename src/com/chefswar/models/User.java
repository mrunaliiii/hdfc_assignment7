package com.chefswar.models;

import com.chefswar.enums.Role;

public abstract class User {
    protected final String username;
    protected final Role role;

    protected User(String username, Role role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }
}