/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 * The Account class is an abstract class that represents a bank account.
 * It holds common account-related information such as the account ID, customer ID, 
 * and interest rate. The class also includes abstract methods to enforce 
 * the implementation of account-specific behaviors in subclasses.
 * 
 * <p>
 * Subclasses of Account are expected to provide specific implementations for:
 * <ul>
 *   <li>Applying monthly interest to the account</li>
 *   <li>Returning detailed account information</li>
 * </ul>
 * </p>
 * 
 * This class ensures that common account functionalities are shared across 
 * different types of accounts.
 * 
 * @author Lahiru
 */
public abstract class Account {
    private String accountId;     // Unique ID for the account
    private String customerId;    // ID of the customer who owns this account
    private double interestRate;  // Interest rate applied to the account

    /**
     * Constructs an Account with the specified account ID, customer ID, and interest rate.
     *
     * @param accountId   the unique ID for the account
     * @param customerId  the ID of the customer owning this account
     * @param interestRate the interest rate for the account
     */
    public Account(String accountId, String customerId, double interestRate) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.interestRate = interestRate;
    }

    /**
     * Applies monthly interest to the account. This method is abstract and must 
     * be implemented by subclasses to define account-specific interest calculation behavior.
     */
    public abstract void applyMonthlyInterest();

    /**
     * Returns the unique account ID.
     *
     * @return the account ID
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Returns the customer ID associated with this account.
     *
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns the interest rate applied to the account.
     *
     * @return the interest rate of the account
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Returns a string containing detailed information about the account.
     * This method is abstract and must be implemented by subclasses.
     *
     * @return a string representing the detailed account information
     */
    public abstract String getAccountDetails();
}
