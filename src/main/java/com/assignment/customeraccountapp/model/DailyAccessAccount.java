/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 *
 * @author Lahiru
 */

public class DailyAccessAccount extends Account {
    private double balance;
    private double minimumBalance;

    public DailyAccessAccount(String accountId, String customerId, double interestRate, double balance) {
        super(accountId, customerId, interestRate);
        this.balance = balance;
        this.minimumBalance = balance;
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = minimumBalance * getInterestRate();
        balance += interest;
        minimumBalance = balance;  // Resetting minimum balance to current balance
    }

    @Override
    public String getAccountDetails() {
        return "Account ID: " + getAccountId() + "\nBalance: " + balance +
               "\nMinimum Balance: " + minimumBalance + "\nInterest Rate: " + getInterestRate();
    }
    
    public double getLastInterestEarned() {
        // Calculate the last interest earned (you might want to store this in the class)
        return minimumBalance * getInterestRate() / 12; // Example calculation
    }
}
