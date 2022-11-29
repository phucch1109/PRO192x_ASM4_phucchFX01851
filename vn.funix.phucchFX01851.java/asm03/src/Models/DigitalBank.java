package Models;

import Asm2.Account;
import Asm2.Bank;
import Asm2.Customer;

public class DigitalBank extends Bank {
    @Override
    public DigitalCustomer getCustomerById(String customerId) {
        Customer customer = null;
        for (Customer i : getCustomers()) {
            if (i.getCustomerId().equalsIgnoreCase(customerId))
                customer = i;
            break;
        }
        if (customer instanceof DigitalCustomer) return (DigitalCustomer) customer;
        return null;
    }

    //overloading method used to check specific number account within customer
    public boolean isAccountNumberExisted(String accountNumber, String customerID) {
        DigitalCustomer customer = getCustomerById(customerID);
        if (customer != null) {
            for (Account account : customer.getAccounts()
            ) {
            if(account.getAccountNumber().equalsIgnoreCase(accountNumber)) return true;
            }
        }
        return false;
    }
    //add digital customer method
    public boolean addCustomer(String customerId, String name) {
        for (Customer i : getCustomers()) {
            if (i.getCustomerId().equalsIgnoreCase(customerId)) {
                System.out.println("customer's id already in database");
                return false;
            }
        }
        this.getCustomers().add(new DigitalCustomer(name, customerId));
        return true;
    }

    public boolean addLoanAccount(String customerId, String accountNumber) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            //kiem tra account number co ton tai ko
            for (Account i :
                    customer.getAccounts()) {
                if (i.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                    System.out.println("This account number already existed");
                    return false;
                }
            }
            //them loan account
            LoanAccount account = new LoanAccount(accountNumber);
            customer.addAccount(account);
            return true;
        }
        System.out.println("This customer ID not in database");
        return false;
    }

    public boolean addSavingAccount(String customerId, String accountNumber, double initialBalance) {
        Customer customer = getCustomerById(customerId);
        if (customer != null) {
            //kiem tra account number co ton tai ko
            for (Account i :
                    customer.getAccounts()) {
                if (i.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                    System.out.println("This account number already existed");
                    return false;
                }
            }
            //them saving account
            SavingAccount account = new SavingAccount(accountNumber, initialBalance);
            customer.addAccount(account);
            return true;
        }
        System.out.println("This customer ID not in database");
        return false;
    }

    public boolean withdrawAccount(String customerId, String accountNumber, double amount) {
        DigitalCustomer customer = getCustomerById(customerId);
        if (customer != null) {
        return customer.withdrawAccount(accountNumber,amount);
        } else System.out.println("customerID not found in database");
        return false;
    }




}
