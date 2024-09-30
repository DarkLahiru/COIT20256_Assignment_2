/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

/**
 *
 * @author Lahiru
 */
public class GoalSaverAccount extends Account {
    private double balance;
    private double startOfMonthBalance;

    public GoalSaverAccount(String accountId, String customerId, double interestRate, double balance, double startOfMonthBalance) {
        super(accountId, customerId, interestRate);
        this.balance = balance;
        this.startOfMonthBalance = startOfMonthBalance;
    }

    @Override
    public void applyMonthlyInterest() {
        if (balance >= startOfMonthBalance + 500) {
            double interest = balance * getInterestRate();
            balance += interest;
        }
        startOfMonthBalance = balance;
    }

    @Override
    public String getAccountDetails() {
        return "Account ID: " + getAccountId() + "\nBalance: " + balance +
               "\nStart of Month Balance: " + startOfMonthBalance + "\nInterest Rate: " + getInterestRate();
    }
}
