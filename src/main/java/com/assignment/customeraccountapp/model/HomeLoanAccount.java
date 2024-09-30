/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 *
 * @author Lahiru
 */
public class HomeLoanAccount extends Account {
    private double originalLoan;
    private double amountOwing;
    private int loanDuration;
    private String loanStartDate;

    public HomeLoanAccount(String accountId, String customerId, double interestRate, double loanAmount, int loanDuration, String loanStartDate) {
        super(accountId, customerId, interestRate);
        this.originalLoan = loanAmount;
        this.amountOwing = loanAmount; // Initially, amount owing is the same as loan amount
        this.loanDuration = loanDuration;
        this.loanStartDate = loanStartDate;
    }

    @Override
    public void applyMonthlyInterest() {
        double interest = amountOwing * getInterestRate();
        amountOwing += interest;
    }

    @Override
    public String getAccountDetails() {
        return "Account ID: " + getAccountId() + "\nOriginal Loan: " + originalLoan + 
               "\nAmount Owing: " + amountOwing + "\nLoan Duration: " + loanDuration +
               " years\nLoan Start Date: " + loanStartDate + "\nInterest Rate: " + getInterestRate();
    }
    
    public double getMonthlyInterestCharged() {
        return amountOwing * getInterestRate() / 12; // Monthly interest calculation
    }
}
