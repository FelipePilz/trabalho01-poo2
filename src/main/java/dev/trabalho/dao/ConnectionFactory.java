package dev.trabalho.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/imobiliaria?user=root";

    private static ConnectionFactory instance;

    public static ConnectionFactory getInstance() {
        if (instance == null) instance = new ConnectionFactory();
        return instance;
    }

    public Connection get() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
