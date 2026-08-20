package com.sunrisedental.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/sunrise_dental";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection connection;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {

            connection = DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );
        }

        return connection;
    }
}