
package server;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import database.DatabaseController;
import entities.MarketingManager;
import guiServer.ServerWindow;
import ocsf.server.ConnectionToClient;

/**
 * controller for client login and signout on server
 * 
 * @version Final
 * @author Elroy, Lior
 */
public class ServerMarketingManagerController {

	private static ServerMarketingManagerController instance;

	private Object lock;
	private ServerWindow serverWindow;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerMarketingManagerController(ServerWindow serverWindow, DatabaseController databaseController,
			Object lock) {
		super();
		this.lock = lock;
		this.serverWindow = serverWindow;
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerMarketingManagerController getInstance(ServerWindow serverWindow,
			DatabaseController databaseController, Object lock) {
		if (instance == null) {
			instance = new ServerMarketingManagerController(serverWindow, databaseController, lock);
		}
		return instance;
	}

	/**
	 * handles client request and sends it to the database controller sends result
	 * got from database controller back to the client
	 * 
	 * @param object
	 * @param client
	 */
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date date = new Date();
			Object result = null;
			if (object instanceof MarketingManager) {
				MarketingManager manager = (MarketingManager) object;
				String function = manager.getFunction();

				//////////////////////// functions /////////////////////
				if (function.startsWith("pull sales patterns")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : request pull of sales patterns ");
						this.lock.notifyAll();
					}
					result = this.databaseController.getAllSalePatterns();

				} else if (function.startsWith("genAnalysis")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client + " : generate analysis ");
						this.lock.notifyAll();
					}
					result = this.databaseController.generateAnalysis();

				} else if (function.startsWith("pull product in sales patterns")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : pull product in sales patterns ");
						this.lock.notifyAll();
					}
					result = this.databaseController.getAllProductInSalePatterns();

				} else if (function.startsWith("check active sales")) {
					String[] msgarr = function.split(" ");
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request check active sales for paternID '" + msgarr[3] + "'");
						this.lock.notifyAll();
					}
					result = this.databaseController.checkActiveOfSale(Integer.parseInt(msgarr[3]));

				} else if (function.startsWith("insert sale")) {
					String[] msgarr = function.split(" ");
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request insert sale with patter ID: '" + msgarr[2] + "'");
						this.lock.notifyAll();
					}
					int[] values = new int[msgarr.length - 2];
					for (int i = 0; i < values.length; i++) {
						values[i] = Integer.parseInt(msgarr[2 + i]);
					}
					result = this.databaseController.insertNewSale(values);

				} else if (function.startsWith("add activity")) {
					String[] msgarr = function.split(" ");
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : request add activity of : " + msgarr[2]);
						this.lock.notifyAll();
					}
					int[] values = new int[5];
					for (int i = 0; i < values.length; i++) {
						values[i] = Integer.parseInt(msgarr[3 + i]);
					}
					String username = msgarr[2];

					Calendar calendar = Calendar.getInstance();
					calendar.set(Calendar.YEAR, values[0]);
					calendar.set(Calendar.MONTH, values[1]);
					calendar.set(Calendar.DAY_OF_MONTH, values[2]);
					calendar.set(Calendar.HOUR, values[3]);
					calendar.set(Calendar.MINUTE, values[4]);
					Date date1 = calendar.getTime();

					StringBuilder sb = new StringBuilder();
					for (int i = 8; i < msgarr.length; i++)
						sb.append(msgarr[i]);

					result = this.databaseController.insertNewActivity(username, date1, sb.toString());

				} else if (function.startsWith("pull ranking sheets")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : request pull ranking sheets ");
						this.lock.notifyAll();
					}
					result = this.databaseController.getAllRankignSheets();

				} else if (function.startsWith("create sale pattern")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : request create sale pattern ");
						this.lock.notifyAll();
					}

					String[] msgarr = function.split(" ");
					int duration = Integer.parseInt(msgarr[3]);
					String[] productInSP = { msgarr[4], msgarr[5], msgarr[6], msgarr[7], msgarr[8], msgarr[9] };

					result = this.databaseController.createNewSalePatternID(duration, productInSP);

				} else if (function.startsWith("pull product rates")) {
					synchronized (this.lock) {
						this.serverWindow
								.updateArea(formatter.format(date) + " : " + client + " : request pull product rates ");
						this.lock.notifyAll();
					}
					result = this.databaseController.getAllProductRanks();

				}
