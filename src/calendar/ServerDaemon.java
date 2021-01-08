package calendar;

import java.net.ServerSocket;

public class ServerDaemon {
	public static final int PORT = 8080;
	private static final int MAX_THREADS = 10;
	public static int CURRENT_THREADS = 0;
	public static int showcurrent() {
		return CURRENT_THREADS;
	}
	public void close() {
		try{
			server.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	ServerSocket server;
	
	public ServerDaemon(){
		try{
			server = new ServerSocket(PORT);
			System.out.println("·þÎñÆ÷Æô¶¯");
			while(true){
				if(CURRENT_THREADS<MAX_THREADS){
					ServerThread thread = new ServerThread(server.accept());
					System.out.println(CURRENT_THREADS);
					thread.start();
				}
			}			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				server.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}