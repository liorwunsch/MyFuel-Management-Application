
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
	private List<RowInSaleCommentReportTable> list = new ArrayList<>();

	public SalesList(List<RowInSaleCommentReportTable> list) {
		this.list = list;
	}

	public List<RowInSaleCommentReportTable> getList() {
		return list;
	}

	public void setList(List<RowInSaleCommentReportTable> list) {
		this.list = list;
	}

}
