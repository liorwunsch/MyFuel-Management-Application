package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class PeriodicReportList implements Serializable {
	private PeriodicCustomersReport report = null;
	private List<CustomerBoughtFromCompany> list = new ArrayList<>();
	private boolean generated = false;

	public boolean isGenerated() {
		return generated;
	}

	public void setGenerated(boolean generated) {
		this.generated = generated;
	}

	public PeriodicReportList() {
		super();
	}

	public PeriodicCustomersReport getReport() {
		return report;
	}

	public void setReport(PeriodicCustomersReport report) {
		this.report = report;
	}

	public List<CustomerBoughtFromCompany> getList() {
		return list;
	}

	public void setList(List<CustomerBoughtFromCompany> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "PeriodicReportList [report=" + report + ", list=" + list + "]";
	}

}
