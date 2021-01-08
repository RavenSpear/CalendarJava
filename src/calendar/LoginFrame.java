package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel input = new JLabel("ÇëÊäÈëÓÃ»§Ãû£º");
	JTextField username=new JTextField(10); 
	JButton login = new JButton("µÇÂ¼");
	JLabel welcome = new JLabel("¼«¼òÈÕÀúv1.0");
	PrintWriter out;
	private static Socket socket;
 	public static Socket getInstance(){
 		try {
 			if(socket==null){
 				socket = new Socket("10.203.240.54",8080);
 				return socket;
 			}
 			else
 				return socket;
 			} catch (IOException e) {
			// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
		return socket;
 	}
	public LoginFrame(){
		setBackground(Color.DARK_GRAY); 
        JPanel pOne=new JPanel();
        JPanel pCentre=new JPanel();
        pOne.setBackground(Color.DARK_GRAY);
        pCentre.setBackground(Color.DARK_GRAY);
        JPanel pSouth=new JPanel();
        pSouth.setBackground(Color.DARK_GRAY);
        input.setForeground(Color.white);
        input.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        username.setForeground(Color.DARK_GRAY);
        welcome.setForeground(Color.white);
        welcome.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,40));
        username.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        login.setBackground(new Color(0,183,239));
        login.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        login.setForeground(Color.white);
        input.setLocation(100,100);
        pOne.add(welcome);
        pCentre.add(input);
        pCentre.add(username);
        pSouth.add(login);
        getContentPane().add(pOne,BorderLayout.NORTH);
        getContentPane().add(pCentre,BorderLayout.CENTER);
        getContentPane().add(pSouth,BorderLayout.SOUTH);
        
        login.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login&&!(username.getText().equals(""))) {
			try {
		    	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(getInstance().getOutputStream())),true);
		    	out.println(username.getText());
		    	CalendarFrame frame = new CalendarFrame();
		    	frame.setBounds(200,200,720,600);   
	            frame.setTitle("ÈÕÀúÐ¡³ÌÐò");  
	            frame.setLocationRelativeTo(null);//´°Ìå¾ÓÖÐÏÔÊ¾  
	            frame.setVisible(true);   
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	            this.setVisible(false);
	            
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}