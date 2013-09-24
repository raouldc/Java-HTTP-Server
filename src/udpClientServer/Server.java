package udpClientServer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DatagramSocket socket = new DatagramSocket( );
			InetAddress serverHost = InetAddress.getLocalHost( );
			System.out.println( "Server destination: " + serverHost.getHostAddress() + ", " + socket.getLocalPort( ) );
			byte[ ] buffer = new byte[ 8 ];
			while( true ) {
				DatagramPacket request = new DatagramPacket( buffer, buffer.length );
				socket.receive( request );
				ByteArrayInputStream inputByteStream = new ByteArrayInputStream( buffer );
				DataInputStream inputDataStream = new DataInputStream( inputByteStream );
				int x = inputDataStream.readInt();
				int y = inputDataStream.readInt();
				int product = x * y;
				ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();
				DataOutputStream outputDataStream = new DataOutputStream( outputByteStream );
				outputDataStream.writeInt( product );
				byte[ ] contents = outputByteStream.toByteArray( );
				DatagramPacket response = new DatagramPacket( contents, contents.length, request.getAddress( ), request.getPort( ) );
				socket.send( response );
			}
		}
		catch( IOException e ) {
			e.printStackTrace();
		}

	}

}
