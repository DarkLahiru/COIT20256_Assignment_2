/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 * The GoalSaverAccount class represents a savings account that rewards account 
 * holders with interest if they meet a specific goal, such as maintaining or increasing
 * the balance by a set amount.
 * 
 * <p>This class extends the abstract Account class and provides specific implementations 
 * for applying interest based on the account balance and start-of-month balance, as well as 
 * retrieving detailed account information.</p>
 * 
 * <ul>
 *   <li>Account holders earn interest only if their balance increases by a specified amount.</li>
 *   <li>The start-of-month balance is tracked to calculate interest eligibility.</li>
 * </ul>
 * 
 * <p>Interest is only applied if the account holder increases their balance by $500 or more 
 * during the month.</p>
 * 
 * @author Lahiru
 */
public class GoalSaverAccount extends Account {
    private double balance;                // The current balance in the account
    private double startOfMonthBalance;    // The balance at the start of the month

    /**
     * Constructs a GoalSaverAccount with the specified account ID, customer ID, 
     * interest rate, initial balance, and start of month balance.
     *
     * @param accountId           the unique ID for the account
     * @param customerId          the ID of the customer owning this account
     * @param interestRate        the interest rate for the account
     * @param balance             the current balance in the account
     * @param startOfMonthBalance the balance at the start of the month
     */
    public GoalSaverAccount(String accountId, String customerId, double interestRate, double balance, double startOfMonthBalance) {
        super(accountId, customerId, interestRate);
        this.balance = balance;
        this.startOfMonthBalance = startOfMonthBalance;
    }

    /**
     * Applies monthly interest to the account. The account earns interest only if 
     * the balance at the end of the month is $500 or more than the start of month balance.
     * If this condition is met, the interest is calculated and added to the balance.
     */
    @Override
    public void applyMonthlyInterest() {
        // Check if balance has increased by at least $500 compared to the start of the month
        if (balance >= startOfMonthBalance + 500) {
            double interest = balance * getInterestRate();  // Calculate interest based on current balance
            balance += interest;  // Add the interest to the balance
        }
        startOfMonthBalance = balance;  // Update start of month balance for the next month
    }

    /**
     * Returns detailed information about the account, including the account ID, 
     * current balance, start of month balance, and interest rate.
     *
     * @return a formatted string with account details
     */
    @Override
    public String getAccountDetails() {
        return "Account ID: " + getAccountId() + "\nBalance: " + balance +
               "\nStart of Month Balance: " + startOfMonthBalance + "\nInterest Rate: " + getInterestRate();
    }
}
