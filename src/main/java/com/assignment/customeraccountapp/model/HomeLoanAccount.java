/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 * The HomeLoanAccount class represents a loan account used for financing a home purchase.
 * It tracks the original loan amount, the amount currently owed, the loan duration,
 * and the start date of the loan. Interest is applied monthly to the remaining loan balance.
 * 
 * <p>This class extends the abstract Account class and provides specific implementations
 * for applying interest to the loan balance, as well as retrieving detailed account information.</p>
 * 
 * <ul>
 *   <li>Interest is calculated monthly based on the amount owed.</li>
 * </ul>
 * 
 * <p>Interest is compounded monthly on the remaining balance of the loan.</p>
 * 
 * @author Lahiru
 */
public class HomeLoanAccount extends Account {
    private double originalLoan;     // The original loan amount
    private double amountOwing;      // The amount currently owed on the loan
    private int loanDuration;        // The duration of the loan in years
    private String loanStartDate;    // The start date of the loan

    /**
     * Constructs a HomeLoanAccount with the specified account ID, customer ID, interest rate,
     * loan amount, loan duration, and loan start date.
     *
     * @param accountId     the unique ID for the account
     * @param customerId    the ID of the customer owning this account
     * @param interestRate  the interest rate for the loan
     * @param loanAmount    the original loan amount
     * @param loanDuration  the duration of the loan in years
     * @param loanStartDate the start date of the loan
     */
    public HomeLoanAccount(String accountId, String customerId, double interestRate, double loanAmount, int loanDuration, String loanStartDate) {
        super(accountId, customerId, interestRate);
        this.originalLoan = loanAmount;
        this.amountOwing = loanAmount;  // Initially, the amount owing is the same as the loan amount
        this.loanDuration = loanDuration;
        this.loanStartDate = loanStartDate;
    }

    /**
     * Applies monthly interest to the loan balance. The interest is calculated 
     * based on the remaining amount owed and added to the total amount owing.
     */
    @Override
    public void applyMonthlyInterest() {
        double interest = amountOwing * getInterestRate();  // Calculate interest based on the amount owing
        amountOwing += interest;  // Add the interest to the amount owing
    }

    /**
     * Returns detailed information about the loan account, including the account ID,
     * original loan amount, current amount owing, loan duration, start date, and interest rate.
     *
     * @return a formatted string with loan account details
     */
    @Override
    public String getAccountDetails() {
        return "Account ID: " + getAccountId() + "\nOriginal Loan: " + originalLoan + 
               "\nAmount Owing: " + amountOwing + "\nLoan Duration: " + loanDuration +
               " years\nLoan Start Date: " + loanStartDate + "\nInterest Rate: " + getInterestRate();
    }
    
    /**
     * Calculates and returns the monthly interest charged on the remaining loan balance.
     *
     * @return the monthly interest charged
     */
    public double getMonthlyInterestCharged() {
        return amountOwing * getInterestRate() / 12;  // Monthly interest calculation based on the amount owing
    }

    // Getters for the loan details

    /**
     * Returns the amount currently owed on the loan.
     *
     * @return the amount owing
     */
    public double getAmountOwing() {
        return amountOwing;
    }

    /**
     * Returns the original loan amount.
     *
     * @return the original loan amount
     */
    public double getOriginalLoan() {
        return originalLoan;
    }

    /**
     * Returns the start date of the loan.
     *
     * @return the loan start date
     */
    public String getLoanStartDate() {
        return loanStartDate;
    }

    /**
     * Returns the duration of the loan in years.
     *
     * @return the loan duration in years
     */
    public int getLoanDuration() {
        return loanDuration;
    }
}
