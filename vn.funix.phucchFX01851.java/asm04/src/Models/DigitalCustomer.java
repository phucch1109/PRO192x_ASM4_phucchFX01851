package Models;

import Asm2.Account;
import Asm2.Customer;
import dao.AccountDao;
import dao.TransactionDao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DigitalCustomer extends Customer implements Serializable {
    static final long serialVersionUID = 112;
    private final List<Transaction> transactions = new ArrayList<Transaction>();

    public DigitalCustomer(String name, String id) {
        super(name, id);
    }


    @Override
    public void displayInformation() {
        System.out.printf("%-12s%-3s%-16s%-1s%7s%-1s%13.2f%1s\n", getCustomerId(), " | ", getName(), " | ", isPremium(), " | ", getBalance(), "đ");
        if (getAccounts().size() == 0) System.out.println("Khách hàng này không có tài khoản");
        else
            for (int i = 0; i < getAccounts().size(); i++) {
                System.out.printf("%-6d%6s%3s%28s%14.2f%1s\n", i + 1, getAccounts().get(i).getAccountNumber(), " | ", getAccountType(getAccounts().get(i)) + " |", getAccounts().get(i).getBalance(), "đ");
            }
    }

    //method return desirable string by checking account type
    public String getAccountType(Account account) {
        if (account.getType() == 0) {
//        if (account instanceof LoanAccount) {
            return "LOAN";
        }
        if (account.getType() == 1) {
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
            boolean success = false;
            if (account instanceof LoanAccount) {
                LoanAccount loanAccount = (LoanAccount) account;
                success = loanAccount.withdraw(amount);
            } else if (account.getClass() == SavingAccount.class) {
                SavingAccount savingAccount = (SavingAccount) account;
                success = savingAccount.withdraw(amount);
            } else {
                System.out.println("This account is neither loan nor saving");
                return false;
            }
            if (success) {
                AccountDao.update(account);
                List<Transaction> transactionList = TransactionDao.list();
                transactionList.add(new Transaction(accountNumber, amount, true, "Withdraw"));
                TransactionDao.save(transactionList);
                System.out.println("Rút tiền thành công");
                return true;
            }
        }else System.out.println("account number not found in database");
        return false;
    }




    //Phương thức displayTransactionInformation() hiển thị thông tin customer,
    // thông tin các tài khoản và thông tin các giao dịch của khách hàng hiện tại.
    public void displayTransactionsList() {
        List<Transaction> transactionList = TransactionDao.list();
        for (Transaction transaction:transactionList) {
            for (Account account:getAccounts()) {
                if(account.getAccountNumber().equals(transaction.getAccountNumber())){
                    transactions.add(transaction);
                    break;
                }
            }
        }
        for (Transaction i : transactions) {
            System.out.printf("[GD]  %6s | %9s | %10.2f d | %s\n", i.getAccountNumber(), i.getType(), i.getAmount(), i.getTime());
        }
    }

}
