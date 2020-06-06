package guiClient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * boundary for marketing department workers' windows
 * 
 * @version Almost Final
 * @author Lior, Liad
 */
public abstract class MarketingDepWorkerWindow extends EmployeeWindow {

	@FXML	protected Button btnHomeGenerateAnalysis;
	
	@FXML	protected AnchorPane createSalePatternPane;
	@FXML	protected TableView<?> tvCSPAnalysis;
	@FXML	protected TextField tfCSPDuration;
	@FXML	protected TextField tfCSPDieselDisc;
	@FXML	protected CheckBox cbCSPDiesel;
	@FXML	protected CheckBox cbCSPGasoline;
	@FXML	protected CheckBox cbCSPMotorbike;
	@FXML	protected TextField tfCSPGasolineDisc;
	@FXML	protected TextField tfCSPMotorbikeDisc;
	@FXML	protected Button btnCSPCreate;
	/**
	 * handle analysis
	 */
	
	@FXML
	void openCreateSalesPattern(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.createSalePatternPane.setVisible(true);
		this.visibleNow = this.createSalePatternPane;
		this.topbar_window_label.setText("Create Sales Pattern");
	}
}
