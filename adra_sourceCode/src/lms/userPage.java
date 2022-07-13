package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class userPage {
    JFrame frame;
    public userPage(JFrame frame){
        this.frame=frame;
        JLayeredPane userP=new JLayeredPane();
       userP.setBounds(0,0,800,600);
        
        //background 
        ImageIcon userBG=new ImageIcon("userBG.jpg");
        JLabel userBGL=new JLabel(userBG);
        userBGL.setBounds(0,0,800,600);
        
        //buttons
         ImageIcon findbook = new ImageIcon("userfindbook.png");
     JButton findbookbtn=new JButton(findbook);
     findbookbtn.setBorder(null);
     findbookbtn.setContentAreaFilled(false);
     findbookbtn.setBounds(30,391,123,41);
   
     findbookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(userP);
              new findBook(frame);
            }
        });
     
     ImageIcon findmember = new ImageIcon("findmember.png");
     JButton findmemberbtn=new JButton(findmember);
     findmemberbtn.setBorder(null);
     findmemberbtn.setContentAreaFilled(false);
     findmemberbtn.setBounds(194,391,123,41);
     
     findmemberbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(userP);
              new findMember(frame);
            }
        });
     ImageIcon returnbook = new ImageIcon("returnbook.png");
     JButton returnbookbtn=new JButton(returnbook  );
     returnbookbtn.setBorder(null);
     returnbookbtn.setContentAreaFilled(false);
     returnbookbtn.setBounds(358,391,123,41);
 
     returnbookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                 Connection con=DBConnection.DBcon();
                int counter=0;
         try{
         Statement st = con.createStatement();
         String query="select * from memberbook where ID="+login.memberID+"";
         ResultSet blockedusersInfo=st.executeQuery(query);
        String rentedbook[]=new String[50];
         while(blockedusersInfo.next()){
            rentedbook[counter]=blockedusersInfo.getString("BookID");
            counter++;
         }
         if(counter>0){
             frame.remove(userP);
             new returnBook(frame,rentedbook,counter);
         }
    }
     catch(Exception e){
         System.out.println("error");
       }
            }
        });
     
     ImageIcon bookorder = new ImageIcon("bookorder.png");
     JButton bookorderbtn=new JButton(bookorder);
     bookorderbtn.setBorder(null);
     bookorderbtn.setContentAreaFilled(false);
    bookorderbtn.setBounds(526,391,123,41);
     bookorderbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(userP);
              new bookOrders(frame);
            }
        });
       
     ImageIcon logout = new ImageIcon("power_btn.png");
     JButton logoutbtn=new JButton(logout);
     logoutbtn.setBorder(null);
     logoutbtn.setContentAreaFilled(false);
     logoutbtn.setBounds(25,84,35,42);
    
     logoutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(userP);
              new login(frame);
            }
        });
     
        userP.add(returnbookbtn);
        userP.add(findbookbtn);
        userP.add(findmemberbtn);
        userP.add(bookorderbtn);
        userP.add(logoutbtn);
        userP.add(userBGL);
        frame.add(userP);
        frame.repaint();
    }
}
