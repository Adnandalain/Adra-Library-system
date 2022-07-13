
package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class adminOrders {
    JFrame frame;
    String orders[];
    int numoforders;
    int counter;
    public adminOrders(JFrame frame){
      this.frame=frame;
      orders= new String[150];
      numoforders=0;
      counter=0;
        JLayeredPane adminorders=new JLayeredPane();
       adminorders.setBounds(0,0,800,600);
       
       //background 
        ImageIcon adminordersBG=new ImageIcon("adminorders.jpg");
        JLabel adminordersBGL=new JLabel(adminordersBG);
        adminordersBGL.setBounds(0,0,800,600);
        
       
       //getting data from database
       Connection con=DBConnection.DBcon();
                try{
           Statement st = con.createStatement();
           String query="select * from bookorders";
          ResultSet result=st.executeQuery(query);

          while(result.next()){
             orders[numoforders]=result.getString("Title");
              orders[numoforders+1]=result.getString("Author");
              orders[numoforders+2]=result.getString("Username");
             numoforders+=3;
          }
       }
       catch(Exception e){
           System.out.println("error");
       }
        
        // Jtext field
        Color myColor = Color.decode("#f6f6f6");
        System.out.println(orders[counter]);
        System.out.println(counter);
        JTextField  title = new JTextField(orders[counter],27);
        title.setBounds(191,333,158,25);
        title.setBorder(null);
        title.setBackground(myColor);
        
        JTextField  author = new JTextField(orders[counter+1],27);
        author.setBounds(191,373,158,25);
        author.setBorder(null);
        author.setBackground(myColor);
        
        JTextField  name = new JTextField(orders[counter+2],27);
        name.setBounds(191,293,158,25);
        name.setBorder(null);
        name.setBackground(myColor);
        
        //button
        ImageIcon home = new ImageIcon("homebtn.png");
     JButton homebtn=new JButton(home);
     homebtn.setBorder(null);
     homebtn.setContentAreaFilled(false);
     homebtn.setBounds(31,49,83,28);
    
     homebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(adminorders);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
     ImageIcon next = new ImageIcon("greennextbtn.png");
     JButton nextbtn=new JButton(next);
     
     ImageIcon previous = new ImageIcon("greenprevbtn.png");
     JButton previousbtn=new JButton(previous);
     previousbtn.setBorder(null);
     previousbtn.setContentAreaFilled(false);
     previousbtn.setBounds(464,482,93,24);
     previousbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              counter-=3;
              name.setText(orders[counter+2]);
              title.setText(orders[counter]);
              author.setText(orders[counter+1]);
               adminorders.remove(nextbtn);
               adminorders.remove(adminordersBGL);
               adminorders.add(nextbtn);
              adminorders.add(adminordersBGL);
 
              if(counter<3){
              adminorders.remove(previousbtn);
              }
              frame.repaint();
          }
     });
     
     ImageIcon add = new ImageIcon("greenaddbtn.png");
     JButton addbtn=new JButton(add);
     addbtn.setBorder(null);
     addbtn.setContentAreaFilled(false);
     addbtn.setBounds(219,421,93,24);
     addbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              
            frame.remove(adminorders);
            new addBook(frame);
              
              frame.repaint();
          }
     });
     
     nextbtn.setBorder(null);
     nextbtn.setContentAreaFilled(false);
     nextbtn.setBounds(571,482,93,24);
     nextbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              counter+=3;
              name.setText(orders[counter+2]);
              title.setText(orders[counter]);
              author.setText(orders[counter+1]);
              if(counter+6>numoforders){
                  adminorders.remove(nextbtn);
                  frame.repaint();
              }
                adminorders.remove(previousbtn);
                adminorders.remove(adminordersBGL);
                adminorders.add(previousbtn);
                
                
                adminorders.add(adminordersBGL);
                  frame.repaint(); 
             
          }
     });
     
     
     adminorders.add(homebtn);
        if(numoforders>3){
        adminorders.add(nextbtn);
        }
        adminorders.add(addbtn);
        adminorders.add(title);
        adminorders.add(author);
        adminorders.add(name);
        adminorders.add(adminordersBGL);
        frame.add(adminorders);
        frame.repaint();
    }
        
}
