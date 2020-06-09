package guiClient;

import java.util.Collection;

import client.MarketingRepresentativeController;
import entities.Customer;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Window;

/**
 * boundary for marketing representative window
 * 
 * @version Basic
 * @author Elroy, Lior
 */
public class MarketingRepresentativeWindow extends MarketingDepWorkerWindow {

	@FXML
	private ToggleGroup one;
	@FXML
	private ToggleButton sidebar_btn0;
	@FXML
	private ToggleButton sidebar_btn1;
	@FXML
	private ToggleButton sidebar_btn2;
	@FXML
	private ToggleButton sidebar_btn3;
	@FXML
	private ToggleButton sidebar_btn4;
	@FXML
	private ToggleButton sidebar_btn5;

	@FXML
	private AnchorPane addEditCustomerPane;
	@FXML
	private Label step1;
	@FXML
	private TextField tfAECUCredit;
	@FXML
	private TextField tfAECUCustID;
	@FXML
	private TextField tfAECUFirstName;
	@FXML
	private TextField tfAECUSurname;
	@FXML
	private TextField tfAECUEmail;
	@FXML
	private ComboBox<String> cobAECUCustType;
	@FXML
	private Button btnAECUSave;
	@FXML
	private Button btnAECUEdit;

	@FXML
	private AnchorPane editCustomerPane;
	@FXML
	private GridPane gpECUCustomer;
	@FXML
	private AnchorPane apECUCustomer;
	@FXML
	private TextField tfACUCustID;
	@FXML
	private Button btnECUUpdate;
	@FXML
	private Button btnECUDelete;
	@FXML
	private Button btnECUClose;
	@FXML
	private TextField tfECUFirstName;
	@FXML
	private TextField tfECUSurname;
	@FXML
	private TextField tfECUEmail;
	@FXML
	private TextField tfECUCredit;
	@FXML
	private ComboBox<String> cobECUCustType;
	@FXML
	private Button btnECUShow;
	@FXML
	private Button btnECUClear;

	@FXML
	private AnchorPane addEditCarPane;
	@FXML
	private Label step2;
	@FXML
	private TextField tfAECARegistration;
	@FXML
	private TextField tfAECAOwner;
	@FXML
	private Button btnAECASave;
	@FXML
	private TextField tfAECACustID;
	@FXML
	private Button btnAECACheck;
	@FXML
	private ComboBox<?> cobAECAFuelType;
	@FXML
	private Button btnAECAEdit;
	@FXML
	private Button btnAECAClear;

	@FXML
	private AnchorPane editCarPane;
	@FXML
	private TableView<?> tvECACarDetails;
	@FXML
	private TextField tfECACustID;
	@FXML
	private TextField tfECARegistration;
	@FXML
	private TextField tfECAOwner;
	@FXML
	private ComboBox<?> cobECAFuelType;
	@FXML
	private Button btnECAUpdate;
	@FXML
	private Button btnECADelete;
	@FXML
	private Button btnExit1;
	@FXML
	private ImageView btnECAClose;
	@FXML
	private Button btnECAClear;
	@FXML
	private Button btnECAShow;

	@FXML
	private AnchorPane setPurchasingPane;
	@FXML
	private Label step3;
	@FXML
	private VBox vbSPPMagicbox2;
	@FXML
	private ScrollPane purchProg_ExpenProgBox_SP;
	@FXML
	private TextArea taSPPExpensiveDetails;
	@FXML
	private ComboBox<?> cobSPPFuelCompany1;
	@FXML
	private ComboBox<?> cobSPPFuelCompany2;
	@FXML
	private ComboBox<?> cobSPPFuelCompany3;
	@FXML
	private Button btnSPPSave;
	@FXML
	private VBox vbSPPMagicbox1;
	@FXML
	private ScrollPane purchProg_ExpenProgBox_SP1;
	@FXML
	private TextArea taSPPSingleDetails;
	@FXML
	private ToggleGroup two;
	@FXML
	private RadioButton rbSPPStandard;
	@FXML
	private RadioButton rbSPPPremium;
	@FXML
	private Label lblSPPChooseCompany;
	@FXML
	private TextField tfSPPCustID;
	@FXML
	private Button btnSPPCheck;
	@FXML
	private Button btnSPPClear;

