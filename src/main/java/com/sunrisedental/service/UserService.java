package com.sunrisedental.service;

import com.sunrisedental.dao.UserDAO;
import com.sunrisedental.model.User;

import java.util.List;

public class UserService {

    private final UserDAO userDAO;

    public UserService() {

        userDAO =
                new UserDAO();
    }

    // ==========================================================
    // ADD USER
    // ==========================================================

    public boolean addUser(
            String username,
            String password,
            String fullName,
            String role) {

        validateUsername(
                username
        );

        validateFullName(
                fullName
        );

        validatePassword(
                password
        );

        validateRole(
                role
        );

        username =
                username.trim();

        if (userDAO.usernameExists(
                username)) {

            throw new IllegalArgumentException(
                    "Username already exists."
            );
        }

        return userDAO
                .insertUser(
                        username,
                        password,
                        fullName.trim(),
                        role
                );
    }

    // ==========================================================
    // UPDATE USER
    // ==========================================================

    public boolean updateUser(
            int userId,
            String fullName,
            String role) {

        if (userId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid user."
            );
        }

        validateFullName(
                fullName
        );

        validateRole(
                role
        );

        return userDAO
                .updateUser(
                        userId,
                        fullName.trim(),
                        role
                );
    }

    // ==========================================================
    // RESET PASSWORD
    // ==========================================================

    public boolean resetPassword(
            int userId,
            String password) {

        if (userId <= 0) {

            throw new IllegalArgumentException(
                    "Invalid user."
            );
        }

        validatePassword(
                password
        );

        return userDAO
                .updatePassword(
                        userId,
                        password
                );
    }

    // ==========================================================
    // GET USERS
    // ==========================================================

    public List<User> getAllUsers() {

        return userDAO
                .getAllUsers();
    }

    public List<User> searchUsers(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {

            return getAllUsers();
        }

        return userDAO
                .searchUsers(
                        keyword.trim()
                );
    }

    // ==========================================================
    // TOTAL USERS
    // ==========================================================

    public int getTotalUserCount() {

        return userDAO
                .countAllUsers();
    }

    // ==========================================================
    // VALIDATION
    // ==========================================================

    private void validateUsername(
            String username) {

        if (username == null
                || username.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (username.trim().length() < 4) {

            throw new IllegalArgumentException(
                    "Username must contain at least 4 characters."
            );
        }
    }

    private void validatePassword(
            String password) {

        if (password == null
                || password.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Password is required."
            );
        }

        if (password.length() < 6) {

            throw new IllegalArgumentException(
                    "Password must contain at least 6 characters."
            );
        }
    }

    private void validateFullName(
            String fullName) {

        if (fullName == null
                || fullName.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Full name is required."
            );
        }
    }

    private void validateRole(
            String role) {

        if (role == null
                || (!role.equalsIgnoreCase(
                        "ADMIN")
                && !role.equalsIgnoreCase(
                        "RECEPTIONIST")
                && !role.equalsIgnoreCase(
                        "DENTIST"))) {

            throw new IllegalArgumentException(
                    "Invalid user role."
            );
        }
    }
}