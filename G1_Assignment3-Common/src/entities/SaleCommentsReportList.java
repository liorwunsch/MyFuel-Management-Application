package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class SaleCommentsReportList implements Serializable {
	private SaleCommentsReport report = null;
	private List<CustomerBoughtInSale> list = new ArrayList<>();
	private boolean generated = false;

	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
	}

	public SaleCommentsReportList() {
		super();
	}

	public SaleCommentsReport getReport() {
		return report;
	}

	public void setReport(SaleCommentsReport report) {
		this.report = report;
	}

	public List<CustomerBoughtInSale> getList() {
		return list;
	}

	public void setList(List<CustomerBoughtInSale> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "SaleCommentReportList [report=" + report + ", list=" + list + "]";
	}

}
