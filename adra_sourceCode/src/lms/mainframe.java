
package lms;
import javax.swing.*;


public class mainframe {
    JFrame frame;
    mainframe(){
        frame=new JFrame("adra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(800,615);
        frame.setLayout(null);
        new login(frame);
    }

   
}
