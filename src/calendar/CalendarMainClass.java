package calendar;

import javax.swing.JFrame;  
import javax.swing.UIManager;  
 
public class CalendarMainClass   
{   
        public static void main(String args[])   
        {   
             /*try {
            	 UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //windows界面风格  
              }catch (Exception e) {  
                e.printStackTrace();  
              } */ 
              LoginFrame frame=new LoginFrame();   
              frame.setBounds(200,200,500,200);
              frame.setResizable(false);
              frame.setTitle("日历登录");  
              frame.setLocationRelativeTo(null);//窗体居中显示  
              frame.setVisible(true);   
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        }
} 