package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; 

public class blockList {
     JFrame frame; 
     String info[];
     int counter;
     int numofblocks;
      JLayeredPane memberinfo;
      JButton unblockbtn;
    public blockList(JFrame frame){
        //preparing data
        info=new String[500];
        numofblocks=0;
        this.frame=frame;
        Connection con=DBConnection.DBcon();
         try{
         Statement st = con.createStatement();
         String query="select * from registration where Isblocked='1'";
         ResultSet blockedusersInfo=st.executeQuery(query);
         counter=0;
         while(blockedusersInfo.next()){
             info[counter]=blockedusersInfo.getString("Email");
             info[counter+1]=blockedusersInfo.getString("Fname");
             info[counter+2]=blockedusersInfo.getString("Lname");
             info[counter+3]=blockedusersInfo.getString("Phone");
             info[counter+4]=blockedusersInfo.getString("Isblocked");
             info[counter+5]=blockedusersInfo.getString("ID");
             counter+=6;
             numofblocks+=6;
         }
    }
     catch(Exception e){
         System.out.println("error");
       }
        
        this.frame=frame;
        memberinfo=new JLayeredPane();
       memberinfo.setBounds(0,0,800,600);
       
       //background 
        ImageIcon memberinfoBG=new ImageIcon("memberresult.jpg");
        JLabel memberinfoBGL=new JLabel(memberinfoBG);
        memberinfoBGL.setBounds(0,0,800,600);
        
       // textfield
       counter=0;
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
        
     if(info[4+counter].equals("1")){
      ImageIcon unblock = new ImageIcon("unblock.png");
    unblockbtn=new JButton(unblock);
     unblockbtn.setBorder(null);
     unblockbtn.setContentAreaFilled(false);
     unblockbtn.setBounds(441,471,93,24);
     unblockbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
          try{
         Statement st = con.createStatement();
         String query="update registration set Isblocked='0' where ID=";
         query=query.concat("'"+info[5+counter]+"'");
         st.executeUpdate(query);
         JOptionPane.showMessageDialog(frame, "unblocked done successfuly","Done",JOptionPane.INFORMATION_MESSAGE);
    }
     catch(Exception e){
         System.out.println("error");
       }
          info[4+counter]="0";
          
          memberinfo.remove( memberinfoBGL);
          checkblockbtn();
          memberinfo.add( memberinfoBGL);
          }
          
     });
         
        memberinfo.add(unblockbtn);
     }
     
     //button
        ImageIcon next = new ImageIcon("nextbtn.png");
     JButton nextbtn=new JButton(next);
     
        ImageIcon previous = new ImageIcon("prevbtn.png");
     JButton previousbtn=new JButton(previous);
     previousbtn.setBorder(null);
     previousbtn.setContentAreaFilled(false);
     previousbtn.setBounds(134,471,93,24);
     previousbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              counter-=6;
               email.setText(info[counter]);
              fname.setText(info[counter+1]);
             lname.setText(info[counter+2]);
              phone.setText(info[counter+3]);
               memberinfo.remove(nextbtn);
               memberinfo.remove(memberinfoBGL);
               memberinfo.add(nextbtn);
                checkblockbtn();
               memberinfo.add(memberinfoBGL);
                  frame.repaint();
              
              if(counter<3){
              memberinfo.remove(previousbtn);
              }
              frame.repaint();
             
          }
     });
     
     
     
     nextbtn.setBorder(null);
     nextbtn.setContentAreaFilled(false);
     nextbtn.setBounds(238,471,93,24);
     nextbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
            counter+=6;
               email.setText(info[counter]);
              fname.setText(info[counter+1]);
             lname.setText(info[counter+2]);
             phone.setText(info[counter+3]);
              System.out.println(counter);
              System.out.println(numofblocks);
              if(counter+12>numofblocks){
                   memberinfo.remove(nextbtn);
                  frame.repaint();
              }
                   memberinfo.remove(previousbtn);
                   memberinfo.remove( memberinfoBGL);
                   memberinfo.add(previousbtn);
                   checkblockbtn();
                  memberinfo.add( memberinfoBGL);
                  frame.repaint();
                  
          }
     });
        if(numofblocks>6){
             memberinfo.add(nextbtn);
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
    void checkblockbtn(){
       
        if(info[counter+4].equals("1")){
           System.out.println(info[counter+4]);
            memberinfo.remove(unblockbtn);
             memberinfo.add(unblockbtn);
        }
        else if(info[counter+4].equals("0")){
            memberinfo.remove(unblockbtn);
        }
    }
}
