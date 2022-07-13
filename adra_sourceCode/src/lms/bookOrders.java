
package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class bookOrders {
    JFrame frame;
    public bookOrders(JFrame frame){
      this.frame=frame;
        JLayeredPane bookorders=new JLayeredPane();
       bookorders.setBounds(0,0,800,600);
        
        //background 
        ImageIcon bookordersBG=new ImageIcon("bookorders.jpg");
        JLabel bookordersBGL=new JLabel(bookordersBG);
        bookordersBGL.setBounds(0,0,800,600);
        
        // Jtext field
        Color myColor = Color.decode("#f6f6f6");
        
        JTextField  title = new JTextField(27);
        title.setBounds(191,333,158,25);
        title.setBorder(null);
        title.setBackground(myColor);
        
        JTextField  author = new JTextField(27);
        author.setBounds(191,373,158,25);
        author.setBorder(null);
        author.setBackground(myColor);
        
        //button
       ImageIcon home = new ImageIcon("homebtn.png");
     JButton homebtn=new JButton(home);
     homebtn.setBorder(null);
     homebtn.setContentAreaFilled(false);
     homebtn.setBounds(31,49,83,28);
    
     homebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(bookorders);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
     
     ImageIcon order = new ImageIcon("orderbtn.png");
     JButton orderbtn=new JButton(order);
     orderbtn.setBorder(null);
     orderbtn.setContentAreaFilled(false);
     orderbtn.setBounds(205,421,123,41);
    
     orderbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.remove(bookorders);
                Connection con=DBConnection.DBcon();
                String storedTitle=title.getText();
        String storedAuthor=author.getText();
        String name=login.userName;
      try{
           Statement st = con.createStatement();
           String query="insert into bookorders values ('"+storedTitle+"','"+storedAuthor+"','"+name+"')";
           st.executeUpdate(query);
       }
       catch(Exception e){
           System.out.println("error");
       }
      JOptionPane.showMessageDialog(frame,"Done");
      new userPage(frame);
            }
            
            });
     
     
        
        
        bookorders.add(title);
        bookorders.add(author);
        bookorders.add(homebtn);
        bookorders.add(orderbtn);
        bookorders.add(bookordersBGL);
        frame.add(bookorders);
        frame.repaint();
        
    }
}
