package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static Connection conn;

    public Util() {

    }
    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "Crazymbappe5612");
            Statement statement = conn.createStatement();
            statement.executeUpdate("drop table users");
            return conn;
    } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
