package entities;

import java.io.Serializable;

/**
 * 
 * @author Liad
 *
 */
@SuppressWarnings("serial")
public class MyFuelStationManager implements Serializable {
	private String func;
	private String params;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MyFuelStationManager() {
		super();
	}

	public String getFunction() {
		return func;
	}

	public void setFunction(String func) {
		this.func = func;
	}

}
