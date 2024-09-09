package com.assignment.customeraccountapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PrimaryController {

    @FXML
    private TextField customerIdField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TextField customerEmailField;
    @FXML
    private TextField customerPhoneField;
    @FXML
    private TextField accountIdField;
    @FXML
    private TextField accountTypeField;
    @FXML
    private TextField numAccountsField;
    @FXML
    private Button findCustomerButton;
    @FXML
    private Button findAccountButton;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button addMonthlyInterestsButton;
    @FXML
    private Button generateReportsButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea messagesArea;
    @FXML
    private TextArea accountDetailsArea;
    @FXML
    private TextField depositAmountField;
    @FXML
    private TextField withdrawAmountField;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();  // Get the clicked button
        String buttonText = clickedButton.getText();  // Get the button's label text
        messagesArea.setText(buttonText.toLowerCase() + " button clicked - under development");
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        customerIdField.clear();
        customerNameField.clear();
        customerEmailField.clear();
        customerPhoneField.clear();
        accountIdField.clear();
        accountTypeField.clear();
        numAccountsField.clear();
        depositAmountField.clear();
        withdrawAmountField.clear();
        messagesArea.clear();
        accountDetailsArea.clear();
        
        // Re-enable withdraw button in case it was disabled for home loan accounts
        withdrawButton.setDisable(false);
    }

    @FXML
    private void handleExitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
