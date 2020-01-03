package ui;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.dbconnection;

public class mainModel {
    Connection connect;


    public mainModel(){
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
    public boolean isDBconnected(){
        return this.connect != null;
    }

    public boolean isCustomerLoggedin(String name, String pass){
        PreparedStatement ps=null;
        ResultSet rs=null;


        String sql="SELECT * FROM registeredCustomer WHERE username=? AND password = ?";
        try{
            ps=this.connect.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pass);
            rs=ps.executeQuery();
            boolean bol1;

            if (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException exception){System.out.println("Error in Model"); return false;}
    }
    public boolean isShelfMLoggedin(String name, String pass){
        PreparedStatement ps=null;
        ResultSet rs=null;

        String sql="SELECT * FROM shelfManagers WHERE username=? AND password = ?";
        try{
            ps=this.connect.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pass);
            rs=ps.executeQuery();
            boolean bol1;

            if (rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException exception){System.out.println("Error in Model"); return false;}
    }


}
