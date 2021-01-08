package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.*;

public class ScheduleDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	JButton saveButton = new JButton("±£¥Ê");
	JLabel inputhour = new JLabel("–° ±");
	JLabel inputminute = new JLabel("∑÷÷”");
	JTextArea content=new JTextArea(10,15); 
	public JComboBox<String> hour = new JComboBox<String>();
	public JComboBox<String> minute = new JComboBox<String>();
	BufferedReader in;
	PrintWriter out;
	CalendarFrame This;
	String title;
	public ScheduleDialog(CalendarFrame th,String t) throws UnknownHostException, IOException{
		This = th;
		title = t;
		setBackground(new Color(128, 128, 128)); 
        JPanel pCenter=new JPanel();   
        pCenter.setBackground(Color.DARK_GRAY);
        pCenter.setLayout(new BorderLayout()); 
        for(Integer i =0;i<24;i++) {
        	hour.addItem(i.toString());
        }
        for(Integer i =0;i<60;i++) {
        	minute.addItem(i.toString());
        }
        JPanel pNorth=new JPanel(),  
        	   pSouth=new JPanel();   
        pNorth.setBackground(Color.DARK_GRAY);
        pSouth.setBackground(Color.DARK_GRAY);
        ScrollPane scrollPane=new ScrollPane();
        scrollPane.add(pCenter);
        pCenter.add(content);
        pNorth.add(inputhour);
        pNorth.add(hour);
        pNorth.add(inputminute);
        pNorth.add(minute);
        pSouth.add(saveButton);
        saveButton.setForeground(Color.white);
        saveButton.setBackground(new Color(0,183,239));
        saveButton.setFont(new Font("∫∫“«—≈ø·∫⁄W",Font.PLAIN,20));
        inputhour.setForeground(Color.white);
        inputhour.setFont(new Font("∫∫“«—≈ø·∫⁄W",Font.PLAIN,30));
        inputminute.setForeground(Color.white);
        inputminute.setFont(new Font("∫∫“«—≈ø·∫⁄W",Font.PLAIN,30));
        hour.setForeground(Color.DARK_GRAY);
        hour.setFont(new Font("∫∫“«—≈ø·∫⁄W",Font.PLAIN,20));
        minute.setForeground(Color.DARK_GRAY);
        minute.setFont(new Font("∫∫“«—≈ø·∫⁄W",Font.PLAIN,20));
        content.setForeground(Color.DARK_GRAY);
        content.setFont(new Font("∫∫“«—≈ø·∫⁄W",Font.PLAIN,20));
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        getContentPane().add(pNorth,BorderLayout.NORTH);
        getContentPane().add(pSouth,BorderLayout.SOUTH);
        in = new BufferedReader(new InputStreamReader(LoginFrame.getInstance().getInputStream()));
    	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(LoginFrame.getInstance().getOutputStream())),true);
        saveButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==saveButton) {
			out.println("4");
			for(int i = 0;i<This.list.size();i++) {
				if(This.list.get(i)[5].equals(title)) {
					This.list.get(i)[3]=hour.getSelectedItem().toString();
					This.list.get(i)[4]=minute.getSelectedItem().toString();
					This.list.get(i)[6]=content.getText();
				}
			}
			out.println(hour.getSelectedItem().toString());
			out.println(minute.getSelectedItem().toString());
			out.println(content.getText());
		}
	}
}