
package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class selectedBook {
     JFrame frame;
      int counter;
    public selectedBook(JFrame frame, String books[],int maxbook){
    this.frame=frame;
        JLayeredPane selectedbook=new JLayeredPane();
       selectedbook.setBounds(0,0,800,600);
        
        //background 
        ImageIcon selectedbookBG=new ImageIcon("bookresult.jpg");
        JLabel selectedbookBGL=new JLabel(selectedbookBG);
        selectedbookBGL.setBounds(0,0,800,600);
        
        //preparing the data
        counter=0;
        String titl=books[counter];
        String autho=books[counter+1];
        String pyear=books[counter+2];
         // Jtext field
         Color myColor = Color.decode("#f6f6f6");
         
        JTextField  title = new JTextField(titl,27);
        title.setBounds(196,303,158,25);
        title.setBorder(null);
        title.setBackground(myColor);
        title.setEditable(false);
        
        JTextField  author = new JTextField(autho,27);
        author.setBounds(196,343,158,25);
        author.setBorder(null);
        author.setBackground(myColor);
        author.setEditable(false);
        
         JTextField  productionyear = new JTextField(pyear,27);
        productionyear.setBounds(196,383,158,25);
        productionyear.setBorder(null);
        productionyear.setBackground(myColor);
        productionyear.setEditable(false);
        
        //button
        ImageIcon next = new ImageIcon("nextbtn.png");
     JButton nextbtn=new JButton(next);
     
        ImageIcon previous = new ImageIcon("prevbtn.png");
     JButton previousbtn=new JButton(previous);
     previousbtn.setBorder(null);
     previousbtn.setContentAreaFilled(false);
     previousbtn.setBounds(134,442,93,24);
     previousbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              counter-=4;
            
               title.setText(books[counter]);
              author.setText(books[counter+1]);
              productionyear.setText(books[counter+2]);
              
              selectedbook.remove(nextbtn);
                   selectedbook.remove(selectedbookBGL);
                  selectedbook.add(nextbtn);
                   selectedbook.add(selectedbookBGL);
                  frame.repaint();
              
              if(counter<4){
              selectedbook.remove(previousbtn);
              }
              frame.repaint();
          }
     });
     ImageIcon rent = new ImageIcon("rentbtn.png");
     JButton rentbtn=new JButton(rent);
     rentbtn.setBorder(null);
     rentbtn.setContentAreaFilled(false);
     rentbtn.setBounds(442,471,93,24);
      Connection con=DBConnection.DBcon();
     rentbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              String memberID=login.memberID;
               try{
           Statement st = con.createStatement();
           String query="insert into memberbook values ('"+memberID+"','"+books[counter+3]+"',SYSDATE())";
           String itemquery="update book set Numofitems=Numofitems-1 where BookID="+books[counter+3]+"";
           st.executeUpdate(query);
           st.executeUpdate(itemquery);
          System.out.println("done yasta");
          JOptionPane.showMessageDialog(frame, "Done");
          
       }
       catch(Exception e){
           System.out.println("error");
       }
          }
     });
     
     
     
     nextbtn.setBorder(null);
     nextbtn.setContentAreaFilled(false);
     nextbtn.setBounds(238,442,93,24);
     nextbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
            counter+=4;
           
              title.setText(books[counter]);
              author.setText(books[counter+1]);
              productionyear.setText(books[counter+2]);
              
              
              if(counter+8>maxbook){
                  selectedbook.remove(nextbtn);
                  frame.repaint();
              }
                  selectedbook.remove(previousbtn);
                  selectedbook.remove(selectedbookBGL);
                  selectedbook.add(previousbtn);
                  selectedbook.add(selectedbookBGL);
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
              frame.remove(selectedbook);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
        
        selectedbook.add(homebtn);
        System.out.println(maxbook);
        if(counter+4<maxbook){
        selectedbook.add(nextbtn);
        }
       
        
        selectedbook.add(title);
        selectedbook.add(author);
        selectedbook.add(productionyear);
        System.out.println(login.userstate.equals("1"));
        if(!login.userstate.equals("1")){
           selectedbook.add(rentbtn); 
        }
        selectedbook.add(selectedbookBGL);
        frame.add(selectedbook);
        frame.repaint();
    }
}
