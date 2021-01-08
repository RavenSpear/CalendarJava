package calendar;

import javax.swing.JFrame;  
import javax.swing.UIManager;  
 
public class ServerMainClass   
{   
        public static void main(String args[])   
        {   
             /*try {
            	 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
            	 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //windows界面风格 
              }catch (Exception e) {  
                e.printStackTrace();  
              } */
              ServerFrame frame=new ServerFrame();   
              frame.setBounds(200,200,360,300);   
              frame.setTitle("日历服务器");  
              frame.setLocationRelativeTo(null);//窗体居中显示  
              frame.setVisible(true);   
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        }  
} 