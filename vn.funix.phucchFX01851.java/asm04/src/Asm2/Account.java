package Asm2;

import java.io.Serializable;

public class Account implements Serializable {
    static final long serialVersionUID = 112;
    private String customerId;
    private String accountNumber;
    private double balance;
    private int type;
    public Account(String accountNumber, double balance,String customerId) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isPremium() {
        return this.balance >= 10000000;
    }

}
