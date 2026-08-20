package com.sunrisedental.service;

import com.sunrisedental.dao.UserDAO;

public class AuthenticationService {

    private final UserDAO userDAO;

    public AuthenticationService() {
        userDAO = new UserDAO();
    }

    public boolean login(
            String username,
            String password) {

        if (username == null ||
                username.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (password == null ||
                password.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Password is required."
            );
        }

        return userDAO.authenticate(
                username.trim(),
                password
        );
    }
}