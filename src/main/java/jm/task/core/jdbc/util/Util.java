package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://localhost:3306";
    private static String dbUsername = "root";
    private static String dbPassword = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("connection established");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection ERROR");
        }
        return connection;

    }

}
