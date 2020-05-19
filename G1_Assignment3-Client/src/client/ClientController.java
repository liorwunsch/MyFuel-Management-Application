package client;

import java.io.IOException;

import guiClient.AFXML;
import ocsf.client.AbstractClient;

/**
 * all logic controllers extend this
 * 
 * @version Final
 * @author Elroy, Lior
 */
public abstract class ClientController extends AbstractClient {

	protected static boolean awaitResponse = false;
	protected AFXML currentWindow;
	protected Object lastMsgFromServer;

	/**
	 * super class constructor
	 * 
	 * @param host = "localhost"
	 * @param port = 5555
	 */
	public ClientController() {
		super("localhost", 5555);
	}

	/**
	 * updates <code>currentWindow</code> so
	 * <code>handleMessageFromClientUI()</code> will call
	 * <code>callAfterMessage()</code> of that window
	 * 
	 * @param currentWindow
	 */
	public void setCurrentWindow(AFXML currentWindow) {
		this.currentWindow = currentWindow;
	}

	/**
	 * updates <code>awaitResponse</code> so
	 * <code>handleMessageFromClientUI()</code> will continue
	 * <p>
	 * updates <code>lastMsgFromServer</code> so <code>callAfterMessage()</code> will use it
	 * 
	 * @param object
	 */
	@Override
	public void handleMessageFromServer(Object object) {
		System.out.println("message from server : " + object.toString());
		awaitResponse = false;
		this.lastMsgFromServer = object;
	}

	/**
	 * receives string from the window
	 * <p>
	 * opens connection to the server
	 * <p>
	 * sends the server a request accordingly
	 * <p>
	 * calls <code>callAfterMessage()</code> of <code>currentWindow</code>
	 * 
	 * @param message
	 */
	public abstract void handleMessageFromClientUI(String message);

	/**
	 * disconnects client from server and exits
	 */
	public void quit() {
		try {
			System.out.println("closing client connection");
			this.closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

}
