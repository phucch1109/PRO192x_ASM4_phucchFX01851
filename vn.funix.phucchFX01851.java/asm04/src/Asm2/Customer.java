package Asm2;

import dao.AccountDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    static final long serialVersionUID = 112;
    private List<Account> accounts;

    public Customer(String name, String id) {
        super(name, id);
        accounts = new ArrayList<>();
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void importDataAccounts() {
        List<Account> accountList = AccountDao.list();
        List<Account> accountList1 = accountList.stream().filter(account -> account.getCustomerId().equals(this.getCustomerId())).collect(Collectors.toList());
        this.accounts = accountList1;
    }

    // Customer(List<String> values) để phục vụ việc đọc dữ liệu từ file text
    public Customer(List<String> values) {
        this(values.get(1), values.get(0));
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String isPremium() {
        for (Account i :
                accounts) {
            if (i.isPremium()) return "Premium";
        }
        return "Normal";
    }


    public boolean addAccount(Account account) {
        for (Account i :
                accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(i.getAccountNumber())) {
                System.out.println("Error , account already exist");
                return false;
            }

        }
        accounts.add(account);
        return true;
    }

    public double getBalance() {
        double total = 0;
        for (Account i :
                accounts) {
            total += i.getBalance();
        }
        return total;
    }

    //Phương thức displayInformation() Hiển thị thông tin của customer
    public void displayInformation() {
        System.out.printf("%-12s%-3s%16s%-1s%7s%-1s%13.2f%1s\n", getCustomerId(), " | ", getName(), " | ", isPremium(), " | ", getBalance(), "đ");
        if (accounts.size() == 0) System.out.println("This user has no account");
        else
            for (int i = 0; i < accounts.size(); i++) {
                System.out.printf("%-6d%6s%3s%42.2f%1s\n", i + 1, accounts.get(i).getAccountNumber(), " | ", accounts.get(i).getBalance(), "đ");
            }
    }
    //Phương thưức kiểm tra số TK trong customer
    public boolean isAccountExisted(String accountNumber) {
        if(accounts.size()!=0) {
            for (Account account:accounts
                 ) {
                if(account.getAccountNumber().equals(accountNumber)) return true;
            }
        }
        return false;
    }


}
