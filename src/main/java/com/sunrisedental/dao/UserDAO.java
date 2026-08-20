package com.sunrisedental.dao;

import com.sunrisedental.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    public boolean authenticate(
            String username,
            String password) {

        String sql =
                "SELECT * FROM users "
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

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}