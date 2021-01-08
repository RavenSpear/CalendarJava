package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class SelectDialog extends JDialog implements ActionListener{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	JButton queryButton = new JButton("²é¿´");
	JButton deleteButton = new JButton("É¾³ý");
	JButton newButton = new JButton("ÐÂÌí");
	JLabel select = new JLabel("Ñ¡ÔñÊÂÏî£º");
	JTextField input = new JTextField(8);
	JComboBox<String> item = new JComboBox<String>();
	BufferedReader in;
	PrintWriter out;
	String year = new String("");
	String month = new String("");
	String day = new String("");
	ScheduleDialog newDialog;
	CalendarFrame This;
	
	public SelectDialog(Integer y,Integer m,String d,CalendarFrame th) throws UnknownHostException, IOException{
		This = th;
		
		setBackground(new Color(128, 128, 128)); 
        JPanel pCenter=new JPanel();
        pCenter.setBackground(Color.DARK_GRAY);
        JPanel pSouth=new JPanel();
        pSouth.setBackground(Color.DARK_GRAY);
        queryButton.setForeground(Color.white);
        queryButton.setBackground(new Color(0,183,239));
        queryButton.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        deleteButton.setForeground(Color.white);
        deleteButton.setBackground(new Color(0,183,239));
        deleteButton.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        newButton.setForeground(Color.white);
        newButton.setBackground(new Color(0,183,239));
        newButton.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        select.setForeground(Color.white);
        select.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,30));
        input.setForeground(Color.DARK_GRAY);
        input.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,20));
        item.setForeground(Color.DARK_GRAY);
        item.setFont(new Font("ººÒÇÑÅ¿áºÚW",Font.PLAIN,25));
        pCenter.add(select);
        pCenter.add(item);
        pSouth.add(queryButton);
        pSouth.add(deleteButton);
        pSouth.add(newButton);
        pSouth.add(input);
        getContentPane().add(pCenter,BorderLayout.CENTER);
        getContentPane().add(pSouth,BorderLayout.SOUTH);
        in = new BufferedReader(new InputStreamReader(LoginFrame.getInstance().getInputStream()));
    	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(LoginFrame.getInstance().getOutputStream())),true);
    	year = y.toString();
    	month = m.toString();
    	day = d;
        out.println("5");
        out.println(year);
        out.println(month);
        out.println(day);
        int count = Integer.parseInt(in.readLine());
        for(int i = 0;i<count;i++) {
        	String temp = new String(in.readLine());
        	item.addItem(temp);
        }
        queryButton.addActionListener(this);
        deleteButton.addActionListener(this);
        newButton.addActionListener(this);
	}
  
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==queryButton) {
			if(item.getSelectedItem()==null) {}
			else {
				try {
					out.println("1");
					out.println(item.getSelectedItem().toString());
					newDialog = new ScheduleDialog(This,item.getSelectedItem().toString());
					newDialog.setVisible(true);
					newDialog.setBounds(100,100,360,300);   
					newDialog.setTitle(item.getSelectedItem().toString());  
					newDialog.setLocationRelativeTo(null);
					String h = new String(in.readLine());
					String m = new String(in.readLine());
					String c = new String(in.readLine());
					newDialog.hour.setSelectedItem(h);
					newDialog.minute.setSelectedItem(m);
					newDialog.content.setText(c);
					newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				} catch (IOException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(e.getSource()==deleteButton){
			if(item.getSelectedItem()==null) {}
			else {
					out.println("2");
					out.println(item.getSelectedItem().toString());
					for(int i = 0;i<This.list.size();i++) {
						if(This.list.get(i)[5].equals(item.getSelectedItem())) {
							This.list.remove(item.getSelectedItem());
						}
					}
					item.removeItem(item.getSelectedItem());
					This.update();
			}
		}
		else {
			if(input.getText().equals("")) {}
			else {
				out.println("3");
				item.addItem(input.getText());
				out.println(input.getText());
				This.update();
				String[] temp = {year,month,day,"0","0",input.getText(),""};
				This.list.add(temp);
				for(int i = 0;i<This.list.size();i++) {
					for(int j = 0;j<7;j++) {
						System.out.print(This.list.get(i)[j]+" ");
					}
					System.out.println("");
				}
			}
		}
	}
}