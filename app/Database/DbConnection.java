package app.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public Connection getDbConnection() {
        Connection connection = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/primebca", "root", "");
            System.out.println("Connection is " + connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Exception occurred :: " + e.getMessage());
        }
        return connection;
    }
}
