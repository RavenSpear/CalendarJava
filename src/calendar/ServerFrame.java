package calendar;

import java.awt.*;   
import java.awt.event.*;
import javax.swing.*;  
 
public class ServerFrame extends JFrame implements ActionListener   {
	JButton start = new JButton("Æô¶¯");
	JLabel info = new JLabel("·þÎñÆ÷Î´¿ªÆô",JLabel.CENTER);
	
	private static ServerDaemon server;
	private ServerDaemon getInstance() {
		if(server==null){
			server = new ServerDaemon();
			return server;
		}
		else
			return server;
	}
	
	class Mainthread implements Runnable{
	@Override
		public void run() {
		// TODO Auto-generated method stub
			ServerDaemon server = getInstance();
		}
	}
	private static final long serialVersionUID = 1L;
	public ServerFrame() {
		setBackground(new Color(0, 0, 0));   
        JPanel Panel = new JPanel();
        JPanel Centre = new JPanel();
        Centre.setLayout(new GridLayout(1,1));
        Panel.setBackground(Color.DARK_GRAY);
        Panel.add(start);
        Centre.setBackground(Color.DARK_GRAY);
        Centre.add(info);
        start.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        start.setBackground(new Color(0,183,239));
        info.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,30));
        start.setForeground(Color.white);
        info.setForeground(Color.white);
        getContentPane().add(Panel,BorderLayout.SOUTH);
        getContentPane().add(Centre,BorderLayout.CENTER);
        
        start.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==start) {
			info.setText("·þÎñÆ÷ÔËÐÐÖÐ");
			Thread mainthread = new Thread(new Mainthread());
			start.setEnabled(false);
			mainthread.start();
		}
	}
}