	@FXML
	private AnchorPane pricingModelPane;
	@FXML
	private Button btnSPMSet;
	@FXML
	private TextField tfSPMCustID;
	@FXML
	private Button btnSPMCheck;
	@FXML
	private Label lblSPMPriceModel1;
	@FXML
	private Text txSPMModel1Details;
	@FXML
	private ToggleButton btnSPMChoose1;
	@FXML
	private ToggleButton btnSPMChoose2;
	@FXML
	private ToggleButton btnSPMChoose3;
	@FXML
	private ToggleButton btnSPMChoose4;
	@FXML
	private ToggleGroup three;
	@FXML
	private Label lblSPMModel1Discount;
	@FXML
	private Label lblSPMPriceModel2;
	@FXML
	private Text txSPMModel2Details;
	@FXML
	private Label lblSPMModel2Discount;
	@FXML
	private Label lblSPMPriceModel3;
	@FXML
	private Text txSPMModel3Details;
	@FXML
	private Label lblSPMModel3Discount;
	@FXML
	private Label lblSPMPriceModel4;
	@FXML
	private Text txSPMModel4Details;
	@FXML
	private Label lblSPMModel4Discount;
	@FXML
	private Button btnSPMClear;

	@FXML
	void initialize() {
		this.homePane.setVisible(true);
		this.addEditCustomerPane.setVisible(false);
		this.addEditCarPane.setVisible(false);
		this.setPurchasingPane.setVisible(false);
		this.pricingModelPane.setVisible(false);
		this.createSalePatternPane.setVisible(false);
		this.editCustomerPane.setVisible(false);
		this.editCarPane.setVisible(false);
		this.visibleNow = this.homePane;
		this.controller = MarketingRepresentativeController.getInstance();
		this.controller.setCurrentWindow(this);
	}

	@Override
	public Window getWindow() {
		return this.addEditCustomerPane.getScene().getWindow();
	}

	/*********************** button listeners ***********************/

	@FXML
	void openAddEditCustomer(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.addEditCustomerPane.setVisible(true);
		this.visibleNow = this.addEditCustomerPane;
		this.topbar_window_label.setText("Add\\Edit Customer");
		clearFields();
	}

	@FXML
	void btnAECUSavePressed(ActionEvent event) {
		String customerID = this.tfAECUCustID.getText();
		String firstName = this.tfAECUFirstName.getText();
		String surname = this.tfAECUSurname.getText();
		String email = this.tfAECUEmail.getText();
		String creditCard = this.tfAECUCredit.getText();
		String customerType = this.cobAECUCustType.getValue();

		if (customerID.isEmpty() || firstName.isEmpty() || surname.isEmpty() || email.isEmpty()
				|| creditCard.isEmpty()) {
			openErrorAlert("Error", "Missing Required Fields");
			return;
		}
		if (customerID.matches(".*[A-z].*") || firstName.matches(".*[0-9].*") || surname.matches(".*[0-9].*")
				|| creditCard.matches(".*[A-z].*") || creditCard.length() != 16 || customerID.length() != 9) {
			openErrorAlert("Error", "Field Not Valid");
			return;
		}

		this.controller.handleMessageFromClientUI("savecustomer " + customerID + " " + firstName + " " + surname + " "
				+ email + " " + creditCard + " " + customerType);
	}

	@FXML
	void btnAECUEditPressed(ActionEvent event) {
		mainBorderPane.setDisable(true);
		editCustomerPane.setVisible(true);
	}

	@FXML
	void btnECUShowPressed(ActionEvent event) {
		String customerID = this.tfACUCustID.getText();
		if (customerID.isEmpty() || customerID.length() != 9 || customerID.matches(".*[A-z].*")) {
			openErrorAlert("Error", "Field Not Valid");
			return;
		}

		this.controller.handleMessageFromClientUI("getcustomerdetails " + customerID);
	}

	@FXML
	void btnECUClearPressed(ActionEvent event) {
		clearEditCustomerPane();
	}

