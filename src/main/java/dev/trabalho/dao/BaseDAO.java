package dev.trabalho.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDAO {

    protected Connection con() throws SQLException {
        return ConnectionFactory.getInstance().get();
    }
}
