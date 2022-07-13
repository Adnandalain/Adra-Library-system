package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
 
public class login{
    JFrame frame;
    static boolean admin;
    static String memberID;
    static String userstate="0";
    static String userName;
    public login(JFrame frame){
        this.frame=frame;
      JLayeredPane loginPage = new JLayeredPane();
      loginPage.setBounds(0,0,800,600); 
 
      //background
      ImageIcon background=new ImageIcon("background1.jpg");
      JLabel bgLabel=new JLabel(background); 
      bgLabel.setBounds(0,0,800,600);
      
      
      //textField
      JTextField email=new JTextField(27);
      email.setBounds(152,337,158,25);
      email.setBorder(null);
      Color myColor = Color.decode("#f6f6f6");
      email.setBackground(myColor);
      loginPage.add(email);
       
      JPasswordField password=new JPasswordField(27);
      password.setBounds(152,377,158,25);
      password.setBorder(null);
      password.setBackground(myColor);
      loginPage.add(password);

      
      //buttons
      ImageIcon LogIn = new ImageIcon("loginbtn.png");
     JButton loginbtn=new JButton(LogIn);
     loginbtn.setBorder(null);
     loginbtn.setContentAreaFilled(false);
   
   
     loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                  //getting data from user
      String storedemail=email.getText();
      String storedpass=password.getText();
      Connection con=DBConnection.DBcon();
      try{
           Statement st = con.createStatement();
           String query="select * from registration";
           ResultSet storeddata=st.executeQuery(query);
           while(storeddata.next()){
               String getemail=storeddata.getString("Email");
               String getpass=storeddata.getString("Password");
               String getmemberID=storeddata.getString("ID");
               String getmemberfname=storeddata.getString("Fname");
               String getmemberlname=storeddata.getString("Lname");
               boolean isadmin=storeddata.getBoolean("Isadmin");
               
               if(storedemail.equals(getemail) && storedpass.equals(getpass)){
                   userName=getmemberfname.concat(" "+ getmemberlname);
                   
                   //is this user blocked ?
                   query="select DATEDIFF(SYSDATE(),rentdate) from memberbook where ID="+getmemberID+"";
                   ResultSet isblocked = st.executeQuery(query);
                   A:while (isblocked.next()){
                      int days =  Integer.parseInt(isblocked.getString("DATEDIFF(SYSDATE(),rentdate)"));
                       System.out.println(days);
                      if(days>7){
                      query="update registration set Isblocked='1' where ID="+getmemberID+"";
                      st.executeUpdate(query);
                      break A;
                      }
                   }
                      query="select Isblocked from registration where ID="+getmemberID+"";
                       ResultSet isblockedstate  = st.executeQuery(query);
                        while (isblockedstate.next()){
                            String userstateset =isblockedstate.getString("Isblocked");
                            System.out.println(userstateset);
                            if(userstateset.equals("0")){
                                userstate="0";
                            }
                            else {
                                userstate="1";
                            }
                        }

                   
                  frame.remove(loginPage);
                  memberID=getmemberID;
                  if(isadmin)
                  {
                      admin=isadmin;
                      new adminPage(frame);  
                  }
                  else if(!isadmin){
                      admin=isadmin;
                      new userPage(frame);
                  }
               }
           }
       }
       catch(Exception e){
          
       }
            }
        });
      loginbtn.setBounds(132,430,93,24);
      
      ImageIcon SignUp = new ImageIcon("signupbtn.png");
       JButton signupbtn=new JButton(SignUp);
      signupbtn.setBorder(null);
      signupbtn.setContentAreaFilled(false);
     signupbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               frame.remove(loginPage);
              new register(frame); 
            }
        });
      signupbtn.setBounds(236,430,93,24);
      
      
     loginPage.add(loginbtn);
     loginPage.add(signupbtn);
     loginPage.add(bgLabel);
     frame.add(loginPage);
     frame.repaint();
 
    }
}