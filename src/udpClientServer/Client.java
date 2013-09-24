package udpClientServer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InetAddress serverAddress = InetAddress.getByName( args[ 0 ] );
			int serverPort = Integer.parseInt( args[ 1 ] );
			DatagramSocket socket = new DatagramSocket();
			int x = Integer.parseInt( args[ 2 ] );
			int y = Integer.parseInt( args[ 3 ] );
			ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream( );
			DataOutputStream outputDataStream = new DataOutputStream( outputByteStream );
			outputDataStream.writeInt( x );
			outputDataStream.writeInt( y );
			byte[ ] contents = outputByteStream.toByteArray( );
			DatagramPacket request = new DatagramPacket( contents, contents.length, serverAddress, serverPort );
			socket.send( request );
			byte[ ] buffer = new byte[ 4 ];
			DatagramPacket reply = new DatagramPacket( buffer, buffer.length );
			socket.receive( reply );
			ByteArrayInputStream inputByteStream = new ByteArrayInputStream( buffer );
			DataInputStream inputDataStream = new DataInputStream( inputByteStream );
			int product = inputDataStream.readInt( ); 
			System.out.println( "Received: " + product );
			socket.close( );
		}
		catch( NumberFormatException e ) {
			e.printStackTrace( );
		}
		catch( IOException e ) {
			e.printStackTrace( );
		}
	}
}
