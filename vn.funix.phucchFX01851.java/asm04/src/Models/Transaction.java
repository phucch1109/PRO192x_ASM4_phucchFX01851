package Models;

import java.util.UUID;

public class Transaction {
    static final long serialVersionUID =112;
    private String id;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Transaction(String accountNumber, double amount, String time, boolean status) {
        this.id = String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time = time;
        this.status = status;
    }

    //WIP
    @Override
    public String toString() {
        return "%-12s | %6s | %10.2f d | %s\n"+getAmount();
    }
}
