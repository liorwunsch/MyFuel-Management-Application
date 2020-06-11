package server;

import java.io.IOException;

import database.DatabaseController;
import entities.Car;
import entities.CarList;
import entities.Customer;
import entities.PricingModel;
import entities.PurchasingProgram;
import entities.User;
import ocsf.server.ConnectionToClient;

/**
 * controller for marketing representative
 * 
 * @version Final
 * @author Lior
 */
public class ServerMarketingRepresentativeController {

	private static ServerMarketingRepresentativeController instance;
	private DatabaseController databaseController;

	/**
	 * singleton class constructor
	 */
	private ServerMarketingRepresentativeController(DatabaseController databaseController) {
		super();
		this.databaseController = databaseController;
	}

	/**
	 * @return instance of this class
	 */
	public static ServerMarketingRepresentativeController getInstance(DatabaseController databaseController) {
		if (instance == null) {
			instance = new ServerMarketingRepresentativeController(databaseController);
		}
		return instance;
	}

	/**
	 * 
	 * @param object
	 * @param client
	 */
	public void handleMessageFromClient(Object object, ConnectionToClient client) {
		try {
			if (object instanceof PricingModel) {
				PricingModel pricingModel = (PricingModel) object;
				String str = this.databaseController.setPricingModel(pricingModel);
				client.sendToClient(str);

			} else if (object instanceof PurchasingProgram) {
				PurchasingProgram purchasingProgram = (PurchasingProgram) object;
				String str = this.databaseController.setPurchasingProgram(purchasingProgram);
				client.sendToClient(str);

			} else if (object instanceof Car) {
				Car car = (Car) object;
				String function = car.getFunction();
				String str = null;
				if (function.equals("save car")) {
					str = this.databaseController.saveNewCarSequence(car);
				} else if (function.equals("update car")) {
					str = this.databaseController.updateCar(car);
				}
				client.sendToClient(str);

			} else if (object instanceof Object[]) {
				Object[] objArr = (Object[]) object;
				if (objArr.length == 2 && objArr[0] instanceof User && objArr[1] instanceof Customer) {
					String function = ((User) objArr[0]).getFunction();
					String str = null;
					if (function.equals("save customer")) {
						str = this.databaseController.saveNewCustomerSequence((User) objArr[0], (Customer) objArr[1]);
					} else if (function.equals("update customer")) {
						str = this.databaseController.updateCustomer((User) objArr[0], (Customer) objArr[1]);
					}
					client.sendToClient(str);
				}

			} else if (object instanceof String) {
				String[] splitMsg = ((String) object).split(" ");
				if (splitMsg[0].equals("deletecar")) {
					boolean bool = this.databaseController.deleteCar(splitMsg[1]);
					if (bool == true)
						client.sendToClient("Car Deleted");
					else
						client.sendToClient("Car Delete Failed");

				} else if (splitMsg[0].equals("getcustomerdetails")) {
					Object[] objArr = this.databaseController.getCustomerDetails(splitMsg[1]);
					client.sendToClient(objArr);

				} else if (splitMsg[0].equals("deletecustomer")) {
					boolean bool = this.databaseController.deleteCustomer(splitMsg[1]);
					if (bool == true)
						client.sendToClient("Customer Deleted");
					else
						client.sendToClient("Customer Delete Failed");

				} else if (splitMsg[0].equals("checkcustomer")) {
					Integer exists = this.databaseController.checkCustomerExists(splitMsg[1]);
					if (exists == 0)
						client.sendToClient("Customer Check : Exists");
					else
						client.sendToClient("Customer Check : Doesn't Exist");

				} else if (splitMsg[0].equals("getcustomercars")) {
					CarList carList = this.databaseController.getCustomerCars(splitMsg[1]);
					client.sendToClient(carList);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
