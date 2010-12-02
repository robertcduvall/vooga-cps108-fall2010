package vooga.engine.networking;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class GameSocket extends Thread{
	private BufferedReader inStream;
	protected PrintStream outStream;
	private Socket socket;

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

	public void send(String s) {
		outStream.println(s);
	}

	public String receive() throws IOException {
		return inStream.readLine();
	}

	public void run(){

	}

	public boolean isConnected() {
		return ((inStream != null) && (outStream != null) && (socket != null));
	}

	public void closeConnections() {
		try {
			socket.close();
			socket = null;
		}
		catch (IOException e) {
			System.out.println("Couldn’t close socket:" + e);
		}
	}

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
