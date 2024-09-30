/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 *
 * @author Lahiru
 */

public abstract class Account {
    private String accountId;
    private String customerId;
    private double interestRate;
    
    
    public abstract void applyMonthlyInterest();
    
    public Account(String accountId, String customerId, double interestRate) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.interestRate = interestRate;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public abstract String getAccountDetails();
}
