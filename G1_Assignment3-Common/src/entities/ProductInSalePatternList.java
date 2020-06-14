package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class ProductInSalePatternList implements Serializable {

	private List<ProductInSalesPattern> list = new ArrayList<>();

	public ProductInSalePatternList(List<ProductInSalesPattern> list) {
		this.list = list;
	}

	public List<ProductInSalesPattern> getList() {
		return list;
	}

	public void setList(List<ProductInSalesPattern> list) {
		this.list = list;
	}

}
