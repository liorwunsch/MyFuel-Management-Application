package entities;

import java.io.Serializable;

/**
 * 
 * @author Lior
 *
 */
@SuppressWarnings("serial")
public class MyNetManager implements Serializable {

	private String func;
	private Object[] params;

	public MyNetManager() {
		super();
	}

	public String getFunction() {
		return func;
	}

	public void setFunction(String func) {
		this.func = func;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

}
