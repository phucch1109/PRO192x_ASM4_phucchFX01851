package Models;

import Asm2.Account;
import Asm2.Customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DigitalCustomer extends Customer implements Serializable {
    static final long serialVersionUID = 112;
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
    //Phương thức displayTransactionInformation() hiển thị thông tin customer,
    // thông tin các tài khoản và thông tin các giao dịch của khách hàng hiện tại.
    public void displayTransactionsList() {
        for (Transaction i:transactions) {
            System.out.printf("%-12s | %6s | %10.2f d | %s\n",getCustomerId(),i.getAccountNumber(),+i.getAmount(),i.getTime());
        }
    }


    //Phương thức getAccounts() lấy ra những account có customerId bằng customerId hiện tại.
    // Phương thức này sử dụng stream.filter để lọc từ danh sách account lấy từ file.

    //Phương thức getAccountByAccountNumber(List<Account> accounts, String accountNumber)
    // lấy ra account từ trong danh sách.



    //Phương thức withdraw(Scanner ) yêu cầu nhập số tài khoản
    // (lấy danh sách accounts từ getAccounts() để kiểm tra xem tài khoản có tồn tại hay không),
    // nhập số tiền rút sau đó gọi hàm rút tiền của account.

    //Phương thức transfers(Scanner scanner) yêu cầu nhập tài khoản dùng để chuyển tiền,
    // nhập tài khoản nhận tiền (kiểm tra tính hợp lệ của từng tài khoản), sau đó yêu cầu nhập
    // số tiền rút, xác nhận việc chuyển tiền và sau khi thỏa mãn hết các điều kiện sẽ gọi
    // hàm transfer của account gửi.
}
