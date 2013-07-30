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
			/* Repeatedly handle requests for processing. */
			while (true) {
				Socket clientConnection = socket.accept();
				BufferedReader d = new BufferedReader(new InputStreamReader(
						clientConnection.getInputStream()));
				String temp = d.readLine();
				System.out.print(temp);

				String path = temp.split(" ")[1];

				path = System.getProperty("user.dir") + path;
				String extension = "";

				int i = path.lastIndexOf('.');
				if (i > 0) {
					extension = path.substring(i + 1);
				}
				System.out.print(extension);

				if (extension.equals("html")) {
					DataOutputStream out = new DataOutputStream(
							clientConnection.getOutputStream());

					DateFormat df = new SimpleDateFormat(
							"EEE, dd MM yy HH:mm:ss zzz");
					String formattedDate = df.format(new Date());

					// String output = d.readLine();

					String output = "HTTP/1.0 200 ok\r\nDate: "
							+ formattedDate
							+ "\r\nServer: SE325 Assignment 1 Server (rdcu001)\r\n"
							+ "Content-type: text/html";

					File f = new File(path);
					try {
						FileInputStream fs = new FileInputStream(f);
						int end = 0;
						while (end != -1) {
							out.write(fs.read());
						}

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					/**
					 * output+="\r\n\r\n<html>\r\n" +
					 * "<header><title>Test</title></header>\r\n" + "<body>\r\n"
					 * + "Hello world\r\n" + "</body>\r\n" + "</html>";
					 **/
					System.out.println(output);

					out.writeBytes(output);

					/* Close this connection. */

				}

				clientConnection.close();

			}
			// socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
