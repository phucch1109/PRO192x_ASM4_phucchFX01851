package Models;

import Asm2.Account;
import Asm2.Bank;
import Asm2.Customer;
import dao.AccountDao;
import dao.CustomerDao;
import exception.CustomerIdNotValidException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DigitalBank extends Bank {

    //Phương thức showCustomers()
    public static void showCustomers() {
        List<Customer> customerList = CustomerDao.list();
        if (customerList.size() == 0) {
            System.out.println("Ngân hàng không có khách hàng!");
            return;
        }
        //fill account
        List<Account> accountList = AccountDao.list();
        int countFailAttempt = 0;
        if (accountList.size() != 0) {
            for (Account acc : accountList) {
                boolean addAccountSuccess = false;
                for (Customer customer : customerList) {
                    if (customer.getCustomerId().equals(acc.getCustomerId())) {
                        customer.addAccount(acc);
                        addAccountSuccess =true;
                        break;
                    }
                }
                if(!addAccountSuccess) countFailAttempt++;
            }
            if(countFailAttempt>0) System.out.println("Dữ liệu lỗi, có "+ countFailAttempt+" tài khoản không có chủ" );
        }
        //displaying loop
        for (Customer customer : customerList
        ) {
            if (customer instanceof DigitalCustomer) {
                DigitalCustomer dCuscomer = (DigitalCustomer) customer;
                dCuscomer.displayInformation();
            } else{
                customer.displayInformation();
            }
        }
    }

    //Phương thức addCustomers(fileName)
    public static void addCustomer(String inputPath) {
        List<Customer> customerList = CustomerDao.list();
        //get relative path
        File f = new File("");
        String rootPath = f.getAbsolutePath() + "\\vn.funix.phucchFX01851.java\\";
        inputPath = Models.MiscFunction.convertSlashesToDoubleBackSlash(inputPath);//convert forward slash to back slash
        inputPath = "store\\customers.txt";//HARDCODE REMOVE BEFORE SUBMITTING
        //reading file
        try (Scanner sc = new Scanner(new FileReader(rootPath + inputPath))) {
            //importing customer using comma as delimiter
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                String customerId = sc.next();
                sc.skip(sc.delimiter());
                String name = sc.nextLine();
                boolean existed = false;
                //check if customerId is duplicated
                for (Customer customer : customerList) {
                    if (customer.getCustomerId().equals(customerId)) {
                        existed = true;
                        break;
                    }
                }
                if (!existed) {
                    customerList.add(new DigitalCustomer(name, customerId));
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
        //Saving customers list to a file
        CustomerDao.save(customerList);
    }


    //Phương thức getCustomerById(List<Customer> customerList, String customerId)
    public static Customer getCustomerById(List<Customer> customerList, String customerId) {
        Customer customer = null;
        for (Customer i : customerList) {
            if (i.getCustomerId().equals(customerId)){
                customer = i;
                break;
            }
        }

        if(customer!= null) customer.importDataAccounts();
        else throw new CustomerIdNotValidException("Không có khách hàng nào có số CMT trên");
        return customer;
    }

    public static boolean withdrawAccount(Customer customer, String accountNumber, double amount) {
        if (!(customer instanceof DigitalCustomer)) return false ;
        DigitalCustomer digitalCustomer = (DigitalCustomer) customer;
        if (customer != null) {
            return digitalCustomer.withdrawAccount(accountNumber,amount);
        } else System.out.println("customerID not found in database");
        return false;
    }


    public static void addSavingAccount(String customerId,String accountNumber,double amount) {
        SavingAccount account = new SavingAccount(customerId, accountNumber, amount);
        AccountDao.update(account);
    }

    public static boolean isAccountNumberExisted(List<Account> accountsList, String accountNumber) {
//        for (Account account :
//                accountsList) {
//            if (account.getAccountNumber().equals(accountNumber)) {
//                return true;
//            }
//        }
//        return false;
        return accountsList.stream().anyMatch(account -> account.getAccountNumber().equals(accountNumber));
    }


    //Phương thức isCustomerExisted(List<Customer> customers, Customer newCustomer)
    public static boolean isCustomerIdExisted(List<Customer> customerList, String customerId) {
//        for (Customer customer :
//                customerList) {
//            if (customer.getCustomerId().equals(customerId)) {
//                return true;
//            }
//        }
        boolean result = customerList.stream().anyMatch(customer -> customer.getCustomerId().equals(customerId));
        if (!result) throw new CustomerIdNotValidException("Không có khách hàng nào có số CMT trên");
        return true;
    }

    public static Account getAccountByAccountNumber(List<Account> accountsList, String accountNumber) {
        for (Account account :
                accountsList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static void printReceipt (String senderAccount,String receiverAccount,double amount, double balanceRemain){
        System.out.println("-------------+-----------+---------------");
        System.out.printf("%30s\n", "BIEN LAI GIAO DICH SAVINGS");
        System.out.printf("NGAY G/D : %27s\n", MiscFunction.getDateTime());
        System.out.printf("ATM ID: %30s\n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("So TK: %31s\n", senderAccount);
        System.out.printf("So TK NHẬN: %31s\n", receiverAccount);
        System.out.printf("SỐ TIỀN CHUYỂN: %29.1f\n", amount);
        System.out.printf("SỐ DƯ TK: %31.1f\n", balanceRemain);
        System.out.printf("PHI + VAT: %27.1f\n", 0.0);
        System.out.println("-------------+-----------+---------------");
    }

    public static void showTransactionByCustomer(Customer customer) {
        DigitalCustomer digitalCustomer ;
        if(customer instanceof DigitalCustomer) {
            digitalCustomer = (DigitalCustomer) customer;
        }else{
            System.out.println("error not digital");
            return;
        }
        if (digitalCustomer !=null)
        digitalCustomer.displayTransactionsList();
    }
}
