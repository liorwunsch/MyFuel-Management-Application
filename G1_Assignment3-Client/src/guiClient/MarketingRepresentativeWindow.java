package guiClient;

import client.MarketingRepresentativeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;

/**
 * boundary for marketing representative window
 * 
 * @version Basic
 * @author Elroy, Lior
 */
public class MarketingRepresentativeWindow extends UserWindow {


    @FXML    private ToggleGroup one;
    @FXML    private ToggleButton sidebar_btn11;
    @FXML    private ToggleButton sidebar_btn1;
    @FXML    private ToggleButton sidebar_btn2;
    @FXML    private ToggleButton sidebar_btn3;
    @FXML    private ToggleButton sidebar_btn4;
    @FXML    private ToggleButton sidebar_btn5;
    
    @FXML    private BorderPane main_pane;
    @FXML    private AnchorPane addCustomer_pane;
    @FXML    private TextField addcust_Credit_TF;
    @FXML    private TextField addcust_CustID_TF;
    @FXML    private TextField addcust_FirstName_TF;
    @FXML    private TextField addcust_Surname_TF;
    @FXML    private TextField addcust_Email_TF;
    @FXML    private ComboBox<?> addcust_CustType_CB;
    @FXML    private Button addcust_Save_btn;
    
    @FXML    private AnchorPane addCar_pane;
    @FXML    private TextField addcar_RegPlate_TF;
    @FXML    private TextField addcar_OwnerName_TF;
    @FXML    private Button addcar_SaveCar_btn;
    @FXML    private TextField addcar_CustID_TF;
    @FXML    private Button addcar_CheckCust_btn;
    @FXML    private ComboBox<?> addcar_FuelType_CB;
    
    @FXML    private AnchorPane PurchProg_pane;
    @FXML    private ScrollPane purchProg_ExpenProgBox_SP;
    @FXML    private RadioButton purchProg_ExpenProg_RB;
    @FXML    private ToggleGroup two;
    @FXML    private ComboBox<?> purchProg_FuelComp1_CB;
    @FXML    private ComboBox<?> purchProg_FuelComp2_CB;
    @FXML    private ComboBox<?> purchProg_FuelComp3_CB;
    @FXML    private Button purchProg_SavePurch_btn;
    @FXML    private ScrollPane purchProg_ExpenProgBox_SP1;
    @FXML    private RadioButton purchProg_ExpenProg_RB1;
    @FXML    private ToggleGroup two1;
    @FXML    private TextField addcar_CustID_TF1;
    @FXML    private Button addcar_CheckCust_btn1;
    
    @FXML    private AnchorPane pricingModel_pane;
    @FXML    private Button pricingModel_SaveModel_btn;
    @FXML    private TextField addcar_CustID_TF2;
    @FXML    private Button addcar_CheckCust_btn2;
    @FXML    private ToggleGroup three;
    
    @FXML    private AnchorPane updateCustomer_pane;
    
	@FXML
	void initialize() {
		this.visableNow = addCustomer_pane;
		this.controller = MarketingRepresentativeController.getInstance();
	}

	@Override
	public Window getWindow() {
		return this.addCustomer_pane.getScene().getWindow();
	}

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		if (lastMsgFromServer instanceof String) {
			String message = lastMsgFromServer.toString();
			if (message.startsWith("sign out"))
				this.handleSignOutFromServer(message, this.getWindow());
		}

		/**
		 * 
		 */
	}

	@FXML
	void Add_Customer(ActionEvent event) {
		visableNow.setVisible(false);
		addCustomer_pane.setVisible(true);
		visableNow = addCustomer_pane;
		topbar_window_label.setText("Add Customer");
	}

	@FXML
	void Add_Car(ActionEvent event) {
		visableNow.setVisible(false);
		addCar_pane.setVisible(true);
		visableNow = addCar_pane;
		topbar_window_label.setText("Add Car");
	}

	@FXML
	void Purchase_Prog(ActionEvent event) {
		visableNow.setVisible(false);
		PurchProg_pane.setVisible(true);
		visableNow = PurchProg_pane;
		topbar_window_label.setText("Purchasing Program");
	}

	@FXML
	void Pricing_Model(ActionEvent event) {
		visableNow.setVisible(false);
		pricingModel_pane.setVisible(true);
		visableNow = pricingModel_pane;
		topbar_window_label.setText("Pricing Model");
	}
	
	@FXML
	void Update_Customer(ActionEvent event) {
		visableNow.setVisible(false);
		updateCustomer_pane.setVisible(true);
		visableNow = updateCustomer_pane;
		topbar_window_label.setText("Update Customer");
	}

}
