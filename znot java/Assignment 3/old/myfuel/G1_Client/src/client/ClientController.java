package client;

import java.io.IOException;

import ocsf.client.AbstractClient;

public abstract class ClientController extends AbstractClient {

	/**
	 * @author do we need to fix this - awaitResponse is shared?
	 */
	protected static boolean awaitResponse = false;
	public String lastMsg;

	public ClientController(String host, int port) {
		super(host, port);
	}

	public abstract void handleMessageFromServer(Object msg);

	public abstract void handleMessageFromClientUI(String message);

	public void display(String message) {
		System.out.println("> " + message);
	}

	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

}
