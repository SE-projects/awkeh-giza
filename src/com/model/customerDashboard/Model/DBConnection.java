/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customerDashboard.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DBConnection {
    //out connect method 
    private static Connection conn=null;
    private static String driver="com.mysql.cj.jdbc.Driver";
    public static String dbName="";
    public static String userName="";
    public static String password="";
    public static boolean connect  (){
        boolean isConnect=false;
        try{
            //this method will connect to db
            Class.forName(driver);
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//            conn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ dbName + userName  + password );
           conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket", "root", "");
            isConnect=true;
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("Cannot connect to database");
        }
        return isConnect;
    }
    //fetch result from db
    public static ResultSet select(String query){
        ResultSet result=null;
        //first we create statements
        try{
        Statement state=(Statement)conn.createStatement();
        result=state.executeQuery(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
        
    }
    //displaying select query in tabular form
    public static void  showSelect(ResultSet result){
        if(result != null){
            try{
            ResultSetMetaData metaData=(ResultSetMetaData)result.getMetaData();
            int noColumns=metaData.getColumnCount();
            for(int i=0;i < noColumns;i++ ){
                System.out.println(metaData.getColumnName(i+1)+ "  ");
            }
            System.out.println();
            //display the result
            while(result.next()){
                for(int i=0;i < noColumns;i++ ){
                    System.out.println(result.getString(i+1) + "  ");
                     System.out.println();
            }
            }
            }catch(Exception ex){}
        }
    }
    // for any query except select
    //for alter ,insert,delete,update,create               
    public static int query(String query){
        //first creating the statement
        int result=-1;
        try{
        Statement state=(Statement)conn.createStatement();
        //result will be no of rows that will be updates because of query
        result=state.executeUpdate(query);
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
  
    }
    public static void disConnect(){
        try{
            conn.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    
    
    }
    
    
    
}
