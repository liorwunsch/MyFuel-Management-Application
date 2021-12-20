package entities;

import java.io.Serializable;

/**
 * 
 * @author Elroy
 */
@SuppressWarnings("serial")
public class MarketingManager implements Serializable {
	private String func;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MarketingManager() {
		super();
	}

	public String getFunction() {
		return func;
	}

	public void setFunction(String func) {
		this.func = func;
	}

}
