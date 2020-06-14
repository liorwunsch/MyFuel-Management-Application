package entities;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class HomeFuelOrderList implements Serializable {

	private ArrayList<HomeFuelOrder> homeFuelOrders;

	public HomeFuelOrderList() {
		this.homeFuelOrders = new ArrayList<HomeFuelOrder>();
	}

	public ArrayList<HomeFuelOrder> getHomeFuelOrders() {
		return this.homeFuelOrders;
	}

	public void setHomeFuelOrders(ArrayList<HomeFuelOrder> homeFuelOrders) {
		this.homeFuelOrders = homeFuelOrders;
	}

	public void add(HomeFuelOrder homeFuelOrder) {
		this.homeFuelOrders.add(homeFuelOrder);
	}

}
