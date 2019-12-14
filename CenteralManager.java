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

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
public class CenteralManager extends Frame implements ActionListener {

     
    JButton[] Btn;
     
    public CenteralManager()
    {    
        setBackground(Color.CYAN);
        JPanel panelDisplayBtn = new JPanel(new FlowLayout());
        Btn = new JButton[4];
        Btn[0] = new JButton("View Product");
        Btn[0].setBounds(50,100,95,30);
        Btn[1]  = new JButton("Requested Product List");
        Btn[1].setBounds(50,100,95,30);
        Btn[2]  = new JButton("Purchase List");
        Btn[3] = new JButton("Back");
        Btn[3].setBounds(50,100,95,30);
        Btn[3].setBackground(Color.red);
        
        Btn[0].addActionListener(this);
        Btn[1].addActionListener(this);
        Btn[2].addActionListener(this);
        Btn[3].addActionListener(this);
        
        panelDisplayBtn.setBackground(Color.WHITE);
        panelDisplayBtn.setBounds(800, 600, 400, 400);
        panelDisplayBtn.add(Btn[0]);
        panelDisplayBtn.add(Btn[1]);
        panelDisplayBtn.add(Btn[2]);
        panelDisplayBtn.add(Btn[3]);
       
        
      add(panelDisplayBtn);
      setTitle("Centeral Manager");
      pack();
      setSize(400, 400);            
      setVisible(true);   
        
     
   }

    @Override
   public void actionPerformed(ActionEvent ae)
 {
        try
        {
            if(ae.getSource()==Btn[3])
            {
               new EmployeeInterface().setVisible(true);
            }
        }
        catch(Exception e)
        {
            System.out.println("The Exception Is : " +e);
        }
    }

    }
