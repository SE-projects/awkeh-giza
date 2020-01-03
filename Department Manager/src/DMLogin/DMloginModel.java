package DMLogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import dbutil.dbconnection;

public class DMloginModel {
    Connection connection;

    public DMloginModel() {

        try {

            this.connection = dbconnection.getConnection();
        }catch (SQLException ex){

            ex.printStackTrace();

        }

        if (this.connection == null) {
            System.exit( 1);
        }

    }

    public boolean isDatabaseConnected(){
        return this.connection !=null;
    }

    public boolean isDMlogin(String user, String pass, String opt)throws Exception{

        PreparedStatement pr = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM DMlogin where username = ? and password = ? and Branch = ?";

        try {

            pr = this.connection.prepareStatement(sql);
            pr.setString(1, user);
            pr.setString(2, pass);
            pr.setString(3, opt);

            rs = pr.executeQuery();

            boolean bull1;

            if (rs.next()) {
                return true;

            }

            return false;

        }
        catch (SQLException ex){

            return false;
        }

        finally {
            {
                pr.close();
                rs.close();
            }
        }



        }

    }



