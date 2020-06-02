package server;

import database.DatabaseController;
import entities.User;
import guiServer.ServerWindow;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * controller for server
 * 
 * @version 1 Method To Final
 * @see handleMessageFromClient()
 * @author Elroy, Lior
 */
public class ServerController extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	private static ServerController instance;

	private Object lock;
	private ServerWindow serverWindow;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerController(String host, String schema, int port, String username, String password, Object lock,
			ServerWindow serverWindow) {
		super(port);
		this.lock = lock;
		this.serverWindow = serverWindow;
		this.databaseController = DatabaseController.getInstance(serverWindow, host, schema, username, password);
	}

	/**
	 * requests an instance of database controller with predetermined info
	 * 
	 * @return instance of this class
	 */
	public static ServerController getInstance(String host, String schema, int port, String username, String password,
			Object lock, ServerWindow serverWindow) {
		if (instance == null) {
			instance = new ServerController(host, schema, port, username, password, lock, serverWindow);
		}
		return instance;
	}

	/**
	 * reroutes request from client to the appropriate controller
	 * 
	 * @param object
	 * @param client
	 */
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		System.out.println(client + ": sent request to server");
		if (object instanceof User) {
			User user = (User) object;
			System.out.println(client + ": login with user : " + user);
			String function = user.getFunction();
			if (function.startsWith("login") || function.startsWith("sign out"))
				ServerUserController.getInstance(serverWindow, databaseController, lock).handleMessageFromClient(user,
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
