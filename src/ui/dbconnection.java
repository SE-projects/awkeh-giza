package ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbconnection {
    private static final String SQconn = "jdbc:sqlite:C:\\Users\\Alazar GT\\Documents\\GitHub\\awkeh-giza\\superMarket.sqlite";

    public static Connection getConnection()throws SQLException{
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQconn);
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
