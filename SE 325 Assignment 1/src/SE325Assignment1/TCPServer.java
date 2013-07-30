/**
 * 
 */
package SE325Assignment1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Raoul D'Cunha
 *
 */
public class TCPServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runserver();
	}
	
	private static void runserver()
	{
		try {
			ServerSocket socket = new ServerSocket( 0 );
			InetAddress serverHost = InetAddress.getLocalHost( );
			System.out.println( "Server destination: " + serverHost.getHostAddress( ) + ", "+ socket.getLocalPort( ) );
			/* Repeatedly handle requests for processing. */
			while( true ) {
				Socket clientConnection = socket.accept( );
				BufferedReader d = new BufferedReader(new InputStreamReader(clientConnection.getInputStream( )));
				
				
				DataOutputStream out = new DataOutputStream( clientConnection.getOutputStream( ) );

				String output = d.readLine();
				
				System.out.println(output);
			
				out.writeBytes(output);
				
				
				/* Close this connection. */
				clientConnection.close( );
			}
			//socket.close();
		}
		catch( IOException e ) {
			e.printStackTrace( );
		}
	}

}
