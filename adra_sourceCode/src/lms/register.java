package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
 
public class register {
 
    JFrame mframe;
    register(JFrame frame){
        this.mframe=frame;
        
      JLayeredPane registration = new JLayeredPane();
      registration.setBounds(0,0,800,600);
      

      //background
      ImageIcon background=new ImageIcon("registration.jpg");
      JLabel bgLabel=new JLabel(background); 
      bgLabel.setBounds(0,0,800,600);
     
      
      
      //getting data from user
      
       Color myColor = Color.decode("#f6f6f6");
      JTextField id=new JTextField(27);
      id.setBounds(195,221,157,25);
      id.setBorder(null);
      id.setBackground(myColor);
      registration.add(id);
      
      JTextField email=new JTextField(27);
      email.setBounds(195,261,157,25);
      email.setBorder(null);
      email.setBackground(myColor);
      registration.add(email);
      
      JTextField password=new JTextField(27);
      password.setBounds(195,301,157,25);
      password.setBorder(null);
      password.setBackground(myColor);
      registration.add(password);
      
      JTextField fname=new JTextField(27);
      fname.setBounds(195,341,157,25);
      fname.setBorder(null);
      fname.setBackground(myColor);
      registration.add(fname);
      
      JTextField lname=new JTextField(27);
      lname.setBounds(195,381,157,25);
      lname.setBorder(null);
      lname.setBackground(myColor);
      registration.add(lname);
      
      JTextField phone=new JTextField(27);
      phone.setBounds(195,421,157,25);
      phone.setBorder(null);
      phone.setBackground(myColor);
      registration.add(phone);
      
        //SIGNup button
      ImageIcon SignUp = new ImageIcon("signupbtn.png");
       JButton signupbtn=new JButton(SignUp);
       signupbtn.setBorder(null);
       // to hide the button 
        signupbtn.setContentAreaFilled(false);
        signupbtn.setBounds(221,478,93,24);
       
      // Storing data in data base 
            //button action listener
      
     signupbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
               mframe.remove(registration);
                Connection con=DBConnection.DBcon();
                String storedId=id.getText();
        String storedEmail=email.getText();
        String storedPass=password.getText();
        String storedFname=fname.getText();
        String storedLname=lname.getText();
        String storedPhone=phone.getText();
      try{
           Statement st = con.createStatement();
           String query="insert into registration values ('"+storedId+"','"+storedEmail+"','"+storedPass+"','"+storedFname+"'"
                   + ",'"+storedLname+"','"+storedPhone+"','0','0')";
           st.executeUpdate(query);
          System.out.println("done yasta");
       }
       catch(Exception e){
           System.out.println("error");
       }
      JOptionPane.showMessageDialog(mframe,"Registration Done");
      new login(mframe);
            }
        });
     
     registration.add(signupbtn);
     registration.add(bgLabel);
     frame.add(registration);
     frame.repaint();
    }
}