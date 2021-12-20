package entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class ProductRateList implements Serializable {
	private List<Product> list = new ArrayList<>();

	public ProductRateList(List<Product> list) {
		this.list = list;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}

}
