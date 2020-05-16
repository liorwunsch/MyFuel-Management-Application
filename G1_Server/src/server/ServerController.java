package server;

import entities.User;
import guiServer.ServerWindow;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ServerController extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	private static ServerController instance;

	private Object lock;
	private ServerWindow serverWindow;
	private DatabaseController databaseController;

	public static ServerController getInstance(String host, String schema, int port, String username, String password,
			Object lock, ServerWindow serverWindow) {
		if (instance == null) {
			instance = new ServerController(host, schema, port, username, password, lock, serverWindow);
		}
		return instance;
	}

	private ServerController(String host, String schema, int port, String username, String password, Object lock,
			ServerWindow serverWindow) {
		super(port);
		this.lock = lock;
		this.serverWindow = serverWindow;
		this.databaseController = DatabaseController.getInstance(serverWindow, host, schema, username, password);
	}
	
	/**
	 * 
	 * @author Lior - add handling for other client controller / server***controller
	 */

	public void handleMessageFromClient(Object obj, ConnectionToClient client) {
		System.out.println(client + ": sent request to server");
		if (obj instanceof User) {
			User user = (User) obj;
			System.out.println(client + ": login with user : " + user);
			String function = user.getFunction();
			if (function.startsWith("login") || function.startsWith("sign out"))
				ServerUserController.getInstance(serverWindow, databaseController).handleMessageFromClient(user,
						client);
		}
	}

	protected void serverStarted() {
		synchronized (this.lock) {
			this.serverWindow.updateArea("Server listening for connections on port " + getPort());
			this.lock.notifyAll();
		}
	}

	public void startListening() {
		try {
			this.listen();
		} catch (Exception e) {
			synchronized (this.lock) {
				this.serverWindow.updateArea("ERROR - Could not listen for connections");
				this.lock.notifyAll();
			}
		}
	}

	protected void serverStopped() {
		synchronized (this.lock) {
			this.serverWindow.updateArea("Server has stopped listening for connections");
			this.lock.notifyAll();
		}
	}

}
