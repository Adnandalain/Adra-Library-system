package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class adminPage {
    JFrame frame;
    public adminPage(JFrame frame){
        this.frame=frame;
        JLayeredPane adminP=new JLayeredPane();
        adminP.setBounds(0,0,800,600);
        
        //background 
        ImageIcon adminBG=new ImageIcon("adminBG.jpg");
        JLabel adminBGL=new JLabel(adminBG);
        adminBGL.setBounds(0,0,800,600);
        
        //buttons
        
        ImageIcon blocklist = new ImageIcon("blocklist.png");
     JButton blocklistbtn=new JButton(blocklist);
     blocklistbtn.setBorder(null);
     blocklistbtn.setContentAreaFilled(false);
     blocklistbtn.setBounds(17,391,123,41);
    
     blocklistbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(adminP);
              new blockList(frame);
            }
        });
        
      ImageIcon findmember = new ImageIcon("findmember.png");
     JButton findmemberbtn=new JButton(findmember);
     findmemberbtn.setBorder(null);
     findmemberbtn.setContentAreaFilled(false);
     findmemberbtn.setBounds(150,391,123,41);
     
     findmemberbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(adminP);
              new findMember(frame);
            }
        });
     ImageIcon addbook = new ImageIcon("addbook.png");
     JButton addbookbtn=new JButton(addbook);
     addbookbtn.setBorder(null);
     addbookbtn.setContentAreaFilled(false);
     addbookbtn.setBounds(283,391,123,41);
     addbookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(adminP);
              new addBook(frame);
            }
        });
      ImageIcon findbook = new ImageIcon("findbook.png");
     JButton findbookbtn=new JButton(findbook);
     findbookbtn.setBorder(null);
     findbookbtn.setContentAreaFilled(false);
     findbookbtn.setBounds(412,391,123,41);
   
     findbookbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               frame.remove(adminP);
               new findBook(frame);
            }
        });
     
     
     
     ImageIcon bookorder = new ImageIcon("bookorder.png");
     JButton bookorderbtn=new JButton(bookorder);
     bookorderbtn.setBorder(null);
     bookorderbtn.setContentAreaFilled(false);
    bookorderbtn.setBounds(545,391,123,41);
     bookorderbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(adminP);
              new adminOrders(frame);
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
              frame.remove(adminP);
              new login(frame);
            }
        });
        adminP.add(blocklistbtn);
        adminP.add(addbookbtn);
        adminP.add(findbookbtn);
        adminP.add(findmemberbtn);
        adminP.add(bookorderbtn);
        adminP.add(logoutbtn);
        adminP.add(adminBGL);
        frame.add(adminP);
        frame.repaint();
    }
}
