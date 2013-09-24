package multithreadedHTTPServer;
///**
// * 
// */
//package se325assignment1;
//
//import java.io.BufferedReader;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
///**
// * @author Raoul D'Cunha
// * 
// */
//public class TCPServer {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		runserver();
//	}
//
//	private static void runserver() {
//		try {
//			ServerSocket socket = new ServerSocket(0);
//			InetAddress serverHost = InetAddress.getLocalHost();
//			System.out.println("Server destination: "
//					+ serverHost.getHostAddress() + ", "
//					+ socket.getLocalPort());
//
//			/**System.out.println("Test address: "
//					+ serverHost.getHostAddress() + ":"
//					+ socket.getLocalPort()+ "/index.html");
//
//			/* Repeatedly handle requests for processing. */
//			while (true) {
//				Socket clientConnection = socket.accept();
//				BufferedReader d = new BufferedReader(new InputStreamReader(
//						clientConnection.getInputStream()));
//
//
//				String temp = d.readLine();
//				
//				/**----------------------------------DO I NEED THIS????????????????????????--------------------------------------------------------**/
//				if (temp == null)
//				{
//					continue;
//				}
//				System.out.println("request: "+temp);
//
//				String path = temp.split(" ")[1];
//
//				path = System.getProperty("user.dir") + path;
//
//				//get extension
//				String extension = "";
//
//				int i = path.lastIndexOf('.');
//				if (i > 0) {
//					extension = path.substring(i + 1);
//				}
//				DataOutputStream out = new DataOutputStream(
//						clientConnection.getOutputStream());
//				try {
//					File f = new File(path);
//					if (!f.isFile())
//					{
//						throw new FileNotFoundException();
//					}
//					String header = generateHeaders(extension);
//					out.writeBytes(header);
//					out.write(readFile(f));
//				} catch (FileNotFoundException e) {
//					String output = "HTTP/1.0 404 Not Found\r\nDate: "
//							+ getDate()
//							+ "\r\nServer: SE325 Assignment 1 Server (rdcu001)\r\n"
//							+ "Content-type: text/html\r\n\r\n<html>\r\n" +
//							"<header><title>404 Not Found</title></header>\r\n" + "<body>\r\n"
//							+ "<h1>404 Not Found</h1>\r\n" + "</body>\r\n" + "</html>";
//					out.writeBytes(output);
//				}
//				//System.out.println(header);
//				/* Close this connection. */ 
//				clientConnection.close();
//			}
//			// socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	private static byte[] readFile(File f) throws IOException
//	{
//		FileInputStream fs = new FileInputStream(f);
//		/**---------------------------------------------------THIS MAY CAUSE PROBLEMS WHEN MULTITHREADING THIS SERVER------------------------------------------------**/
//		byte[] bArray = new byte[fs.available()];
//		fs.read(bArray);
//		fs.close();
//		return bArray;
//	}
//
//
//	private static String generateHeaders(String extension) throws FileNotFoundException
//	{
//		String contentType = "";
//		switch (extension){
//		case "html":
//			contentType = "text/html";
//			break;
//		case "htm":
//			contentType = "text/html";
//			break;
//		case "css":
//			contentType = "text/css";
//			break;
//		case "jpg":
//			contentType = "image/jpeg";
//			break;
//		case "gif":
//			contentType = "image/gif";
//			break;
//		default:
//			throw new FileNotFoundException("Invalid Content Type");
//		}
//		String header = "HTTP/1.0 200 ok\r\nDate: "
//				+ getDate()
//				+ "\r\nServer: SE325 Assignment 1 Server (rdcu001)\r\n"
//				+ "Content-type: "+contentType+"\r\n\r\n";
//		return header;
//	}
//
//
//	private static String getDate()
//	{
//		//Set Date Format
//		DateFormat df = new SimpleDateFormat(
//				"EEE, dd MM yy HH:mm:ss zzz");
//		//Format Date
//		String formattedDate = df.format(new Date());
//		return formattedDate;
//	}
//
//}
