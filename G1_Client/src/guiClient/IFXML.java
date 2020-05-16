package guiClient;

public interface IFXML {
	
	/**
	 * 
	 * @param msg
	 * gets message from controller
	 * and executes things in the window
	 */
	
	
	public void callAfterMessage(String msg);

	public void openErrorAlert(String title, String msg);
}
