/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 * The DailyAccessAccount class represents a type of bank account where the user 
 * has daily access to their funds, with no restrictions on withdrawals or deposits.
 * This class supports the application of monthly interest, tracking of the balance, 
 * and management of withdrawals and deposits.
 * 
 * <p>It extends the abstract Account class and provides specific implementations 
 * for applying interest, handling deposits and withdrawals, and getting detailed account information.</p>
 * 
 * <ul>
 *   <li>Account holders can withdraw or deposit money at any time.</li>
 *   <li>Interest is applied monthly based on the minimum balance for that month.</li>
 * </ul>
 * 
 * @author Lahiru
 */
public class DailyAccessAccount extends Account {
    private double balance;         // The current balance in the account
    private double minimumBalance;  // The minimum balance maintained in the account

    /**
     * Constructs a DailyAccessAccount with the specified account ID, customer ID, 
     * interest rate, and initial balance.
     *
     * @param accountId   the unique ID for the account
     * @param customerId  the ID of the customer owning this account
     * @param interestRate the interest rate for the account
     * @param balance     the initial balance in the account
     */
    public DailyAccessAccount(String accountId, String customerId, double interestRate, double balance) {
        super(accountId, customerId, interestRate);
        this.balance = balance;
        this.minimumBalance = balance;  // Initially, the minimum balance is the current balance
    }

    /**
     * Applies monthly interest to the account. The interest is calculated based on
     * the minimum balance for the month and added to the current balance.
     */
    @Override
    public void applyMonthlyInterest() {
        double interest = minimumBalance * getInterestRate();  // Calculate interest on minimum balance
        balance += interest;  // Add interest to balance
        minimumBalance = balance;  // Reset minimum balance to current balance
    }

    /**
     * Returns detailed information about the account, including the account ID, 
     * current balance, minimum balance, and interest rate.
     *
     * @return a formatted string with account details
     */
    @Override
    public String getAccountDetails() {
        return "Account ID: " + getAccountId() + "\nBalance: " + balance +
               "\nMinimum Balance: " + minimumBalance + "\nInterest Rate: " + getInterestRate();
    }
    
    /**
     * Calculates and returns the last interest earned by the account, 
     * based on the minimum balance and monthly interest rate.
     *
     * @return the last interest earned in the previous month
     */
    public double getLastInterestEarned() {
        // Calculate the last interest earned (for example, you could store this in the class for accuracy)
        return minimumBalance * getInterestRate() / 12; // Example monthly interest calculation
    }
    
    /**
     * Withdraws a specified amount from the account, provided there are sufficient funds.
     * If the withdrawal is successful, the minimum balance is updated accordingly.
     *
     * @param amount the amount to withdraw
     * @return true if the withdrawal is successful, false if there are insufficient funds
     */
    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;  // Deduct the amount from the balance
            if (balance < minimumBalance) {
                minimumBalance = balance;  // Update minimum balance if it is lower than the current balance
            }
            return true;  // Successful withdrawal
        }
        return false;  // Insufficient funds
    }

    /**
     * Deposits a specified amount into the account and updates the balance and minimum balance.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance += amount;  // Add the deposit amount to the balance
        if (balance > minimumBalance) {
            minimumBalance = balance;  // Update minimum balance if the new balance is higher
        }
    }

    // Getters for balance and minimum balance

    /**
     * Returns the current balance of the account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns the minimum balance maintained in the account.
     *
     * @return the minimum balance
     */
    public double getMinimumBalance() {
        return minimumBalance;
    }
}
