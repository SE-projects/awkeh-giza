package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBconnection.dbconnection;
import com.model.Connexion;


public class loginModel {
    Connection connect;
    private Connection connection;

    public loginModel(){
        try {
            this.connect = dbconnection.getConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connect == null){
            System.exit(1);
        }
    }

    public void openConnection(){
            connection = Connexion.getInstance().getConnection();


    }



    public boolean isDBconnected(){
        return this.connect != null;
    }
    public boolean isLoggedin(String user, String pass, String profile) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        // our query
        String sql = "SELECT * FROM Login where username = ? and password = ? and profile = ? ";
        try {

            pr = this.connect.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, profile);

            rs = pr.executeQuery();

            boolean bol1;

            if (rs.next()) {
                return true;
            }
            return false;

        }
        catch (SQLException ex ) {
            return false;
        }
        finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {}
            }
            if (pr != null){
                try{
                    pr.close();
                } catch (SQLException e){}
            }
        }
    }
}