//					else if (function.startsWith("create new PRUR")) {
//					synchronized (this.lock) {
//						this.serverWindow
//								.updateArea(formatter.format(date) + " : " + client + " : request create new PRUR ");
//						this.lock.notifyAll();
//					}
//
//					String[] msgarr = function.split(" ");
//					double dieselRank = Double.parseDouble(msgarr[3]);
//					double gasolineRank = Double.parseDouble(msgarr[4]);
//					double motorRank = Double.parseDouble(msgarr[5]);
//					double homeRank = Double.parseDouble(msgarr[6]);
//
//					result = this.databaseController.createNewPRUR(dieselRank, gasolineRank, motorRank, homeRank);
//
//				} 
				else if (function.startsWith("pull common data for common tableView")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request pull common data for common tableView ");
						this.lock.notifyAll();
					}

					result = this.databaseController.getSaleList();

				} else if (function.startsWith("generate SaleCommentReport")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : request generate Sale Comment Report");
						this.lock.notifyAll();
					}
					String[] msgarr = function.split(" ");

					result = this.databaseController.generateSaleCommentReport(Integer.parseInt(msgarr[2]));

				} else if (function.startsWith("generate periodic report")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(
								formatter.format(date) + " : " + client + " : request generate Periodic Report");
						this.lock.notifyAll();
					}
					String[] msgarr = function.split(" ");
					Calendar calendar1 = Calendar.getInstance(); // fromDate
					calendar1.set(Calendar.YEAR, Integer.parseInt(msgarr[3]));
					calendar1.set(Calendar.MONTH, Integer.parseInt(msgarr[4]));
					calendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(msgarr[5]));
					Date fromDate = calendar1.getTime();
					System.out.println(fromDate.toString());
					Calendar calendar2 = Calendar.getInstance(); // toDate
					calendar2.set(Calendar.YEAR, Integer.parseInt(msgarr[6]));
					calendar2.set(Calendar.MONTH, Integer.parseInt(msgarr[7]));
					calendar2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(msgarr[8]));
					Date toDate = calendar2.getTime();
					System.out.println(toDate.toString());

					result = this.databaseController.generatePeriodicReport(fromDate, toDate);

				} else if (function.startsWith("check sale range")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request check sale range for initiating sale");
						this.lock.notifyAll();
					}
					String[] msgarr = function.split(" ");

					Calendar calendar1 = Calendar.getInstance(); // startDate
					calendar1.set(Calendar.YEAR, Integer.parseInt(msgarr[3]));
					calendar1.set(Calendar.MONTH, Integer.parseInt(msgarr[4]));
					calendar1.set(Calendar.DAY_OF_MONTH, Integer.parseInt(msgarr[5]));
					Date startDate = calendar1.getTime();
					System.out.println(startDate.toString());
					Calendar calendar2 = Calendar.getInstance(); // endDate
					calendar2.set(Calendar.YEAR, Integer.parseInt(msgarr[6]));
					calendar2.set(Calendar.MONTH, Integer.parseInt(msgarr[7]));
					calendar2.set(Calendar.DAY_OF_MONTH, Integer.parseInt(msgarr[8]));
					Date endDate = calendar2.getTime();
					System.out.println(endDate.toString());

					result = this.databaseController.checkSaleRange(startDate, endDate);
				} else if (function.startsWith("get pricing model type discounts")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request get pricing model type discounts");
						this.lock.notifyAll();
					}

					result = this.databaseController.getPricingModelTypeDiscounts();

				} else if (function.startsWith("create pricing model request")) {
					synchronized (this.lock) {
						this.serverWindow.updateArea(formatter.format(date) + " : " + client
								+ " : request get pricing model type discounts");
						this.lock.notifyAll();
					}
					String[] strArr = function.split(" ");
					double discount = Double.parseDouble(strArr[5]);
					result = this.databaseController.createNewPricingModelRequest(strArr[4], discount);

				}

				//////////////////////////////////////////////////////////////////////////////////////////////////////////

				synchronized (this.lock) {
					this.serverWindow.updateArea(formatter.format(date) + " : " + client + " : " + result);
					this.lock.notifyAll();
				}

				/* send message back to client */
			}
			client.sendToClient(result);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
