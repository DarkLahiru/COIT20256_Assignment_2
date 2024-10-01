package com.assignment.customeraccountapp;

import com.assignment.customeraccountapp.model.Account;
import com.assignment.customeraccountapp.model.Customer;
import com.assignment.customeraccountapp.model.CustomerList;
import com.assignment.customeraccountapp.model.DailyAccessAccount;
import com.assignment.customeraccountapp.model.HomeLoanAccount;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The CustomerController class manages user interactions with the customer account application.
 * It handles the logic for finding customers and accounts, processing transactions (deposits and withdrawals),
 * applying monthly interest, and generating reports. The controller updates the UI based on user actions.
 * 
 * This class ensures that all operations related to customers and their accounts are handled correctly
 * and updates the UI components accordingly.
 * 
 * @author Lahiru
 */
public class CustomerController {
    private CustomerList customerList = new CustomerList();  // List of customers and accounts
    private Customer currentCustomer;  // Currently selected customer
    private Account currentAccount;     // Currently selected account

    // FXML components for user input and display
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
     * Clears all input fields and messages displayed in the UI.
     * Resets the state of the Withdraw button to enabled.
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
        
        withdrawButton.setDisable(false);  // Enable withdraw button
    }

    /**
     * Closes the application when the Exit button is pressed.
     */
    @FXML
    private void handleExitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles deposit action. Retrieves the amount from the input field, checks if the account type
     * allows deposits, and updates the account balance accordingly.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleDepositButtonAction(ActionEvent event) {
        double amount = Double.parseDouble(depositAmountField.getText());
        
        if (currentAccount instanceof DailyAccessAccount) {
            ((DailyAccessAccount) currentAccount).deposit(amount);
            messagesArea.setText("Deposit of $" + amount + " successful.");
        } else {
            messagesArea.setText("Deposits can only be made to Daily Access accounts.");
        }
        updateAccountDetails();  // Refresh account details
    }

    /**
     * Handles withdrawal action. Retrieves the amount from the input field, checks if the account type
     * allows withdrawals, and updates the account balance accordingly.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleWithdrawButtonAction(ActionEvent event) {
        double amount = Double.parseDouble(withdrawAmountField.getText());
        if (currentAccount instanceof DailyAccessAccount) {
            boolean success = ((DailyAccessAccount) currentAccount).withdraw(amount);
            if (success) {
                messagesArea.setText("Withdrawal of $" + amount + " successful.");
            } else {
                messagesArea.setText("Insufficient funds.");
            }
        } else {
            messagesArea.setText("Withdrawals can only be made from Daily Access accounts.");
        }
        updateAccountDetails();  // Refresh account details
    }

    /**
     * Applies monthly interest to the current account and updates the UI.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleMonthlyInterestsButtonAction(ActionEvent event) {
        String accountId = accountIdField.getText();
        currentAccount = customerList.findAccount(accountId);
        currentAccount.applyMonthlyInterest();
        messagesArea.setText("Monthly interest applied.");
        updateAccountDetails();  // Refresh account details
    }

    /**
     * Generates a report of customers and their accounts when the corresponding button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleGenerateReportsButtonAction(ActionEvent event) {
        customerList.generateReport();  // Call the method to generate the report
        messagesArea.setText("Report generated successfully!");  // Provide feedback to the user
    }

    /**
     * Updates the account details area with the current account's information.
     * This is called after every relevant transaction to refresh the displayed data.
     */
    private void updateAccountDetails() {
        if (currentAccount != null) {
            if (currentAccount instanceof DailyAccessAccount) {
                accountDetailsArea.setText(((DailyAccessAccount) currentAccount).getAccountDetails());
            } else if (currentAccount instanceof HomeLoanAccount) {
                accountDetailsArea.setText(((HomeLoanAccount) currentAccount).getAccountDetails());
            }
        }
    }

    /**
     * Finds a customer by their ID when the Find Customer button is clicked,
     * and updates the UI with the customer's information.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleFindCustomerButtonAction(ActionEvent event) {
        String customerId = customerIdField.getText();
        currentCustomer = customerList.findCustomer(customerId);

        if (currentCustomer != null) {
            customerNameField.setText(currentCustomer.getName());
            customerPhoneField.setText(currentCustomer.getPhoneNumber());
            customerEmailField.setText(currentCustomer.getEmail());
            numAccountsField.setText(String.valueOf(currentCustomer.getNumberOfAccounts()));  // Display number of accounts

            // Display the first account ID and type
            displayFirstAccount();
            updateWithdrawButtonState();  // Update the state of the Withdraw button
            messagesArea.setText("Customer found: " + customerId);
        } else {
            messagesArea.setText("Customer not found: " + customerId);
        }
    }

    /**
     * Displays the first account associated with the current customer in the UI.
     */
    private void displayFirstAccount() {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            var firstAccount = currentCustomer.getFirstAccount();
            accountIdField.setText(firstAccount.getAccountId());
            accountTypeField.setText(firstAccount.getClass().getSimpleName()); // Display the type of account
            currentAccount = customerList.findAccount(firstAccount.getAccountId());
            displayAccountDetails(currentAccount);  // Update the account details area
        } else {
            messagesArea.setText("No accounts available for this customer.");
        }
    }
    
    /**
     * Finds an account by its ID when the Find Account button is clicked,
     * and updates the UI with the account's information.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleFindAccountButtonAction(ActionEvent event) {
        String accountId = accountIdField.getText();
        currentAccount = customerList.findAccount(accountId);

        if (currentAccount != null) {
            currentCustomer = customerList.findCustomer(currentAccount.getCustomerId());
            customerIdField.setText(currentCustomer.getCustomerId());
            customerNameField.setText(currentCustomer.getName());
            customerEmailField.setText(currentCustomer.getEmail());
            customerPhoneField.setText(currentCustomer.getPhoneNumber());
            numAccountsField.setText(String.valueOf(currentCustomer.getNumberOfAccounts()));  // Display number of accounts
            updateWithdrawButtonState();  // Update the state of the Withdraw button
            displayAccountDetails(currentAccount);  // Update account details
        } else {
            messagesArea.setText("Account not found: " + accountId);
        }
    }
    
    /**
     * Displays the details of the specified account in the account-related fields.
     *
     * @param account the account to display
     */
    private void displayAccountDetails(Account account) {
        accountIdField.setText(account.getAccountId());
        accountTypeField.setText(account.getClass().getSimpleName());
        accountDetailsArea.setText(account.getAccountDetails());
    }

    /**
     * Handles the action of moving to the next account when the Next button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handleNextButtonAction(ActionEvent event) {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            Account account = currentCustomer.getNextAccount();
            accountIdField.setText(account.getAccountId());
            accountTypeField.setText(account.getClass().getSimpleName());
            accountDetailsArea.setText(account.getAccountDetails());
            currentAccount = customerList.findAccount(account.getAccountId());
            updateWithdrawButtonState();  // Update the state of the Withdraw button
        }
    }
    
    /**
     * Handles the action of moving to the previous account when the Previous button is clicked.
     *
     * @param event the action event triggered by the button click
     */
    @FXML
    private void handlePreviousButtonAction(ActionEvent event) {
        if (currentCustomer != null && currentCustomer.getNumberOfAccounts() > 0) {
            Account account = currentCustomer.getPreviousAccount();
            accountIdField.setText(account.getAccountId());
            accountTypeField.setText(account.getClass().getSimpleName());
            accountDetailsArea.setText(account.getAccountDetails());
            currentAccount = customerList.findAccount(account.getAccountId());
            updateWithdrawButtonState();  // Update the state of the Withdraw button
        }
    }

    /**
     * Updates the state of the Withdraw button based on the current account type.
     * The Withdraw button is disabled for Home Loan accounts.
     */
    private void updateWithdrawButtonState() {
        if (currentAccount instanceof HomeLoanAccount) {
            withdrawButton.setDisable(true);  // Disable withdraw button for Home Loan accounts
        } else {
            withdrawButton.setDisable(false);  // Enable withdraw button for other account types
        }
    }
}
