package Asm2;

import Models.MiscFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Bank {
    private final String id;
    private List<Customer> customers;

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
        this.customers = new ArrayList<>();
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public String getId() {
        return id;
    }

    public boolean addCustomer(Customer newCustomer) {
        for (Customer i : customers) {
            if (i.getCustomerId().equalsIgnoreCase(newCustomer.getCustomerId())) {
                System.out.println("customer's id already in database");
                return false;
            }
        }
        customers.add(newCustomer);
        return true;
    }

    public Customer getCustomerById(String customerId) {
        if(customers.size()==0) return null;
        for (Customer i : customers) {
            if (i.getCustomerId().equalsIgnoreCase(customerId))
                return i;
        }
        return null;
    }

    public ArrayList<Customer> getCustomerByName(String name) {
        ArrayList<Customer> result = new ArrayList<>();
        for (Customer i : customers) {
            if (i.getName().toLowerCase().contains(name.toLowerCase()))
                result.add(i);
        }
        return result;
    }

    public String createNewAccountNumber() {
        boolean unique;
        String number;
        do {
            unique = true;
            number = MiscFunction.randomNumberString(6);
            for (Customer customer : customers) {
                for (Account account : customer.getAccounts()) {
                    if (account.getAccountNumber().equalsIgnoreCase(number)) {
                        unique = false;
                        break;
                    }
                }
                if (!unique) break;
            }
        } while (!unique);
        return number;
    }

    public boolean isAccountNumberExisted(String accountNumber) {
        for (Customer customer : customers) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                    return true;
                }
            }
        }
        return false;
    }
}
