package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    // ==========================================================
    // LOGIN
    // ==========================================================
    public User authenticate(String username, String password) {

        String sql
                = "SELECT user_id, username, full_name, role "
                + "FROM users "
                + "WHERE username = ? "
                + "AND password = ? "
                + "AND status = 'ACTIVE'";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("full_name"),
                        rs.getString("role")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ==========================================================
    // INSERT USER
    // ==========================================================
    public boolean insertUser(
            String username,
            String password,
            String fullName,
            String role) {

        String sql
                = "INSERT INTO users "
                + "(username, password, full_name, role) "
                + "VALUES (?, ?, ?, ?)";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    password
            );

            ps.setString(
                    3,
                    fullName
            );

            ps.setString(
                    4,
                    role
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // UPDATE USER
    // ==========================================================
    public boolean updateUser(
            int userId,
            String fullName,
            String role) {

        String sql
                = "UPDATE users "
                + "SET full_name = ?, "
                + "role = ? "
                + "WHERE user_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    fullName
            );

            ps.setString(
                    2,
                    role
            );

            ps.setInt(
                    3,
                    userId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // RESET PASSWORD
    // ==========================================================
    public boolean updatePassword(
            int userId,
            String password) {

        String sql
                = "UPDATE users "
                + "SET password = ? "
                + "WHERE user_id = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    password
            );

            ps.setInt(
                    2,
                    userId
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // GET ALL USERS
    // ==========================================================
    public List<User> getAllUsers() {

        List<User> users
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "user_id, "
                + "username, "
                + "full_name, "
                + "role "
                + "FROM users "
                + "ORDER BY full_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                User user
                        = new User();

                user.setUserId(
                        rs.getInt(
                                "user_id"
                        )
                );

                user.setUsername(
                        rs.getString(
                                "username"
                        )
                );

                user.setFullName(
                        rs.getString(
                                "full_name"
                        )
                );

                user.setRole(
                        rs.getString(
                                "role"
                        )
                );

                users.add(
                        user
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }

    // ==========================================================
    // SEARCH USERS
    // ==========================================================
    public List<User> searchUsers(
            String keyword) {

        List<User> users
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "user_id, "
                + "username, "
                + "full_name, "
                + "role "
                + "FROM users "
                + "WHERE username LIKE ? "
                + "OR full_name LIKE ? "
                + "OR role LIKE ? "
                + "ORDER BY full_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            String searchValue
                    = "%"
                    + keyword
                    + "%";

            ps.setString(
                    1,
                    searchValue
            );

            ps.setString(
                    2,
                    searchValue
            );

            ps.setString(
                    3,
                    searchValue
            );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                User user
                        = new User();

                user.setUserId(
                        rs.getInt(
                                "user_id"
                        )
                );

                user.setUsername(
                        rs.getString(
                                "username"
                        )
                );

                user.setFullName(
                        rs.getString(
                                "full_name"
                        )
                );

                user.setRole(
                        rs.getString(
                                "role"
                        )
                );

                users.add(
                        user
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }

    // ==========================================================
    // CHECK USERNAME
    // ==========================================================
    public boolean usernameExists(
            String username) {

        String sql
                = "SELECT user_id "
                + "FROM users "
                + "WHERE username = ?";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ps.setString(
                    1,
                    username
            );

            ResultSet rs
                    = ps.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================================
    // USER COUNT
    // ==========================================================
    public int countAllUsers() {

        String sql
                = "SELECT COUNT(*) "
                + "FROM users";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(sql);

            ResultSet rs
                    = ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(
                        1
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return 0;
    }

    public List<User> getDentistUsers() {

        List<User> users
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "user_id, "
                + "username, "
                + "full_name, "
                + "role "
                + "FROM users "
                + "WHERE role = 'DENTIST' "
                + "ORDER BY full_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                User user
                        = new User();

                user.setUserId(
                        rs.getInt(
                                "user_id"
                        )
                );

                user.setUsername(
                        rs.getString(
                                "username"
                        )
                );

                user.setFullName(
                        rs.getString(
                                "full_name"
                        )
                );

                user.setRole(
                        rs.getString(
                                "role"
                        )
                );

                users.add(
                        user
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }

    public List<User> getAvailableDentistUsers() {

        List<User> users
                = new ArrayList<>();

        String sql
                = "SELECT "
                + "u.user_id, "
                + "u.username, "
                + "u.full_name, "
                + "u.role "
                + "FROM users u "
                + "LEFT JOIN dentists d "
                + "ON u.user_id = d.user_id "
                + "WHERE u.role = 'DENTIST' "
                + "AND d.user_id IS NULL "
                + "ORDER BY u.full_name ASC";

        try {

            Connection con
                    = DatabaseConnection.getConnection();

            PreparedStatement ps
                    = con.prepareStatement(
                            sql
                    );

            ResultSet rs
                    = ps.executeQuery();

            while (rs.next()) {

                User user
                        = new User();

                user.setUserId(
                        rs.getInt(
                                "user_id"
                        )
                );

                user.setUsername(
                        rs.getString(
                                "username"
                        )
                );

                user.setFullName(
                        rs.getString(
                                "full_name"
                        )
                );

                user.setRole(
                        rs.getString(
                                "role"
                        )
                );

                users.add(
                        user
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return users;
    }
}
