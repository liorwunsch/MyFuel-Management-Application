package entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class SalesPatternList implements Serializable {
	private List<SalesPattern> list = new ArrayList<>();

	public SalesPatternList(List<SalesPattern> list) {
		this.list = list;
	}

	public List<SalesPattern> getList() {
		return list;
	}

	public void setList(List<SalesPattern> list) {
		this.list = list;
	}

}
