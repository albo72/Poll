package com.albo.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {
    Connection getConnection() throws ClassNotFoundException, SQLException;
}
