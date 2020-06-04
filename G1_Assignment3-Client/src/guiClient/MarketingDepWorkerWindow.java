package guiClient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
}
