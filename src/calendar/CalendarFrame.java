package calendar;

import java.awt.*;   
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

import javax.swing.*;

import calendar.ServerFrame.Mainthread;  
 
public class CalendarFrame extends JFrame implements ActionListener   
{   
       /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton[] ButtonDay=new JButton[42];   
       JTextField text=new JTextField(10);  
       JLabel titleName[]=new JLabel[7];   
       JButton button = new JButton();
       JButton disconnect = new JButton("断开");
       String name[]={"日","一","二","三", "四","五","六"};   
       JButton nextMonth,previousMonth;   
       int year=1996,month=1; //启动程序显示的日期信息  
       CalendarBean calendar;   
       JLabel showMessage=new JLabel("",JLabel.CENTER);
       JLabel currentdate = new JLabel("",JLabel.CENTER);
       JLabel Inputyear = new JLabel("请输入年份：");
       BufferedReader in;
   	   PrintWriter out;
   	   ArrayList<String[]>list = new ArrayList<String[]>();
      
   	   class Timerthread implements Runnable{
   		@Override
   			public void run() {
   			String year;
   			String month;
   			String day;
   			String hour;
   			String minute;
   			
   			while(true) {
   				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
   				Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
   				 year = Integer.toString(c.get(Calendar.YEAR)); 
   				month = Integer.toString(c.get(Calendar.MONTH)+1); 
   				day = Integer.toString(c.get(Calendar.DATE)); 
   				hour = Integer.toString(c.get(Calendar.HOUR_OF_DAY));  
   				minute = Integer.toString(c.get(Calendar.MINUTE));
   				System.out.println(year+" "+month+" "+day+" "+hour+" "+minute);
   				for(int i =0;i<list.size();i++) {
   					if(list.get(i)[0].equals(year)
   					   &&list.get(i)[1].equals(month)
   					   &&list.get(i)[2].equals(day)
   					   &&list.get(i)[3].equals(hour)
   					   &&list.get(i)[4].equals(minute)) {
   						String str =  list.get(i)[5]+":"+ list.get(i)[6];
   						JOptionPane.showMessageDialog(null,str);
   					}
   				}
   			}
   			
   			}
   		}
       public void update(){
    	   out.println("6");
    	   out.println(Integer.toString(calendar.getYear()));
    	   out.println(Integer.toString(calendar.getMonth()));
    	   int count;
    	   for(int x=0;x<42;x++) {
    		   ButtonDay[x].setBackground(Color.DARK_GRAY);
    	   }
		try {
		   count = Integer.parseInt(in.readLine());
		   if(count==0) {
			   for(int x=0;x<42;x++)   
			   { 
				   ButtonDay[x].setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
				   ButtonDay[x].setBackground(Color.DARK_GRAY);
				   if(x%7==6) {
						   ButtonDay[x].setForeground(new Color(0,183,239));
						   ButtonDay[x].setBackground(Color.DARK_GRAY);
				   }
				   else if(x%7==0){
						   ButtonDay[x].setForeground(new Color(0,183,239));
						   ButtonDay[x].setBackground(Color.DARK_GRAY);
				   }
				   else {
					   ButtonDay[x].setForeground(Color.white);
					   ButtonDay[x].setBackground(Color.DARK_GRAY);
				   }
			   }
		   }else {
			   for(int i = 0;i<count;i++) {
				   String temp = new String(in.readLine());
				   for(int x=0;x<42;x++)   
				   { 
					   if(ButtonDay[x].getText()==null) {
						   continue;
					   }
					   else if(ButtonDay[x].getText().equals(temp)) {
						   ButtonDay[x].setForeground(Color.white);
						   ButtonDay[x].setBackground(new Color(229,63,63));
						   ButtonDay[x].setFont(new Font("汉仪雅酷黑W",Font.BOLD,20));
					   }else {
						   ButtonDay[x].setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));

						   if(x%7==6) {
							   ButtonDay[x].setForeground(new Color(0,183,239));
							   if(!ButtonDay[x].getBackground().equals(new Color(229,63,63))) {
								   ButtonDay[x].setBackground(Color.DARK_GRAY);
							   }
						   }
						   else if(x%7==0){
								   ButtonDay[x].setForeground(new Color(0,183,239));
								   if(!ButtonDay[x].getBackground().equals(new Color(229,63,63))) {
									   ButtonDay[x].setBackground(Color.DARK_GRAY);
								   }
						   }
						   else {
							   ButtonDay[x].setForeground(Color.white);
							   if(!ButtonDay[x].getBackground().equals(new Color(229,63,63))) {
								   ButtonDay[x].setBackground(Color.DARK_GRAY);
							   }
						   }
					   }
				   }
			   }
		   }
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
       public CalendarFrame()   
       {
       	   setForeground(Color.DARK_GRAY);   
           JPanel pCenter=new JPanel();   
           pCenter.setBackground(Color.DARK_GRAY);
             
         //将pCenter的布局设置为7行7列的GridLayout 布局。   
           pCenter.setLayout(new GridLayout(7,7));    
             
         //pCenter添加组件titleName[i]  
           for(int i=0;i<7;i++)   
           {   
               titleName[i]=new JLabel(name[i],JLabel.CENTER);   
               pCenter.add(titleName[i]);
               titleName[i].setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
               if(i!=0&&i!=6) {
            	   titleName[i].setForeground(Color.white);
               }
               else {
            	   titleName[i].setForeground(new Color(0,183,239));
               }
           }   
 
         //pCenter添加组件labelDay[i]  
           for(int i=0;i<42;i++)   
           {   
               ButtonDay[i] = new JButton();   
               pCenter.add(ButtonDay[i]);
               
           }   
           try {
        	   in = new BufferedReader(new InputStreamReader(LoginFrame.getInstance().getInputStream()));
        	   out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(LoginFrame.getInstance().getOutputStream())),true);
           } catch (IOException e) {
			// TODO Auto-generated catch block
        	   e.printStackTrace();
           }
			
           text.addActionListener(this);  
           calendar=new CalendarBean();   
           calendar.setYear(year);   
           calendar.setMonth(month);   
           String day[]=calendar.getCalendar();   
 
           for(int i=0;i<42;i++)   
           {   
        	   ButtonDay[i].setBackground(Color.DARK_GRAY);
               ButtonDay[i].setText(day[i]);
               if(ButtonDay[i].getText()==null)
               {
            	   ButtonDay[i].setVisible(false);
               }
               else
               {
               	   ButtonDay[i].setVisible(true);
               }
           }   
 
           nextMonth=new JButton("下月");   
           previousMonth=new JButton("上月");   
           button=new JButton("确定");  
             
           //注册监听器  
           nextMonth.addActionListener(this);   
           previousMonth.addActionListener(this);   
           button.addActionListener(this);
           disconnect.addActionListener(this);
           for(int i=0;i<42;i++)   
           {   
               ButtonDay[i].addActionListener(this);
           }
           
           JPanel pNorth=new JPanel(),  
           pSouth=new JPanel();   
           pNorth.add(showMessage);
           pNorth.add(currentdate);
           pSouth.add(Inputyear);          
           pSouth.add(text);  
           pSouth.add(button);    
           pSouth.add(previousMonth);   
           pSouth.add(nextMonth); 
           pSouth.add(disconnect);
           pSouth.setBackground(Color.DARK_GRAY);
           pNorth.setBackground(Color.DARK_GRAY);
           previousMonth.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
           previousMonth.setBackground(new Color(0,183,239));
           previousMonth.setForeground(Color.white);
           disconnect.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
           disconnect.setBackground(new Color(0,183,239));
           disconnect.setForeground(Color.white);
           nextMonth.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
           nextMonth.setBackground(new Color(0,183,239));
           nextMonth.setForeground(Color.white);
           button.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
           button.setBackground(new Color(0,183,239));
           button.setForeground(Color.white);
           Inputyear.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
           Inputyear.setForeground(Color.white);
           text.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,20));
           text.setForeground(Color.DARK_GRAY);
           showMessage.setText("日历：");
           showMessage.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,30));
           showMessage.setForeground(Color.white);
           currentdate.setText(calendar.getYear()+"."+ calendar.getMonth());
           currentdate.setFont(new Font("汉仪雅酷黑W",Font.PLAIN,30));
           currentdate.setForeground(Color.white);
           ScrollPane scrollPane=new ScrollPane();   
           scrollPane.add(pCenter);   
           getContentPane().add(scrollPane,BorderLayout.CENTER);// 窗口添加scrollPane在中心区域   
           getContentPane().add(pNorth,BorderLayout.NORTH);// 窗口添加pNorth 在北面区域   
           getContentPane().add(pSouth,BorderLayout.SOUTH);// 窗口添加pSouth 在南区域。  
           out.println("8");
           try {
			int count = Integer.parseInt(in.readLine());
			for(int i = 0;i<count;i++) {
				String[] temp = {in.readLine(),"","","","","",""};
				list.add(temp);
			}
			for(int i = 0;i<count;i++) {
				list.get(i)[1] = in.readLine();
			}
			for(int i = 0;i<count;i++) {
				list.get(i)[2] = in.readLine();
			}
			for(int i = 0;i<count;i++) {
				list.get(i)[3] = in.readLine();
			}
			for(int i = 0;i<count;i++) {
				list.get(i)[4] = in.readLine();
			}
			for(int i = 0;i<count;i++) {
				list.get(i)[5] = in.readLine();
			}
			for(int i = 0;i<count;i++) {
				list.get(i)[6] = in.readLine();
			}
			for(int i = 0;i<list.size();i++) {
				for(int j = 0;j<7;j++) {
					System.out.print(list.get(i)[j]+" ");
				}
				System.out.println("");
			}
			Thread mainthread = new Thread(new Timerthread());
			mainthread.start();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
           update();
        }   
        
        public void actionPerformed(ActionEvent e)   
        {   
            if(e.getSource()==nextMonth)   
            {   
                month=month+1;   
                if(month>12)   
                {
                	year++;
                	calendar.setYear(year);   
                	month=1;   
                }
                calendar.setMonth(month);   
                String day[]=calendar.getCalendar();   
 
                for(int i=0;i<42;i++)   
                {  
                    ButtonDay[i].setText(day[i]);
                    if(ButtonDay[i].getText()==null)
                    {
                 	   ButtonDay[i].setVisible(false);
                    }
                    else
                    {
                    	ButtonDay[i].setVisible(true);
                    }
                }
                update();
             }   
            else if(e.getSource()==previousMonth)   
            {   
                month=month-1;                
                if(month<1)   
                {
                	year--;
                	calendar.setYear(year);
                	month=12;   
                }
                calendar.setMonth(month);   
                String day[]=calendar.getCalendar();
 
                for(int i=0;i<42;i++)   
                {   
                    ButtonDay[i].setText(day[i]);
                    if(ButtonDay[i].getText()==null)
                    {
                 	   ButtonDay[i].setVisible(false);
                    }
                    else
                    {
                    	ButtonDay[i].setVisible(true);
                    }
                }
                update();
             }   
            else if(e.getSource()==button)  
            {  
            	year = Integer.parseInt(text.getText());
                calendar.setYear(Integer.parseInt(text.getText()));               
                String day[]=calendar.getCalendar();                  
                for(int i=0;i<42;i++)  
                {  
                    ButtonDay[i].setText(day[i]);
                    if(ButtonDay[i].getText()==null)
                    {
                 	   ButtonDay[i].setVisible(false);
                    }
                    else
                    {
                    	ButtonDay[i].setVisible(true);
                    }
                }
                update();
            }
            else if(e.getSource()==disconnect) {
            	Socket socket = LoginFrame.getInstance();
            	out.println("7");
            	try {
            		in.close();
            		out.close();
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
            else {
            	for(int i =0;i<42;i++) {
            		if(e.getSource()==ButtonDay[i]){
            			SelectDialog newDialog;
						try {
							newDialog = new SelectDialog(calendar.getYear(),calendar.getMonth(),ButtonDay[i].getText(),this);
							newDialog.setVisible(true);
							this.setEnabled(false);
							newDialog.setBounds(100,100,450,130);   
							newDialog.setTitle(calendar.getYear()+"年"+calendar.getMonth()+"月"+ButtonDay[i].getText()+"日备忘录");  
							newDialog.setLocationRelativeTo(null);//窗体居中显示
							newDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							newDialog.setResizable(false);
							CalendarFrame This=this;
							newDialog.addWindowListener(new WindowAdapter() {  
						        //添加第二个界面的关闭事件:
						            public void windowClosing(WindowEvent e) {  
						              //添加事件:
						              This.setEnabled(true);//将主界面再设置为可操作的
						         }           
								});
							}catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
            		}
            	}
            }
            currentdate.setText(calendar.getYear()+"."+ calendar.getMonth());   
       }   
}