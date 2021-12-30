package com.albo.model.dao.jdbc;

import com.albo.model.dao.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements ConnectionFactory {
    private static final String URL = "jdbc:postgresql://192.168.99.100:5432/poll";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
