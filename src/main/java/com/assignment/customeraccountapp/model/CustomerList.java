/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author Lahiru
 */


public class CustomerList {
    // HashMap for customers (key: customer ID, value: Customer object)
    private HashMap<String, Customer> customers = new HashMap<>();
    
    // HashMap for accounts (key: account ID, value: Account object)
    private HashMap<String, Account> accounts = new HashMap<>();

    public CustomerList() {
        loadCustomerData();
    }

    private void loadCustomerData() {
        // Create Customers
        Customer customer1 = new Customer("C0001", "John Smith", "john@example.com", "0412345678");
        Customer customer2 = new Customer("C0002", "Jane Doe", "jane@example.com", "0412345679");
        Customer customer3 = new Customer("C0003", "Mary Johnson", "mary@example.com", "0412345680");

        // Create Accounts for Customer 1
        Account account1 = new DailyAccessAccount("DA001", "C0001", 0.03, 1000);
        Account account2 = new GoalSaverAccount("GS001", "C0001", 0.05, 5000, 5000);
        Account account3 = new HomeLoanAccount("HL001", "C0001", 0.04, 200000, 30, "01/01/2020");
        
        customer1.addAccount(account1);
        customer1.addAccount(account2);
        customer1.addAccount(account3);
        
        // Create Accounts for Customer 2
        Account account4 = new DailyAccessAccount("DA002", "C0002", 0.03, 1500);
        Account account5 = new GoalSaverAccount("GS002", "C0002", 0.06, 8000, 8000);
        
        customer2.addAccount(account4);
        customer2.addAccount(account5);
        
        // Create Accounts for Customer 3
        Account account6 = new DailyAccessAccount("DA003", "C0003", 0.02, 500);
        Account account7 = new HomeLoanAccount("HL002", "C0003", 0.045, 300000, 25, "01/05/2019");

        customer3.addAccount(account6);
        customer3.addAccount(account7);

        // Add Customers to HashMap
        customers.put(customer1.getCustomerId(), customer1);
        customers.put(customer2.getCustomerId(), customer2);
        customers.put(customer3.getCustomerId(), customer3);

        // Add Accounts to HashMap
        accounts.put(account1.getAccountId(), account1);
        accounts.put(account2.getAccountId(), account2);
        accounts.put(account3.getAccountId(), account3);
        accounts.put(account4.getAccountId(), account4);
        accounts.put(account5.getAccountId(), account5);
        accounts.put(account6.getAccountId(), account6);
        accounts.put(account7.getAccountId(), account7);
    }

    // Find Customer by customer ID
    public Customer findCustomer(String customerId) {
        return customers.get(customerId);
    }

    // Find Account by account ID
    public Account findAccount(String accountId) {
        return accounts.get(accountId);
    }
    
    // Generate report using Formatter
    public void generateReport() {
        String reportName = "ReportForDate_" + new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime());
        String reportDate = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

        try (Formatter formatter = new Formatter(new PrintWriter(reportName))) {
            formatter.format("ReportForDate_%s%n", new SimpleDateFormat("dd_MM_yyyy").format(Calendar.getInstance().getTime()));
            formatter.format("Date: %s%n", reportDate);
            formatter.format("%n");

            for (Customer customer : customers.values()) {
                formatter.format("Customer ID: %s\t Name: %s%n", customer.getCustomerId(), customer.getName());
                formatter.format("%n");

                for (Account account : customer.getAccounts()) {
                    formatter.format("Account ID: %s\t Type: %s%n", account.getAccountId(), account.getClass().getSimpleName());

                    // Different account details based on the account type
                    if (account instanceof HomeLoanAccount) {
                        HomeLoanAccount homeLoanAccount = (HomeLoanAccount) account;
                        //formatter.format("Amount Owing: $%.2f%n", homeLoanAccount.getAmountOwing());
                        formatter.format("Interest Rate: %.4f%n", homeLoanAccount.getInterestRate());
                        formatter.format("Monthly Interest Charged: $%.2f%n", homeLoanAccount.getMonthlyInterestCharged());
                        //formatter.format("Original Loan Amount: $%.2f%n", homeLoanAccount.getOriginalLoan());
                        //formatter.format("Loan Start Date: %s%n", homeLoanAccount.getLoanStartDate());
                        //formatter.format("Duration of Loan: %d years%n", homeLoanAccount.getLoanDuration());
                    } else if (account instanceof DailyAccessAccount) {
                        DailyAccessAccount dailyAccount = (DailyAccessAccount) account;
                        //formatter.format("Account Balance: $%.2f%n", dailyAccount.getBalance());
                        //formatter.format("Minimum Monthly Balance: $%.2f%n", dailyAccount.getMinimumBalance());
                        formatter.format("Last Interest Earned: $%.4f%n", dailyAccount.getLastInterestEarned());
                        formatter.format("Interest Rate: %.4f%n", dailyAccount.getInterestRate());
                    }
                    formatter.format("%n");
                }
                formatter.format("===========================%n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error creating report file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to report file: " + e.getMessage());
        }
    }
}

