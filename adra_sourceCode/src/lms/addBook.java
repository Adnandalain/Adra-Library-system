
package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class addBook {
     JFrame frame;
    public addBook(JFrame frame){
    this.frame=frame;
        JLayeredPane addingbook=new JLayeredPane();
       addingbook.setBounds(0,0,800,600);
        
        //background 
        ImageIcon addingbookBG=new ImageIcon("addbook.jpg");
        JLabel addingbookBGL=new JLabel(addingbookBG);
        addingbookBGL.setBounds(0,0,800,600);
       
         // Jtext field
         Color myColor = Color.decode("#f6f6f6");
         
        JTextField  title = new JTextField(27);
        title.setBounds(195,303,158,25);
        title.setBorder(null);
        title.setBackground(myColor);
        
        JTextField  author = new JTextField(27);
        author.setBounds(195,343,158,25);
        author.setBorder(null);
        author.setBackground(myColor);
        
         JTextField  productionyear = new JTextField(27);
        productionyear.setBounds(195,383,158,25);
        productionyear.setBorder(null);
        productionyear.setBackground(myColor);
        
        JTextField  numofcopies = new JTextField(27);
       numofcopies.setBounds(195,423,158,25);
       numofcopies.setBorder(null);
        numofcopies.setBackground(myColor);
        
        //button
        ImageIcon save = new ImageIcon("save.png");
     JButton savebtn=new JButton(save);
     savebtn.setBorder(null);
     savebtn.setContentAreaFilled(false);
     savebtn.setBounds(224,471,93,24);
     savebtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
            String Title = title.getText();
            String Author = author.getText();
            String Productionyear = productionyear.getText();
            String Numofcopies = numofcopies.getText();
              Connection con=DBConnection.DBcon();
     
      try{
           Statement st = con.createStatement();
           String query="insert into book values ('"+Productionyear+"','"+Title+"','"+Author+"','"+Numofcopies+"')";
           st.executeUpdate(query);
          System.out.println("done yasta");
       }
       catch(Exception e){
           System.out.println("error");
       }  
          }
     });
        
     ImageIcon home = new ImageIcon("homebtn.png");
     JButton homebtn=new JButton(home);
     homebtn.setBorder(null);
     homebtn.setContentAreaFilled(false);
     homebtn.setBounds(31,49,83,28);
    
     homebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               frame.remove(addingbook);
               new adminPage(frame);
            }
        });
     
        addingbook.add(homebtn);
        addingbook.add(savebtn);
        addingbook.add(title);
        addingbook.add(author);
        addingbook.add(productionyear);
        addingbook.add(numofcopies);
        addingbook.add(addingbookBGL);
        frame.add(addingbook);
        frame.repaint();
    }
}
