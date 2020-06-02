package guiClient;

import client.MarketingRepresentativeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 * boundary for marketing representative window
 * 
 * @version Basic
 * @author Elroy, Lior
 */
public class MarketingRepresentativeWindow extends UserWindow {

	@FXML	private ToggleGroup one;
	@FXML	private ToggleButton sidebar_btn0;
	@FXML	private ToggleButton sidebar_btn1;
	@FXML	private ToggleButton sidebar_btn2;
	@FXML	private ToggleButton sidebar_btn3;
	@FXML	private ToggleButton sidebar_btn4;
	@FXML	private ToggleButton sidebar_btn5;

	@FXML	private ComboBox<?> cobHomeWeek;
	@FXML	private Button btnHomeGenerateAnalysis;

	@FXML	private AnchorPane addEditCustomerPane;
	@FXML	private Label step1;
	@FXML	private TextField tfAECUCredit;
	@FXML	private TextField tfAECUCustID;
	@FXML	private TextField tfAECUFirstName;
	@FXML	private TextField tfAECUSurname;
	@FXML	private TextField tfAECUEmail;
	@FXML	private ComboBox<?> cobAECUCustType;
	@FXML	private Button btnAECUSave;
	@FXML	private Button btnAECUEdit;

	@FXML	private AnchorPane editCustomerPane;
	@FXML	private TextField tfACUCustID;
	@FXML	private Button btnECUUpdate;
	@FXML	private Button btnECUDelete;
	@FXML	private Button btnECUClose;
	@FXML	private TextField tfECUFirstName;
	@FXML	private TextField tfECUSurname;
	@FXML	private TextField tfECUEmail;
	@FXML	private TextField tfECUCredit;
	@FXML	private ComboBox<?> cobECUCustType;
	@FXML	private Button btnECUShow;
	@FXML	private Button btnECUClear;

	@FXML	private AnchorPane addEditCarPane;
	@FXML	private Label step2;
	@FXML	private TextField tfAECARegistration;
	@FXML	private TextField tfAECAOwner;
	@FXML	private Button btnAECASave;
	@FXML	private TextField tfAECACustID;
	@FXML	private Button btnAECACheck;
	@FXML	private ComboBox<?> cobAECAFuelType;
	@FXML	private Button btnAECAEdit;
	@FXML	private Button btnAECAClear;
	
	@FXML	private AnchorPane editCarPane;
	@FXML	private TableView<?> tvECACarDetails;
	@FXML	private TextField tfECACustID;
	@FXML	private TextField tfECARegistration;
	@FXML	private TextField tfECAOwner;
	@FXML	private ComboBox<?> cobECAFuelType;
	@FXML	private Button btnECAUpdate;
	@FXML	private Button btnECADelete;
	@FXML	private Button btnExit1;
	@FXML	private ImageView btnECAClose;
	@FXML	private Button btnECAClear;
	@FXML	private Button btnECAShow;
	
	@FXML	private AnchorPane setPurchasingPane;
	@FXML	private Label step3;
	@FXML	private VBox vbSPPMagicbox2;
	@FXML	private ScrollPane purchProg_ExpenProgBox_SP;
	@FXML	private TextArea taSPPExpensiveDetails;
	@FXML	private ComboBox<?> cobSPPFuelCompany1;
	@FXML	private ComboBox<?> cobSPPFuelCompany2;
	@FXML	private ComboBox<?> cobSPPFuelCompany3;
	@FXML	private Button btnSPPSave;
	@FXML	private VBox vbSPPMagicbox1;
	@FXML	private ScrollPane purchProg_ExpenProgBox_SP1;
	@FXML	private TextArea taSPPSingleDetails;
	@FXML	private ToggleGroup two;
	@FXML	private RadioButton rbSPPStandard;
	@FXML	private RadioButton rbSPPPremium;
	@FXML	private Label lblSPPChooseCompany;
	@FXML	private TextField tfSPPCustID;
	@FXML	private Button btnSPPCheck;
	@FXML	private Button btnSPPClear;

	@FXML	private AnchorPane pricingModelPane;
	@FXML	private Button btnSPMSet;
	@FXML	private TextField tfSPMCustID;
	@FXML	private Button btnSPMCheck;
	@FXML	private Label lblSPMPriceModel1;
	@FXML	private Text txSPMModel1Details;
	@FXML	private ToggleButton btnSPMChoose1;
	@FXML	private ToggleButton btnSPMChoose2;
	@FXML	private ToggleButton btnSPMChoose3;
	@FXML	private ToggleButton btnSPMChoose4;
	@FXML	private ToggleGroup three;
	@FXML	private Label lblSPMModel1Discount;
	@FXML	private Label lblSPMPriceModel2;
	@FXML	private Text txSPMModel2Details;
	@FXML	private Label lblSPMModel2Discount;
	@FXML	private Label lblSPMPriceModel3;
	@FXML	private Text txSPMModel3Details;
	@FXML	private Label lblSPMModel3Discount;
	@FXML	private Label lblSPMPriceModel4;
	@FXML	private Text txSPMModel4Details;
	@FXML	private Label lblSPMModel4Discount;
	@FXML	private Button btnSPMClear;
	
	@FXML	private AnchorPane createSalePatternPane;
	@FXML	private TableView<?> tvCSPAnalysis;
	@FXML	private TextField tfCSPDuration;
	@FXML	private TextField tfCSPDieselDisc;
	@FXML	private CheckBox cbCSPDiesel;
	@FXML	private CheckBox cbCSPGasoline;
	@FXML	private CheckBox cbCSPMotorbike;
	@FXML	private TextField tfCSPGasolineDisc;
	@FXML	private TextField tfCSPMotorbikeDisc;
	@FXML	private Button btnCSPCreate;

	@FXML
	void initialize() {
		this.visableNow = homePane;
//		sidebar_btn0.requestFocus();
		this.controller = MarketingRepresentativeController.getInstance();
	}

	@Override
	public Window getWindow() {
		return this.btnCSPCreate.getScene().getWindow();
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
		addEditCustomerPane.setVisible(true);
		visableNow = addEditCustomerPane;
		topbar_window_label.setText("Add Customer");
	}

	@FXML
	void Add_Car(ActionEvent event) {
		visableNow.setVisible(false);
		addEditCarPane.setVisible(true);
		visableNow = addEditCarPane;
		topbar_window_label.setText("Add Car");
	}

	@FXML
	void Purchase_Prog(ActionEvent event) {
		visableNow.setVisible(false);
		setPurchasingPane.setVisible(true);
		visableNow = setPurchasingPane;
		topbar_window_label.setText("Purchasing Program");
	}

	@FXML
	void Pricing_Model(ActionEvent event) {
		visableNow.setVisible(false);
		pricingModelPane.setVisible(true);
		visableNow = pricingModelPane;
		topbar_window_label.setText("Pricing Model");
	}

	@FXML
	void Create_Sale_Pattern(ActionEvent event) {
		visableNow.setVisible(false);
		createSalePatternPane.setVisible(true);
		visableNow = createSalePatternPane;
		topbar_window_label.setText("Create Sales Pattern");
	}

}
