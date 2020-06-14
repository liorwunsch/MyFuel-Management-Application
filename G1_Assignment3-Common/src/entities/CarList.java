package entities;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Lior
 */
@SuppressWarnings("serial")
public class CarList implements Serializable {

	private ArrayList<Car> cars;

	public CarList() {
		this.cars = new ArrayList<Car>();
	}

	public ArrayList<Car> getCars() {
		return this.cars;
	}

	public void setCars(ArrayList<Car> cars) {
		this.cars = cars;
	}

	public void add(Car car) {
		this.cars.add(car);
	}

}
