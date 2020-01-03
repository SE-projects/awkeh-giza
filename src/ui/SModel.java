package ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBconnection.dbconnection;


public class SModel {
    Connection connects;
    //private Connection connection;

    public SModel(){
        try {
            this.connects = dbconnection.getConnection();
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }
        if(this.connects == null){
            System.exit(1);
        }
    }

    /*public void openConnection(){
        try{
            connection = Connexion.getInstance().getConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

     */


    public boolean isDBconnected(){
        return this.connects != null;
    }
    //for central manager
    public boolean isCMloggedin(String name, String pass){
        PreparedStatement p1 = null;
        ResultSet r1 = null;

        String sq2 = "SELECT * FROM CMtable where CMname = ? and CMpass = ?";
        try{
            p1 = this.connects.prepareStatement(sq2);
            p1.setString(1, name);
            p1.setString(2, pass);
            r1 = p1.executeQuery();

            if (r1.next()) {
                return true;
            }
            return false;
        }catch(SQLException e){return false;}
        finally {
            if (r1 != null) {
                try {
                    r1.close();
                } catch (SQLException e) {
                }
            }
            if (p1 != null){
                try{
                    p1.close();
                } catch (SQLException e){
                }
            }
        }
    }
    // for storage manager
    public boolean isSMloggedin(String name, String pass, String branch) {
        PreparedStatement pr = null;
        ResultSet rs = null;
        // our query
        String sq1 = "SELECT * FROM SMtable where SMname = ? and pass = ? and branch = ? ";
        try {

            pr = this.connects.prepareStatement(sq1);
            pr.setString(1, name);
            pr.setString(2, pass);
            pr.setString(3, branch);
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
                } catch (SQLException e) {
                }
            }
            if (pr != null){
                try{
                    pr.close();
                } catch (SQLException e){
                }
            }
        }
    }
    public ResultSet getItemToBeBought(){
        PreparedStatement pr;
        ResultSet rs = null;
        // our query
        String sql = "SELECT * FROM ToBeBought";
        try {

            pr = this.connects.prepareStatement(sql);
            rs = pr.executeQuery();
            return rs;
        }catch(SQLException ex){

        }
        return rs;
    }


}
