/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

import java.util.ArrayList;

/**
 * The Customer class represents a customer in the customer account application.
 * It stores the customer's personal information and manages their associated accounts.
 * The class provides methods for retrieving and navigating through accounts
 * and setting the current account by its ID.
 * 
 * <p>
 * This class includes methods to handle customer-related functionalities such as:
 * <ul>
 *   <li>Fetching the customer's first, next, or previous account</li>
 *   <li>Returning the total number of accounts associated with the customer</li>
 * </ul>
 * </p>
 * 
 * @author Lahiru
 */
public class Customer {
    private String customerId;  // The unique ID of the customer
    private String name;  // The name of the customer
    private String email;  // The email address of the customer
    private String phoneNumber;  // The phone number of the customer
    private ArrayList<Account> accounts;  // List of accounts associated with the customer
    private int currentAccountIndex = 0;  // Index of the currently active account

    /**
     * Constructs a Customer object with the specified details.
     *
     * @param customerId   the unique ID of the customer
     * @param name         the name of the customer
     * @param email        the email address of the customer
     * @param phoneNumber  the phone number of the customer
     */
    public Customer(String customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();  // Initialize the account list
    }

    /**
     * Returns the unique ID of the customer.
     *
     * @return the customer ID
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Returns the name of the customer.
     *
     * @return the customer's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email of the customer.
     *
     * @return the customer's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone number of the customer.
     *
     * @return the customer's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Returns the total number of accounts associated with this customer.
     *
     * @return the number of accounts
     */
    public int getNumberOfAccounts() {
        return accounts.size();
    }

    /**
     * Returns the first account associated with the customer.
     * Resets the current account index to the first account.
     *
     * @return the first account
     */
    public Account getFirstAccount() {
        currentAccountIndex = 0;  // Reset to the first account
        return accounts.get(currentAccountIndex);
    }

    /**
     * Returns the next account in the customer's account list.
     * Wraps around if the end of the list is reached.
     *
     * @return the next account
     */
    public Account getNextAccount() {
        currentAccountIndex = (currentAccountIndex + 1) % accounts.size();  // Move to the next account
        return accounts.get(currentAccountIndex);
    }

    /**
     * Returns the previous account in the customer's account list.
     * Wraps around if the beginning of the list is reached.
     *
     * @return the previous account
     */
    public Account getPreviousAccount() {
        currentAccountIndex = (currentAccountIndex - 1 + accounts.size()) % accounts.size();  // Move to the previous account
        return accounts.get(currentAccountIndex);
    }

    /**
     * Adds a new account to the customer's account list.
     *
     * @param account the account to be added
     */
    public void addAccount(Account account) {
        accounts.add(account);  // Add the account to the list
    }

    /**
     * Sets the current account to the account with the specified ID.
     * If the account is found, the currentAccountIndex is updated.
     *
     * @param accountId the ID of the account to set as the current account
     */
    public void setCurrentAccount(String accountId) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId().equals(accountId)) {
                currentAccountIndex = i;  // Set the current account index
                break;
            }
        }
    }

    /**
     * Returns the list of all accounts associated with the customer.
     *
     * @return an ArrayList of accounts
     */
    public ArrayList<Account> getAccounts() {
        return accounts;  // Return the list of accounts
    }
}