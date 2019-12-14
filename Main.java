/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradleproject1;

/**
 *
 * @author SUR
 */
public class Main {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String args[])
    {
        try{
       EmployeeInterface F = new EmployeeInterface();
        F.setSize(800, 800);
        F.show();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }    

}
   
