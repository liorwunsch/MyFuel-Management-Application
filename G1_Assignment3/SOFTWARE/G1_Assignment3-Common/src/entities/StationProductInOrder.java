package entities;

import java.io.Serializable;

/**
 * 
 * @author Liad
 *
 */
@SuppressWarnings("serial")
public class StationProductInOrder implements Serializable {

	private Orders orders;
	private ProductInStation product;

	public StationProductInOrder(ProductInStation product, Orders orders) {
		this.orders = orders;
		this.product = product;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public ProductInStation getProduct() {
		return product;
	}

	public void setProduct(ProductInStation product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "StationProductInOrder orders=" + orders.toString() + "\n product=" + product.toString();
	}

}
