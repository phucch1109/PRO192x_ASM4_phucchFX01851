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

    //Ph????ng th???c withdraw(double amount) ki???m tra ??i???u ki???n r??t ti???n (quy ?????nh ??? c??c asm tr?????c),
    // n???u h???p l??? th?? g???i ph????ng th???c t???o m???i giao d???ch v?? c???p nh???t s??? d?? c???a t??i kho???n, n???u kh??ng h???p l??? th??
    // tr??? v??? th??ng b??o.

    //Ph????ng th???c transfers(Account receiveAccount, double amount) ki???m tra ??i???u ki???n chuy???n ti???n
    // (gi???ng ??i???u ki???n r??t ti???n), n???u h???p l??? th?? t???o m???t giao d???ch tr??? ti???n c???a ng?????i g???i v?? giao d???ch c???ng
    // ti???n cho ng?????i nh???n, n???u kh??ng h???p l??? th?? tr??? v??? l???i.
}
