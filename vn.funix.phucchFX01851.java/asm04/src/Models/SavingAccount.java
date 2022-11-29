package Models;

import Asm2.Account;

import java.io.Serializable;

public class SavingAccount extends Account implements Serializable,Report, Withdraw {
    final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    final int SAVING_TYPE_ACCOUNT = 1;
    public SavingAccount(String customerID,String accountNumber, double balance) {
        super(accountNumber, balance,customerID);
        setType(SAVING_TYPE_ACCOUNT);
    }

    @Override
    public void log(double amount) {
        System.out.println("-------------+-----------+---------------");
        System.out.printf("%30s\n", "BIEN LAI GIAO DICH SAVING");
        System.out.printf("NGAY G/D : %27s\n", MiscFunction.getDateTime());
        System.out.printf("ATM ID: %30s\n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("So TK: %31s\n", getAccountNumber());
        System.out.printf("SO TIEN: %29.1f\n", amount);
        System.out.printf("SO DU: %31.1f\n", getBalance());
        System.out.printf("PHI + VAT: %27.1f\n", 0.0);
        System.out.println("-------------+-----------+---------------");
    }

    @Override
    public boolean withdraw(double amount) {
        if(isAccepted(amount)) {
            double total = getBalance() - amount;
            setBalance(total);
            log(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean isAccepted(double amount) {
        if(amount<50000) {
            System.out.println("So tien khong duoc duoi 50000d");
            return false;
        }
        double total = getBalance() - amount;
        if (total<50000) {
            System.out.println("han muc con lai sau khi rut khong duoc nho hon 50000d");
            return false;
        }
        if(amount>SAVINGS_ACCOUNT_MAX_WITHDRAW && !isPremium()) {
            System.out.println("khong duoc rut qua 5.000.000 do day la tai khoan thuong");
            return false;
        }
        if (amount%10000!=0) {
            System.out.println("So tien rut phai la boi so cua 10000d");
            return false;
        }

        return true;
    }

    //Phương thức withdraw(double amount) kiểm tra điều kiện rút tiền (quy định ở các asm trước),
    // nếu hợp lệ thì gọi phương thức tạo mới giao dịch và cập nhật số dư của tài khoản, nếu không hợp lệ thì
    // trả về thông báo.

    //Phương thức transfers(Account receiveAccount, double amount) kiểm tra điều kiện chuyển tiền
    // (giống điều kiện rút tiền), nếu hợp lệ thì tạo một giao dịch trừ tiền của người gửi và giao dịch cộng
    // tiền cho người nhận, nếu không hợp lệ thì trả về lỗi.
}
