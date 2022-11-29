package Models;

import Asm2.Account;
import Asm2.Bank;
import Asm2.Customer;
import dao.CustomerDao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class DigitalBank extends Bank {
    //let over function
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
            LoanAccount account = new LoanAccount(customerId,accountNumber);
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
            SavingAccount account = new SavingAccount(customerId,accountNumber, initialBalance);
            customer.addAccount(account);
            return true;
        }
        System.out.println("This customer ID not in database");
        return false;
    }

//Phương thức showCustomers()
    public void showCustomers() {
        List<Customer> customers = CustomerDao.list();
        if (customers.size() == 0) System.out.println("Chưa có khách hàng nào trong danh sách!");
        else {
            for (Customer customer : customers) {
                if(customer instanceof DigitalCustomer) {
                   DigitalCustomer digitalCustomer = (DigitalCustomer) customer;
                   digitalCustomer.displayInformation();
                }
                customer.displayInformation();
            }
        }
    }
//Phương thức addCustomers(fileName)
    public void addCustomer(String inputPath) {
        //get relative path
        File f = new File("");
        String rootPath = f.getAbsolutePath() + "\\vn.funix.phucchFX01851.java\\";
        inputPath = Models.MiscFunction.convertSlashesToDoubleBackSlash(inputPath);//convert forward slash to back slash
        inputPath = "store\\customers.txt";//HARDCODE REMOVE BEFORE SUBMITTING
        //reading file
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(rootPath + inputPath));
            //importing customer using comma as delimiter
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                String customerId = sc.next();
                sc.skip(sc.delimiter());
                String name = sc.nextLine();
                if (addCustomer(customerId, name)) {
                    System.out.println("Thêm khách hàng " + name + " thành công");
                } else System.out.println("Thêm khách hàng " + name + " thất bại");
            }
            //catching ex
        } catch (FileNotFoundException e) {
            System.out.println("Tệp không tồn tại");
            System.out.println(e);
        } catch (InputMismatchException e) {
            System.out.println(e);
        } catch (NoSuchElementException e) {
            System.out.println("File Thiếu dấu ,");
            System.out.println(e);
        }
        //closing
        finally {
            if (sc != null) {
                sc.close();
            }
        }
        //Saving customers list to a file
        try {
            CustomerDao.save(getCustomers());
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    //Phương thức getCustomerById(List<Customer> customerList, String customerId)
    public DigitalCustomer getCustomerById(List<Customer> customerList, String customerId) {
        Customer customer = null;
        for (Customer i : customerList) {
            if (i.getCustomerId().equalsIgnoreCase(customerId))
                customer = i;
            break;
        }
        if (customer instanceof DigitalCustomer) return (DigitalCustomer) customer;
        return null;
    }

    //Phương thức withdraw(Scanner scanner, String customerId)

    //Phương thức tranfers(Scanner scanner, String customerId)

    //Phương thức isAccountExisted(List<Account> accountsList, Account newAccount)

    //Phương thức isCustomerExisted(List<Customer> customers, Customer newCustomer)
}
