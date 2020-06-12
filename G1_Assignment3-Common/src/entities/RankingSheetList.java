package entities;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class RankingSheetList implements Serializable {
	private List<RankingSheet> list = new ArrayList<>();

	public RankingSheetList( List<RankingSheet> list) {
		this.list=list;
	}

	public List<RankingSheet> getList() {
		return list;
	}

	public void setList(List<RankingSheet> list) {
		this.list = list;
	}
	

}

