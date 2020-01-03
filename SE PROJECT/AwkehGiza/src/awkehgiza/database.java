/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//KALKIDAN TAMENE    ATR/8747/09
//TINBIT MESIFIN     ATR/5596/09
//http://localhost/phpmyadmin/db_structure.php?server=1&db=awkeh_giza&token=96c3bd9f3be870c722a35c6a4d6ab154

package awkehgiza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author user
 */
public class database {
     Connection con;
    Statement stmt;
    ResultSet rs;

     public void connect()
    {
        try
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection(  
            "jdbc:mysql://localhost:3306/awkeh_giza?useSSL=false&autoReconnect=true","root","");
            stmt=con.createStatement(); 
            
        }
        catch(Exception e)
        { 
            System.out.println(e);
            //JOptionPane.showMessageDialog(this,"connection error");
        }
    }
    public void disconnect()
    {
        try
        {
           con.close(); 
        }
        catch(Exception e)
        {}

    }
}
