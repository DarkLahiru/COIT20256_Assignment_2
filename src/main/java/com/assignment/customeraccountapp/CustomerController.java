package com.assignment.customeraccountapp;

import com.assignment.customeraccountapp.model.Account;
import com.assignment.customeraccountapp.model.Customer;
import com.assignment.customeraccountapp.model.CustomerList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CustomerController {
    private CustomerList customerList = new CustomerList();
    private Customer currentCustomer;
    private Account currentAccount;


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
    /**
     * Handles the functionality for the Clear button.
     * Clears all text fields and text areas, and re-enables the withdraw button if it was previously disabled.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleClearButtonAction() {
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

     /**
     * Handles the functionality for the Exit button.
     * Displays a confirmation message in the messages area.
     */
    @FXML
    private void handleExitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    
    /**
     * Handles the functionality for the Deposit button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleDepositButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();  // Get the clicked button
        String buttonText = clickedButton.getText();  // Get the button's label text
        messagesArea.setText(buttonText.toLowerCase() + " button clicked - under development");
    }

    /**
     * Handles the functionality for the Withdraw button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleWithdrawButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();  // Get the clicked button
        String buttonText = clickedButton.getText();  // Get the button's label text
        messagesArea.setText(buttonText.toLowerCase() + " button clicked - under development");
    }

    /**
     * Handles the functionality for the MonthlyInterests button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleMonthlyInterestsButtonAction(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();  // Get the clicked button
        String buttonText = clickedButton.getText();  // Get the button's label text
        messagesArea.setText(buttonText.toLowerCase() + " button clicked - under development");
    }

    /**
     * Handles the functionality for the GenerateReports button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleGenerateReportsButtonAction(ActionEvent event) {
        customerList.generateReport();  // Call the method to generate the report
        messagesArea.setText("Report generated successfully!");  // Provide feedback to the user
    }

    /**
     * Handles the functionality for the FindCustomer button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleFindCustomerButtonAction(ActionEvent event) {
        String customerId = customerIdField.getText();
        currentCustomer = customerList.findCustomer(customerId);

        if (currentCustomer != null) {
            customerNameField.setText(currentCustomer.getName());
            customerPhoneField.setText(currentCustomer.getPhoneNumber());
            customerEmailField.setText(currentCustomer.getEmail());
            
            // Display the first account ID and type
            displayFirstAccount();
            messagesArea.setText("Customer found: " + customerId);
        } else {
            messagesArea.setText("Customer not found: " + customerId);
        }
    }
    
    private void displayFirstAccount() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            var firstAccount = currentCustomer.getFirstAccount();
            accountIdField.setText(firstAccount.getAccountId());
            accountTypeField.setText(firstAccount.getClass().getSimpleName()); // Display the type of account
        } else {
            messagesArea.setText("No accounts available for this customer.");
        }
    }
              
    /**
     * Handles the functionality for the FindAccount button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleFindAccountButtonAction(ActionEvent event) {
        String accountId = accountIdField.getText();
        currentAccount = customerList.findAccount(accountId);

        if (currentAccount != null) {
            currentCustomer = customerList.findCustomer(currentAccount.getCustomerId());
            customerIdField.setText(currentCustomer.getCustomerId());
            customerNameField.setText(currentCustomer.getName());
            customerPhoneField.setText(currentCustomer.getPhoneNumber());
            displayAccountDetails(currentAccount);
        } else {
            messagesArea.setText("Account not found: " + accountId);
        }
    }
    
    private void displayAccountDetails(Account account) {
        accountIdField.setText(account.getAccountId());
        accountTypeField.setText(account.getClass().getSimpleName());
        messagesArea.setText(account.getAccountDetails());
    }

    /**
     * Handles the functionality for the Next button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handleNextButtonAction(ActionEvent event) {
         if (currentCustomer != null&& currentCustomer.getNumberOfAccounts() > 0) {
            Account account = currentCustomer.getNextAccount();
            accountIdField.setText(account.getAccountId());
            accountTypeField.setText(account.getClass().getSimpleName());

        }
         
    }
    
    /**
     * Handles the functionality for the Previous button.
     * Displays a confirmation message in the messages area.
     */

    @FXML
    private void handlePreviousButtonAction(ActionEvent event) {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            Account account = currentCustomer.getPreviousAccount();
            accountIdField.setText(account.getAccountId());
            accountTypeField.setText(account.getClass().getSimpleName());

        }      
    }


}