	@FXML
	void btnECUClosePressed(ActionEvent event) {
		mainBorderPane.setDisable(false);
		editCustomerPane.setVisible(false);
		clearEditCustomerPane();
	}

	@FXML
	void btnECUDeletePressed(ActionEvent event) {
		this.controller.handleMessageFromClientUI("deletecustomer " + this.tfACUCustID.getText());
	}

	@FXML
	void openAddEditCar(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.addEditCarPane.setVisible(true);
		this.visibleNow = this.addEditCarPane;
		this.topbar_window_label.setText("Add\\Edit Car");
		clearFields();
	}

	@FXML
	void openSetPurchasingProgram(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.setPurchasingPane.setVisible(true);
		this.visibleNow = this.setPurchasingPane;
		this.topbar_window_label.setText("Set Purchasing Program");
		clearFields();
	}

	@FXML
	void openSetPricingModel(ActionEvent event) {
		this.visibleNow.setVisible(false);
		this.pricingModelPane.setVisible(true);
		this.visibleNow = this.pricingModelPane;
		this.topbar_window_label.setText("Set Pricing Model");
		clearFields();
	}

	/*************** boundary "logic" - window changes ***************/

	@Override
	public void callAfterMessage(Object lastMsgFromServer) {
		super.callAfterMessage(lastMsgFromServer);

		if (lastMsgFromServer instanceof String) {
			String str = (String) lastMsgFromServer;
			if (str.equals("save customer success")) {
				openErrorAlert("Success",
						"Customer Saved\nUsername: " + this.tfAECUCustID.getText() + "\nPassword: 1234");

			} else if (str.equals("save customer fail")) {
				openErrorAlert("Error", "Add Customer Failed");

			} else if (str.equals("save customer exist")) {
				openErrorAlert("Error", "Customer Already Exists");

			} else if (str.startsWith("Customer Delete")) {
				openErrorAlert("Delete", str);
				clearEditCustomerPane();
			}

		} else if (lastMsgFromServer instanceof Object[]) {
			Object[] objArr = (Object[]) lastMsgFromServer;
			if (objArr.length == 2 && objArr[0] instanceof User && objArr[1] instanceof Customer) {
				User user = (User) objArr[0];
				Customer customer = (Customer) objArr[1];
				this.tfECUFirstName.setText(user.getFirstName());
				this.tfECUSurname.setText(user.getSurname());
				this.tfECUEmail.setText(user.getEmail());
				this.tfECUCredit.setText(customer.getCreditCard());
				this.cobECUCustType.setValue(customer.getCustomerType().toString());
				this.gpECUCustomer.setDisable(true);
				this.apECUCustomer.setDisable(false);
			}
		}
	}

	/**
	 * initialized tableview in home of marketing rep only
	 * 
	 * @param username
	 */
	@Override
	public void setUserComponents(String username) {
		super.setUserComponents(username);
		this.cobAECUCustType.getItems().removeAll((Collection<?>) this.cobAECUCustType.getItems());
		this.cobAECUCustType.getItems().addAll(new String[] { "Person", "Company" });
		this.cobAECUCustType.setValue("Person");
		this.cobECUCustType.getItems().removeAll((Collection<?>) this.cobAECUCustType.getItems());
		this.cobECUCustType.getItems().addAll(new String[] { "Person", "Company" });
		this.cobECUCustType.setValue("Person");
	}

	@Override
	public void clearFields() {
		this.tfAECUCredit.clear();
		this.tfAECUCustID.clear();
		this.tfAECUFirstName.clear();
		this.tfAECUSurname.clear();
		this.tfAECUEmail.clear();
		this.cobAECUCustType.setValue("Person");
		clearEditCustomerPane();
	}

	public void clearEditCustomerPane() {
		this.tfACUCustID.clear();
		this.tfECUFirstName.clear();
		this.tfECUSurname.clear();
		this.tfECUEmail.clear();
		this.tfECUCredit.clear();
		this.cobECUCustType.setValue("Person");
		this.gpECUCustomer.setDisable(false);
		this.apECUCustomer.setDisable(true);
	}

}
