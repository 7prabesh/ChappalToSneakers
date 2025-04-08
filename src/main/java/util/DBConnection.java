package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String databaseName = "sneakercw"; // ✅ updated
    private final static String username = "root";
    private final static String password = "";
    private final static String jdbcURL = "jdbc:mysql://localhost:3306/sneakercw";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(jdbcURL, username, password);
        return conn;
    }

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DBConnection.getConnection();
            if (conn != null) {
                System.out.print("Connected Success");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
