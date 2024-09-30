/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.assignment.customeraccountapp.model;

import java.util.ArrayList;

/**
 *
 * @author Lahiru
 */

public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private ArrayList<Account> accounts;
    private int currentAccountIndex = 0;

    public Customer(String customerId, String name, String email, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accounts = new ArrayList<>();
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public Account getFirstAccount() {
        currentAccountIndex = 0;
        return accounts.get(currentAccountIndex);
    }

    public Account getNextAccount() {
        currentAccountIndex = (currentAccountIndex + 1) % accounts.size();
        return accounts.get(currentAccountIndex);
    }

    public Account getPreviousAccount() {
        currentAccountIndex = (currentAccountIndex - 1 + accounts.size()) % accounts.size();
        return accounts.get(currentAccountIndex);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }
    
    public void setCurrentAccount(String accountId) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountId().equals(accountId)) {
                currentAccountIndex = i;
                break;
            }
        }
    }
    
    public ArrayList<Account> getAccounts() {
        return accounts;  // Return the list of accounts
    }
}

