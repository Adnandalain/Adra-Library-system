
package lms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DBConnection {
    
    
    public static Connection DBcon(){
        
       try{
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:------your_path_here");
           Statement st = con.createStatement();
                
           return con;
       }
       catch(Exception e){
           return null;
       }
    }
}
