package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Helper class for the database
 * @author Benjamin Bowen
 */
public class DBHelper
{
    public static Connection connect()
    {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost/studentregistration", "root", "123");
            return conn;

        } catch (ClassNotFoundException | SQLException ex) {

            System.err.println("DB connection: " + ex.getMessage());
            return null;

        } catch (Exception ex) {

            System.err.println("ERROR: " + ex.getMessage());
            return null;

        }
    }
}
