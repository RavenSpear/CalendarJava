package calendar;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.ArrayList;

public class ServerThread extends Thread{
	Socket socket;
	BufferedReader in; 
	PrintWriter out;
	String identifier = new String("");
	String content = new String("");
	String username;
	String hour = new String("59");
	String minute = new String("59");
	String title = new String("");
	String year = new String("1960");
	String month = new String("12");
	String day = new String("20");
	Connection connection;
	Statement statement;
	ResultSet rs;
	boolean isconnected = true;
	
	public ServerThread(Socket socket) throws IOException{
		ServerDaemon.CURRENT_THREADS++;
		this.socket = socket;
	}
	
	public void run(){
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			String username = new String(in.readLine());
			//String dbURL = "jdbc:mysql://127.0.0.1:3306/" +
					//"MyDB?user=root&password=123456?useUnicode=true&characterEncoding=utf8";
			String dbURL = "jdbc:mysql://10.203.240.54:3306/MyDB?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC";
				// 加载及注册JDBC驱动程序
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(dbURL,"root","123456");
				statement = connection.createStatement();
				 if (!connection.isClosed())
					 System.out.println("Successfully connected to MySQL server...");
				 DatabaseMetaData meta = connection.getMetaData();
				 rs = meta.getTables(null, null, username, null);
				 if(rs.next()) {
					 System.out.println("已存在用户！");
				 }
				 else {
				 statement.executeUpdate ("CREATE TABLE "+username+" ("+
						 				  "id INT UNSIGNED NOT NULL AUTO_INCREMENT,"+
						 				  "PRIMARY KEY (id),"+
						 				  "year CHAR(5), month CHAR(3), day CHAR(3), "
						 				  + "hour CHAR(3), minute CHAR(3), title CHAR(10), content CHAR(40))"); 					 
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			System.out.println(username+"登录成功！");
			while(isconnected) {
				identifier = in.readLine();
				System.out.println("识别符为："+identifier);
				if(identifier.equals("1")) {
					System.out.println("查询！");
					title = in.readLine();
					statement.executeQuery("SELECT hour,minute,content" +" FROM "+username+" WHERE title = '"+title+"' AND year = '"
							+year+"' AND month = '"+month+"' AND day = '"+day+"'"); 
					rs = statement.getResultSet ();
					while(rs.next()) {
						hour = rs.getString("hour");
						minute = rs.getString("minute");
						content = rs.getString("content");
					}
					out.println(hour);
					out.println(minute);
					out.println(content);
				}else if(identifier.equals("2")) {
					title = in.readLine();
					System.out.println("删除:"+title);
					statement.executeUpdate("DELETE FROM "+username+" WHERE title = '"+title+"'");
				}else if(identifier.equals("3")) {
					title = in.readLine();
					statement.executeUpdate ("INSERT INTO "+username+" (year,month,day,title,hour,minute,content)"
					+ " VALUES"+"('"+year+"','"+month+"','"+day+"','"+title+"','0','0','')");
					System.out.println("添加:"+title);
				}else if(identifier.equals("4")) {
					hour = in.readLine();
					minute = in.readLine();
					content = in.readLine();
					statement.executeUpdate ("UPDATE "+username+" SET hour = '"+hour+"',minute = '"+minute+"',content = '"
											 +content+"' WHERE title = '"+title+"' AND year = '"+year
											 +"' AND month = '"+month+"' AND day = '"+day+"'");
					System.out.println("保存:"+hour+minute+content);
				}else if(identifier.equals("5")) {
					year = in.readLine();
					month = in.readLine();
					day = in.readLine();
					statement.executeQuery("SELECT title" +" FROM "+username
											+" WHERE year = '"+year+"' AND month = '"+month+"' AND day = '"+day+"'");
					rs = statement.getResultSet();
					ArrayList<String> list = new ArrayList<String>();
					Integer count = 0;
					while (rs.next()) {
						list.add(rs.getString("title"));
						count++;
					}
					out.println(count.toString());
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					System.out.println("初始化"+year+month+day);
				}
				else if(identifier.equals("6")){
					year = in.readLine();
					month = in.readLine();
					statement.executeQuery("SELECT day" +" FROM "+username+" WHERE year = '"
							+year+"' AND month = '"+month+"'"); 
					rs = statement.getResultSet();
					ArrayList<String> list = new ArrayList<String>();
					Integer count = 0;
					while (rs.next()) {
						list.add(rs.getString("day"));
						count++;
					}
					out.println(count.toString());
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					System.out.println("更新"+list);
				}
				else if(identifier.equals("7")) {
					System.out.println("客户端断开连接");
					ServerDaemon.CURRENT_THREADS--;
					isconnected = false;
					in.close();
					out.close();
					rs.close();
					socket.close();
				}
				else if(identifier.equals("8")) {
					System.out.println("初始化时间表");
					statement.executeQuery("SELECT year" +" FROM "+username); 
					rs = statement.getResultSet();
					ArrayList<String> list = new ArrayList<String>();
					Integer count = 0;
					while (rs.next()) {
						list.add(rs.getString("year"));
						count++;
					}
					out.println(count.toString());
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					statement.executeQuery("SELECT month" +" FROM "+username); 
					rs = statement.getResultSet();
					list.clear();
					while (rs.next()) {
						list.add(rs.getString("month"));
					}
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					statement.executeQuery("SELECT day" +" FROM "+username); 
					rs = statement.getResultSet();
					list.clear();
					while (rs.next()) {
						list.add(rs.getString("day"));
					}
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					statement.executeQuery("SELECT hour" +" FROM "+username); 
					rs = statement.getResultSet();
					list.clear();
					while (rs.next()) {
						list.add(rs.getString("hour"));
					}
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					statement.executeQuery("SELECT minute" +" FROM "+username); 
					rs = statement.getResultSet();
					list.clear();
					while (rs.next()) {
						list.add(rs.getString("minute"));
					}
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					statement.executeQuery("SELECT title" +" FROM "+username); 
					rs = statement.getResultSet();
					list.clear();
					while (rs.next()) {
						list.add(rs.getString("title"));
					}
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
					statement.executeQuery("SELECT content" +" FROM "+username); 
					rs = statement.getResultSet();
					list.clear();
					while (rs.next()) {
						list.add(rs.getString("content"));
					}
					for(int i =0;i<count;i++) {
						out.println(list.get(i));
					}
				}
				else {
					System.out.println("wrong case.");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}