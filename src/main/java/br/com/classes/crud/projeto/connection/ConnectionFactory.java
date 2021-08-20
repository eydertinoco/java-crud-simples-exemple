package br.com.classes.crud.projeto.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String URL = "jdbc:postgresql://localhost:5432/crud-exemplo";
    private final String USER = "postgres";
    private final String PASSWORD = "postgres";
    private final String DRIVER = "org.postgresql.Driver";


    public Connection returnConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}

//


