package vooga.engine.networking;
/* // // Java 109 : Networking with Java, by David Reilly // // TCP_Finger_Client // // TCP finger client connects to the host // and requests information on a single user // // Params : TCP_Finger_Client username@host.com // */ 
import java.io.*; 
import java.net.*; 

public class TCPFingerClient {
	
	public static void main ( String args[] ) { 
		// Check command line paramaters
		if (args.length != 0) { 
			System.err.println ("Invalid number of paramaters"); System.exit(1); 
		} 
		else {
			// Check for existence of @ in paramater 
//			if (args[0].indexOf("@") == -1) { 
//				System.err.println ("Invalid paramater : syntax user@host"); System.exit(1); 
//			} 
			// Split command line paramater at the @ character
			String name = "acue@Adam-MacBook-Pro.local";
			String username = name.substring(0, name.indexOf("@")); 
			String hostname = name.substring(name.indexOf("@") +1, name.length()); 
			try { 
				System.out.println ("Connecting to " + hostname); 
				// Create a connection to server
				Socket s = new Socket(hostname, 79);
				// Create input and output streams to socket 
				PrintStream out = new PrintStream(s.getOutputStream()); 
				DataInputStream temp = new DataInputStream(s.getInputStream()); 
				BufferedReader in = new BufferedReader(new InputStreamReader(temp));
				// Write username to socket output
				out.println( username );
				// Read response from socket
				String line = in.readLine();
				while (line != null) { 
					System.out.println(line);
					line = in.readLine(); 
				} 
				// Terminate connection
				s.close();
			} 
			catch (SocketException e ){ 
				System.err.println ("Socket error : " + e); 
			} 
			catch (UnknownHostException e ){ 
				System.err.println ("Invalid host!");
			} 
			catch (IOException e ){
				System.err.println ("I/O error : " + e); 
			}
		}
	}
}