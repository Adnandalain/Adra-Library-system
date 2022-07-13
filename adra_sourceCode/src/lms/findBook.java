
package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class findBook {
    JFrame frame;
    public findBook(JFrame frame){
      this.frame=frame;
        JLayeredPane findbook=new JLayeredPane();
       findbook.setBounds(0,0,800,600);
        
        //background 
        ImageIcon findbookBG=new ImageIcon("booksearch.jpg");
        JLabel findbookBGL=new JLabel(findbookBG);
        findbookBGL.setBounds(0,0,800,600);
        
        //arrow
        ImageIcon arrow=new ImageIcon("arrowR.png");
        JLabel arrowL=new JLabel(arrow);
        arrowL.setBounds(289,323,16,17);
        
         // Jtext field
        JTextField  input = new JTextField(27);
        input.setBounds(79,381,158,25);
        input.setBorder(null);
        Color myColor = Color.decode("#f6f6f6");
        input.setBackground(myColor);
        
        //combo box
        String[] searchcategory ={"Title","Author","production year"};
        JComboBox searchby =new JComboBox(searchcategory);
        searchby.setBounds(140,319 ,177,25);
        
         //button
        ImageIcon search = new ImageIcon("searchbtn.png");
     JButton searchbtn=new JButton(search);
     searchbtn.setContentAreaFilled(false);
     searchbtn.setBounds(105,441,123,41);
     searchbtn.setBorder(null);
     searchbtn.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent ae) {
              Connection con=DBConnection.DBcon();
                String storedinput=input.getText();
                String searchselected = searchby.getSelectedItem().toString();
                try{
           Statement st = con.createStatement();
           String query="select * from book";
          ResultSet result=st.executeQuery(query);
            int counter=0;
            String neededbooks[]=new String[1000];
          while(result.next()){
             String title = result.getString("Title");
             String author = result.getString("Author");
             String productionyear = result.getString("Productionyear");
             String bookID=result.getString("BookID");
             
             int isavailable =Integer.parseInt(result.getString("Numofitems"));
             if(searchselected.equals("Title") && title.equals(storedinput) && isavailable>0){
             neededbooks[counter]=title;
             counter++;
             neededbooks[counter]=author;
             counter++;
             neededbooks[counter]=productionyear;
             counter++;
             neededbooks[counter]=bookID;
             counter++;
             }
             else if(searchselected.equals("Author") && author.equals(storedinput) && isavailable>0){
              neededbooks[counter]=title;
             counter++;
             neededbooks[counter]=author;
             counter++;
             neededbooks[counter]=productionyear;
             counter++;
             neededbooks[counter]=bookID;
             counter++;
             }
             else if(searchselected.equals("production year") && productionyear.equals(storedinput) && isavailable>0){
              neededbooks[counter]=title;
             counter++;
             neededbooks[counter]=author;
             counter++;
             neededbooks[counter]=productionyear;
             counter++;
              neededbooks[counter]=bookID;
             counter++;
             }
          
          }
          if(counter !=0){
              frame.remove(findbook);
              new selectedBook(frame,neededbooks,counter);
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
              frame.remove(findbook);
              if(login.admin){
                  new adminPage(frame);
              }
              else if(!login.admin){
                  new userPage(frame);
              }
            }
        });
        
        findbook.add(homebtn);
        findbook.add(searchbtn);
        findbook.add(arrowL);
        findbook.add(searchby);
        findbook.add(input);
        findbook.add(findbookBGL);
        frame.add(findbook);
        frame.repaint();
    }
}
