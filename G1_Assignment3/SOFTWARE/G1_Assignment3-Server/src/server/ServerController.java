package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import database.DatabaseController;
import entities.Car;
import entities.Customer;
import entities.FastFuel;
import entities.HomeFuelOrder;
import entities.MarketingManager;
import entities.PricingModel;
import entities.PurchasingProgram;
import entities.User;
import entities.MyFuelStationManager;
import entities.MyNetManager;
import guiServer.ServerWindow;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

/**
 * controller for server
 * 
 * @version Final
 * @author Elroy, Lior, Vlad, Liad
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
	@Override
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		try {
			System.out.println(client + " : sent request to server");

			if (object instanceof User) {
				User user = (User) object;
				synchronized (this.lock) {
					this.serverWindow.updateArea(formatter.format(date) + " : " + client
							+ " : request : login with username " + user.getUsername());
					this.lock.notifyAll();
				}
				String function = user.getFunction();
				if (function.startsWith("login") || function.startsWith("sign out")) {
					ServerUserController.getInstance(serverWindow, databaseController, lock)
							.handleMessageFromClient(user, client);

				} else if (function.equals("get purchasing program")) {
					ServerCustomerController.getInstance(databaseController).handleMessageFromClient(user, client);
				}

			} else if (object instanceof HomeFuelOrder) {
				HomeFuelOrder homeFuelOrder = (HomeFuelOrder) object;
				synchronized (this.lock) {
					this.serverWindow
							.updateArea(formatter.format(date) + " : " + client + " : request : save homefuel order");
					this.lock.notifyAll();
				}
				ServerCustomerController.getInstance(databaseController).handleMessageFromClient(homeFuelOrder, client);

			} else if (object instanceof Car) {
				ServerMarketingRepresentativeController.getInstance(databaseController).handleMessageFromClient(object,
						client);

			} else if (object instanceof PurchasingProgram) {
				ServerMarketingRepresentativeController.getInstance(databaseController).handleMessageFromClient(object,
						client);

			} else if (object instanceof PricingModel) {
				ServerMarketingRepresentativeController.getInstance(databaseController).handleMessageFromClient(object,
						client);

			} else if (object instanceof FastFuel) {
				ServerFastFuelController.getInstance(databaseController).handleMessageFromClient(object, client);

			} else if (object instanceof String) {
				String str = (String) object;
				synchronized (this.lock) {
					this.serverWindow.updateArea(formatter.format(date) + " : " + client + " : request : " + str);
					this.lock.notifyAll();
				}
				if (str.startsWith("ack")) {
					client.sendToClient("ack");

				} else if (str.startsWith("activity")) {
					ServerUserController.getInstance(serverWindow, databaseController, lock)
							.handleMessageFromClient(str, client);

				} else if (str.startsWith("fastfuel")) {
					ServerCustomerController.getInstance(databaseController).handleMessageFromClient(str, client);

				} else if (str.startsWith("homefuel")) {
					ServerCustomerController.getInstance(databaseController).handleMessageFromClient(str, client);

				} else if (str.startsWith("getcustomerdetails") || str.startsWith("deletecustomer")
						|| str.startsWith("checkcustomer") || str.startsWith("getcustomercars")
						|| str.startsWith("deletecar") || str.startsWith("getAllPricingModelDiscounts")) {
					ServerMarketingRepresentativeController.getInstance(databaseController).handleMessageFromClient(str,
							client);
				} else if (str.startsWith("updatepassword")) {
					ServerCustomerController.getInstance(databaseController).handleMessageFromClient(str, client);

				} else if (str.startsWith("fuel_station_order")) {
					ServerSupplierController.getInstance(databaseController).handleMessageFromClient(str, client);
				}

			} else if (object instanceof Object[]) {
				Object[] objArr = (Object[]) object;
				if (objArr.length == 2 && objArr[0] instanceof User && objArr[1] instanceof Customer) {
					ServerMarketingRepresentativeController.getInstance(databaseController)
							.handleMessageFromClient(object, client);
				}

			} else if (object instanceof MarketingManager) {
				System.out.println(client + " requested MarketingManager ");
				MarketingManager manager = (MarketingManager) object;
				synchronized (this.lock) {
					this.serverWindow.updateArea(formatter.format(date) + " : " + client
							+ " : requested MarketingManager operation : " + manager.getUserName());
					this.lock.notifyAll();
				}
				ServerMarketingManagerController.getInstance(serverWindow, databaseController, lock)
						.handleMessageFromClient(manager, client);
				System.out.println(client + " end MarketingManager ");

			} else if (object instanceof MyFuelStationManager) {
				System.out.println(client + " requested FuelStationManager ");
				MyFuelStationManager manager = (MyFuelStationManager) object;
				this.serverWindow.updateArea(formatter.format(date) + " : " + client
						+ " : requesterd FuelStationManager operation : " + manager.getUserName());
				ServerFuelStationManagerController.getInstance(serverWindow, databaseController, lock)
						.handleMessageFromClient(manager, client);
				System.out.println(client + " end FuelStationManager ");

			} else if (object instanceof MyNetManager) {
				System.out.println(client + " requested NetworkManager ");
				MyNetManager netManager = (MyNetManager) object;
				this.serverWindow.updateArea(formatter.format(date) + " : " + client
						+ " : requested NetworkManager operation : " + netManager.getFunction());
				ServerNetworkManagerController.getInstance(databaseController).handleMessageFromClient(netManager,
						client);
				System.out.println(client + " end NetworkManager ");
			}

		} catch (IOException e) {
			e.printStackTrace();
			synchronized (this.lock) {
				this.serverWindow.updateArea(formatter.format(date) + " : " + e.getMessage());
				this.lock.notifyAll();
			}
		}
	}

	/**
	 * method that starts the server in order to listen for clients
	 */
	protected void serverStarted() {
		synchronized (this.lock) {
			this.serverWindow.updateArea("Server listening for connections on port " + getPort());
			this.lock.notifyAll();
		}
	}
	
	/**
	 * method that sets the server to listen mod
	 */
	public void startListening() {
		try {
			this.listen();
		} catch (Exception e) {
			synchronized (this.lock) {
				this.serverWindow.updateArea("Error - Could not listen for connections");
				this.lock.notifyAll();
			}
		}
	}
	
/**
 * method that stops the server from listening to clients
 */
	protected void serverStopped() {
		synchronized (this.lock) {
			this.serverWindow.updateArea("Server has stopped listening for connections");
			this.lock.notifyAll();
		}
	}

}
