package vooga.engine.networking;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Class to abstract dealing with sockets and streams away from the Vooga networking API users so they can simply send() and receive() messages from a socket.
 * 
 * @author Cue, Kolodziejzyk, Townsend
 * @version 1.0
 */
public class GameSocket extends Thread{
	private BufferedReader inStream;
	protected PrintStream outStream;
	private Socket socket;

	/**
	 * Save the socket as well as it's input and output streams.
	 * 
	 * @param sock the socket to communicate through.
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public GameSocket(Socket sock) {
		super("GameSocket");
		try {
			inStream = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			outStream = new PrintStream(new
					BufferedOutputStream(sock.getOutputStream(), 1024), true);
			socket = sock;
		}
		catch (IOException e) {
			System.out.println("Couldn’t initialize GameSocket:" + e);
			System.exit(1);
		}
	}

	/**
	 * Send a message to the socket.
	 * 
	 * @param s the message to send
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void send(String s) {
		outStream.println(s);
	}

	/**
	 * Read the latest message from the socket.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public String receive() throws IOException {
		return inStream.readLine();
	}

	public void run(){

	}

	/**
	 * Return if the GameSocket is still connected, meaning it can read and write from and to a socket.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public boolean isConnected() {
		return ((inStream != null) && (outStream != null) && (socket != null));
	}

	/**
	 * Close the socket.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	public void closeConnections() {
		try {
			if(socket != null)
				socket.close();
			socket = null;
		}
		catch (IOException e) {
			System.out.println("Couldn’t close socket:" + e);
		}
	}
	
	public Socket getSocket () {
		return socket;
	}

	/**
	 * Make sure the socket is closed and if not close it.
	 * 
	 * @author Cue, Kolodziejzyk, Townsend
	 * @version 1.0
	 */
	protected void finalize () {
		if (socket != null) {
			try {
				socket.close();
			}
			catch (IOException e) {
				System.out.println("Couldn’t close socket:" + e);
			}
			socket = null;
		}
	}
}
