package lms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 
public class findMember {
    JFrame frame;
    public findMember(JFrame frame){
      this.frame=frame;
        JLayeredPane findmember=new JLayeredPane();
       findmember.setBounds(0,0,800,600);
        
        //background 
        ImageIcon findmemberBG=new ImageIcon("memebersearch.jpg");
        JLabel findmemberBGL=new JLabel(findmemberBG);
        findmemberBGL.setBounds(0,0,800,600);
        
        // Jtext field
        JTextField  idinput = new JTextField(27);
        idinput.setBounds(155,301,158,25);
        idinput.setBorder(null);
        Color myColor = Color.decode("#f6f6f6");
        idinput.setBackground(myColor);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
        //button
        ImageIcon search = new ImageIcon("searchbtn.png");
     JButton searchbtn=new JButton(search);
     searchbtn.setBorder(null);
     searchbtn.setContentAreaFilled(false);
     searchbtn.setBounds(175,345,123,41);
     searchbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
           Connection con=DBConnection.DBcon();
                String storedId=idinput.getText();
                try{
           Statement st = con.createStatement();
           String query="select * from registration";
          ResultSet result=st.executeQuery(query);

          while(result.next()){
             String id=result.getString("ID");
             if(id.equals(storedId))
             {
                 String info[]=new String[7];
                 info[0]=result.getString("Email");
                 info[1]=result.getString("Fname");
                 info[2]=result.getString("Lname");
                 info[3]=result.getString("Phone");
                 info[4]=result.getString("ID");
                 info[6]=result.getString("Isadmin");
                 info[5]=result.getString("Isblocked");
                 
                frame.remove(findmember);
                new memberInfo(frame,info);
             }
              
          }
          
        
       }
       catch(Exception e){
           System.out.println("error");
       }
          }
      });
      ImageIcon home = new ImageIcon("homebtnblue.png");
     JButton homebtn=new JButton(home);
     homebtn.setBorder(null);
     homebtn.setContentAreaFilled(false);
     homebtn.setBounds(31,49,83,28);
    
     homebtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
              frame.remove(findmember);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
        
        findmember.add(homebtn);
        findmember.add(searchbtn);
        findmember.add(idinput);
        findmember.add(findmemberBGL);
        frame.add(findmember);
        frame.repaint();
    }
}
