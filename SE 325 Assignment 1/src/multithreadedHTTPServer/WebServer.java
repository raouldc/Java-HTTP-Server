package multithreadedHTTPServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {
	public static void main(String[] args){
		ExecutorService executor = Executors.newCachedThreadPool();
		ServerSocket socket;
		try {
			socket = new ServerSocket(0);

			InetAddress serverHost;

			serverHost = InetAddress.getLocalHost();

			System.out.println("Server destination: "
					+ serverHost.getHostAddress() + ", "
					+ socket.getLocalPort());
			while(true){
				Runnable worker = new ServerWorkerThread(socket.accept());
				executor.execute(worker);
			}
			
			
			
		}
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
