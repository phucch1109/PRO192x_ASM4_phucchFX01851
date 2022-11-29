package Models;

import Asm2.Account;
import Asm2.Customer;

import java.util.ArrayList;
import java.util.List;

public class DigitalCustomer extends Customer {
    private final List<Transaction> transactions= new ArrayList<Transaction>();
    public DigitalCustomer(String name, String id) {
        super(name, id);
    }


    @Override
    public void displayInformation() {
        System.out.printf("%-12s%-3s%16s%-1s%7s%-1s%13.2f%1s\n", getCustomerId(), " | ", getName(), " | ", isPremium(), " | ", getBalance(), "đ");
        if (getAccounts().size() == 0) System.out.println("This user has no account");
        else
            for (int i = 0; i < getAccounts().size(); i++) {
                // System.out.println(i+1 + "   " + accounts.get(i).getAccountNumber() + "  |                " + String.format("%,.2f",accounts.get(i).getBalance())+"đ");
                System.out.printf("%-6d%6s%3s%28s%14.2f%1s\n", i + 1, getAccounts().get(i).getAccountNumber(), " | ", getAccountType(getAccounts().get(i)) + " |", getAccounts().get(i).getBalance(), "đ");
            }
    }
    //method return desirable string by checking account type
    public String getAccountType(Account account) {
        if (account.getType()==0) {
//        if (account instanceof LoanAccount) {
            return "LOAN";
        }
        if (account.getType()==1){
//        if (account.getClass() == SavingAccount.class) {
            return "SAVING";
        }

        return "normal";
    }


    public boolean withdrawAccount(String accountNumber, double amount) {
        Account account = null;
        //get account
        for (Account i : getAccounts()) {
            if (i.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                account = i;
            }
        }
        //executing withdraw
        if (account != null) {
            if (account instanceof LoanAccount) {
                LoanAccount loanAccount = (LoanAccount) account;
                boolean success = loanAccount.withdraw(amount);
                if(success) transactions.add(new Transaction(accountNumber,amount,MiscFunction.getDateTime(),true));
                return success;
            } else if (account.getClass() == SavingAccount.class) {
                SavingAccount savingAccount = (SavingAccount) account;
                boolean success = savingAccount.withdraw(amount);
                if (success)transactions.add(new Transaction(accountNumber,amount,MiscFunction.getDateTime(),true));
                return success;
            } else
                System.out.println("This account is neither loan nor saving account");
            return false;

        }
        System.out.println("account number not found in database");
        return false;
    }

    public void showTransaction() {
        for (Transaction i:transactions) {
            System.out.printf("%-12s | %6s | %10.2f d | %s\n",getCustomerId(),i.getAccountNumber(),+i.getAmount(),i.getTime());
        }
    }
}
