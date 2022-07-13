
package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class returnBook {
    
    JFrame frame;
    String title[];
    int selectedbook=0;
    public returnBook(JFrame frame,String rentedbook[],int numofbooks){
        this.frame=frame;
        title=new String[numofbooks+5];
         JTextField  titleTF = new JTextField(27);
        JLayeredPane returnbook=new JLayeredPane();
       returnbook.setBounds(0,0,800,600);
        
        //background 
        ImageIcon returnbookBG=new ImageIcon("returnbookbg.jpg");
        JLabel returnbookBGL=new JLabel(returnbookBG);
        returnbookBGL.setBounds(0,0,800,600);
        
        
         //buttons
          ImageIcon returnb = new ImageIcon("returnbtn.png");
      JButton returnbbtn=new JButton(returnb);
     returnbbtn.setBorder(null);
     returnbbtn.setContentAreaFilled(false);
     returnbbtn.setBounds(441,471,93,24);
     returnbbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
           Connection con=DBConnection.DBcon();
           try{
         Statement st = con.createStatement();
               System.out.println(rentedbook[selectedbook]);
               System.out.println(login.memberID);
         String query="delete from memberbook where BookID="+rentedbook[selectedbook]+" and ID="+login.memberID+"";
         String query2="update book set Numofitems=Numofitems+1 where BookID="+rentedbook[selectedbook]+"";
         st.executeUpdate(query);
         st.executeUpdate(query2);
        
    }
     catch(Exception e){
         System.out.println("error");
       }
           rentedbook[selectedbook]="-1";
            returnbook.remove(returnbbtn);
            frame.repaint();
          }
     });
     
         ImageIcon previous = new ImageIcon("prevbtn.png");
     JButton previousbtn=new JButton(previous);
     
     ImageIcon next = new ImageIcon("nextbtn.png");
     JButton nextbtn=new JButton(next);
      nextbtn.setBorder(null);
     nextbtn.setContentAreaFilled(false);
     nextbtn.setBounds(238,442,93,24);
     nextbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
           selectedbook++;
              titleTF.setText(title[selectedbook]);
              if(selectedbook+2>numofbooks){
                  returnbook.remove(nextbtn);
                  frame.repaint();
              }
                  returnbook.remove(previousbtn);
                  returnbook.remove(returnbookBGL);
                  returnbook.add(previousbtn);
                   if(!rentedbook[selectedbook].equals("-1")){
           System.out.println(rentedbook[selectedbook]);
           returnbook.remove(returnbbtn);
             returnbook.add(returnbbtn);
        }
        else if(rentedbook[selectedbook].equals("-1")){
            returnbook.remove(returnbbtn);
        }
                  returnbook.add(returnbookBGL);
                  frame.repaint();
          }
     });
     
        
     previousbtn.setBorder(null);
     previousbtn.setContentAreaFilled(false);
     previousbtn.setBounds(134,442,93,24);
     previousbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              selectedbook--;
              titleTF.setText(title[selectedbook]);
              returnbook.remove(nextbtn);
                   returnbook.remove(returnbookBGL);
                  returnbook.add(nextbtn);
                  if(!rentedbook[selectedbook].equals("-1")){
           System.out.println(rentedbook[selectedbook]);
           returnbook.remove(returnbbtn);
             returnbook.add(returnbbtn);
        }
        else if(rentedbook[selectedbook].equals("-1")){
            returnbook.remove(returnbbtn);
        }
                   returnbook.add(returnbookBGL);
                  frame.repaint();
 
              if(selectedbook<1){
              returnbook.remove(previousbtn);
              }
              frame.repaint();
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
              frame.remove(returnbook);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
     
     Connection con=DBConnection.DBcon();
                int counter=0;
         try{
         Statement st = con.createStatement();
         String query;
         ResultSet blockedusersInfo;
         
       for(int i=0;i<numofbooks;i++){
          query ="select * from book where BookID="+Integer.parseInt(rentedbook[i])+"";
          blockedusersInfo=st.executeQuery(query);
          while(blockedusersInfo.next()){
              title[i]=blockedusersInfo.getString("Title");
          }

       }
        
    }
     catch(Exception e){
         System.out.println("error");
       }
         // textfield
        Color myColor = Color.decode("#f6f6f6");
        
        titleTF.setText(title[selectedbook]);
        titleTF.setBounds(144,361,158,25);
        titleTF.setBorder(null);
        titleTF.setBackground(myColor);
        titleTF.setEditable(false);
     
     
     returnbook.add(titleTF);
     returnbook.add(homebtn);
     returnbook.add(returnbbtn);
     if(numofbooks>1){
     returnbook.add(nextbtn);
     }
     returnbook.add(returnbookBGL);
     frame.add(returnbook);
     frame.repaint();
    }
    
}
