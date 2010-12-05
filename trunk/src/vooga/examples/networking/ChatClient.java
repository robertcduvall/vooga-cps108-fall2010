package vooga.examples.networking;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {

	private ChatConnection connection;

	public ChatClient() {
	}

	public void initConnection() throws UnknownHostException, IOException {
			connection = new ChatConnection();

	}

	public void sendMessage(String betch) {

		connection.sendMessage(betch);
	}

	public void getMessage() {
		System.out.println(connection.getTheirMessage());
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		ChatClient chat = new ChatClient();
		chat.initConnection();
		Scanner scanner = new Scanner(System.in);
		System.out
				.println("please type something to say to the other jamboy: ");
		String userInput = scanner.nextLine();
		if (userInput != null) {
			chat.sendMessage(userInput);
		} else {
			System.out.println("you're an idiot");
		}

		chat.getMessage();
	}

}