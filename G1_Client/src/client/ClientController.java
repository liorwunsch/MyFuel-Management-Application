package client;

import java.io.IOException;

import ocsf.client.AbstractClient;

/**
 * 
 * @author Lior - don't change
 *
 */
public abstract class ClientController extends AbstractClient {

	protected String lastMsg;

	public ClientController(String host, int port) {
		super(host, port);
	}

	public abstract void handleMessageFromServer(Object obj);

	public abstract void handleMessageFromClientUI(String message);

	public void quit() {
		try {
			System.out.println("closing client connection");
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

}
