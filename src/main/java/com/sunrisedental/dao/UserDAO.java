package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;
import com.sunrisedental.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public User authenticate(String username, String password) {

        String sql =
                "SELECT user_id, username, full_name, role "
                + "FROM users "
                + "WHERE username = ? "
                + "AND password = ? "
                + "AND status = 'ACTIVE'";

        try {

            Connection con =
                    DatabaseConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs =
                    ps.executeQuery();

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
}