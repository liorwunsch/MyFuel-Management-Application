
package entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class SalesList implements Serializable {
	private List<RowInSaleCommentsReportTable> list = new ArrayList<>();

	public SalesList(List<RowInSaleCommentsReportTable> list) {
		this.list = list;
	}

	public List<RowInSaleCommentsReportTable> getList() {
		return list;
	}

	public void setList(List<RowInSaleCommentsReportTable> list) {
		this.list = list;
	}

}
