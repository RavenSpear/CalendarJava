package calendar;

import javax.swing.JFrame;  
import javax.swing.UIManager;  
 
public class ServerMainClass   
{   
        public static void main(String args[])   
        {   
             /*try {
            	 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
            	 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //windows������ 
              }catch (Exception e) {  
                e.printStackTrace();  
              } */
              ServerFrame frame=new ServerFrame();   
              frame.setBounds(200,200,360,300);   
              frame.setTitle("����������");  
              frame.setLocationRelativeTo(null);//���������ʾ  
              frame.setVisible(true);   
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        }  
} 