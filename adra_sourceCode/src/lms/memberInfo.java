package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class memberInfo {
    JFrame frame;
     
    public memberInfo(JFrame frame,String[] info){
        this.frame=frame;
        JLayeredPane memberinfo=new JLayeredPane();
       memberinfo.setBounds(0,0,800,600);
      
       
       //background 
        ImageIcon memberinfoBG=new ImageIcon("memberresult.jpg");
        JLabel memberinfoBGL=new JLabel(memberinfoBG);
        memberinfoBGL.setBounds(0,0,800,600);
        
       // textfield
       Color myColor = Color.decode("#f6f6f6");
       JTextField email =new JTextField(info[0],27);
      email.setBounds(192,308,157,25);
      email.setBorder(null);
      email.setBackground(myColor);
     email.setEditable(false);
      
        JTextField fname=new JTextField(info[1],27);
      fname.setBounds(192,348,157,25);
      fname.setBorder(null);
      fname.setBackground(myColor);
      fname.setEditable(false);
      
      JTextField lname=new JTextField(info[2],27);
      lname.setBounds(192,388,157,25);
      lname.setBorder(null);
      lname.setBackground(myColor);
      lname.setEditable(false);
      
      JTextField phone=new JTextField(info[3],27);
      phone.setBounds(192,428,157,25);
      phone.setBorder(null);
      phone.setBackground(myColor);
       phone.setEditable(false);
       
        //buttons
         ImageIcon home = new ImageIcon("homebtn.png");
     JButton homebtn=new JButton(home);
     homebtn.setBorder(null);
     homebtn.setContentAreaFilled(false);
     homebtn.setBounds(31,49,83,28);
   
     homebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(memberinfo);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
        
     if(info[5].equals("1")){
    
      ImageIcon unblock = new ImageIcon("unblock.png");
     JButton unblockbtn=new JButton(unblock);
     unblockbtn.setBorder(null);
     unblockbtn.setContentAreaFilled(false);
     unblockbtn.setBounds(238,471,93,24);
     unblockbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              Connection con=DBConnection.DBcon();
          try{
         Statement st = con.createStatement();
         String query="update registration set Isblocked='0' where ID=";
         query=query.concat("'"+info[4]+"'");
         st.executeUpdate(query);
         memberinfo.remove(unblockbtn);
         frame.repaint();
    }
     catch(Exception e){
        
       }
          }
     });
         
        memberinfo.add(unblockbtn);
     }
     if(info[6].equals("0")){
           System.out.println(info[6]);
         Connection con=DBConnection.DBcon();
      ImageIcon addadmin = new ImageIcon("addadmin.png");
     JButton addadminbtn=new JButton(addadmin);
     addadminbtn.setBorder(null);
     addadminbtn.setContentAreaFilled(false);
     addadminbtn.setBounds(131,471,93,24);
     addadminbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              Connection conn=DBConnection.DBcon();
              
          try{
         Statement st = conn.createStatement();
         String query="update registration set Isadmin=1 where ID=";
         query=query.concat("'"+info[4]+"'");
         st.executeUpdate(query);
    }
     catch(Exception e){
        
       }
          }
     });  
        memberinfo.add(addadminbtn);
     }
     
        
        memberinfo.add(homebtn);
        memberinfo.add(phone);
        memberinfo.add(lname);
        memberinfo.add(fname);
        memberinfo.add(email);
        memberinfo.add(memberinfoBGL);
        frame.add(memberinfo);
        frame.repaint();
    }
}
