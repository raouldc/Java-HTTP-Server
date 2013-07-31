/**
 * 
 */
package SE325Assignment1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	private static void runserver() {
		try {
			ServerSocket socket = new ServerSocket(0);
			InetAddress serverHost = InetAddress.getLocalHost();
			System.out.println("Server destination: "
					+ serverHost.getHostAddress() + ", "
					+ socket.getLocalPort());

			/**System.out.println("Test address: "
					+ serverHost.getHostAddress() + ":"
					+ socket.getLocalPort()+ "/index.html");

			/* Repeatedly handle requests for processing. */
			while (true) {
				Socket clientConnection = socket.accept();
				BufferedReader d = new BufferedReader(new InputStreamReader(
						clientConnection.getInputStream()));


				String temp = d.readLine();

				if (temp == null)
				{
					continue;
				}
				System.out.println("request: "+temp);

				String path = temp.split(" ")[1];

				path = System.getProperty("user.dir") + path;

				//get extension
				String extension = "";

				int i = path.lastIndexOf('.');
				if (i > 0) {
					extension = path.substring(i + 1);
				}
				System.out.println(extension);

				if (extension.equals("html")) {
					DataOutputStream out = new DataOutputStream(
							clientConnection.getOutputStream());

					String header = generateHeaders(extension);


					try {
						out.writeBytes(header);
						out.write(readFile(path));
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/**
					 * output+="\r\n\r\n<html>\r\n" +
					 * "<header><title>Test</title></header>\r\n" + "<body>\r\n"
					 * + "Hello world\r\n" + "</body>\r\n" + "</html>";
					 **/
					System.out.println(header);
					/* Close this connection. */ 
				}
				clientConnection.close();
			}
			// socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static byte[] readFile(String path) throws IOException
	{
		File f = new File(path);
		FileInputStream fs = new FileInputStream(f);
		byte[] bArray = new byte[fs.available()];
		fs.read(bArray);
		fs.close();
		return bArray;
	}


	private static String generateHeaders(String extension)
	{
		String contentType = "";
		switch (extension){
		case "html":
			contentType = "text/html";
			break;
		}
		
		//Set Date Format
		DateFormat df = new SimpleDateFormat(
				"EEE, dd MM yy HH:mm:ss zzz");
		//Format Date
		String formattedDate = df.format(new Date());

		String header = "HTTP/1.0 200 ok\r\nDate: "
				+ formattedDate
				+ "\r\nServer: SE325 Assignment 1 Server (rdcu001)\r\n"
				+ "Content-type: "+contentType+"\r\n\r\n";
		return header;
	}
}
