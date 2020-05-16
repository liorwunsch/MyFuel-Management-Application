package server;

import guiServer.ServerWindow;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ServerController extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	private Object lock;
	private ServerWindow serverWindow;
	private DatabaseController db;
	private ServerUserController serverUserController;

	/**
	 * @author Singleton - fix
	 */
	public ServerController(int port, Object lock, ServerWindow serverWindowController) {
		super(port);
		this.lock = lock;
		db = new DatabaseController(serverWindowController);
		this.serverWindow = serverWindowController;
		this.serverUserController = new ServerUserController(serverWindow, db);
	}

	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		String message = (String) msg;
		serverWindow.updateArea(client + ": " + message);

		if (message.startsWith("login") || message.startsWith("sign out"))
			serverUserController.handleMessageFromClient(msg, client);
	}

	protected void serverStarted() {
		synchronized (lock) {
			System.out.println("Server listening for connections on port " + getPort());
			serverWindow.updateArea("Server listening for connections on port " + getPort());
			lock.notifyAll();
		}
	}

	public void startListening() {
		try {
			this.listen(); // Start listening for connections
		} catch (Exception ex) {
			synchronized (lock) {
				System.out.println("ERROR - Could not listen for clients!");
				serverWindow.updateArea("ERROR - Could not listen for clients!");
				lock.notifyAll();
			}
		}
	}

	protected void serverStopped() {
		synchronized (lock) {
			System.out.println("Server has stopped listening for connections.");
			serverWindow.updateArea("Server has stopped listening for connections.");
			lock.notifyAll();
		}
	}

}