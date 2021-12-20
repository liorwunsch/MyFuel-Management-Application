package entities;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FastFuelList implements Serializable {

	private ArrayList<FastFuel> fastFuels;

	public FastFuelList() {
		this.fastFuels = new ArrayList<FastFuel>();
	}

	public ArrayList<FastFuel> getFastFuels() {
		return this.fastFuels;
	}

	public void setFastFuels(ArrayList<FastFuel> fastFuels) {
		this.fastFuels = fastFuels;
	}

	public void add(FastFuel fastFuel) {
		this.fastFuels.add(fastFuel);
	}

}
