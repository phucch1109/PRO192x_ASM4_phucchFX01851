package Models;

import Asm2.Account;

import java.io.Serializable;


public class LoanAccount extends Account implements Serializable,Report, Withdraw {
    final double LOAN_ACCOUNT_WITHDRAW_FEE = 0.05;
    final double LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE = 0.01;
    final double LOAN_ACCOUNT_MAX_BALANCE = 100000000;
    final int LOAN_TYPE_ACCOUNT = 0;

    public LoanAccount(String customerId,String accountNumber) {
        super(accountNumber, 0,customerId);
        this.setBalance(LOAN_ACCOUNT_MAX_BALANCE);
        this.setType(LOAN_TYPE_ACCOUNT);
    }

    @Override
    public void log(double amount) {
        System.out.println("-------------+-----------+---------------");
        System.out.printf("%30s\n", "BIEN LAI GIAO DICH LOAN");
        System.out.printf("NGAY G/D : %28s\n", MiscFunction.getDateTime());
        System.out.printf("ATM ID: %30s\n", "DIGITAL-BANK-ATM 2022");
        System.out.printf("So TK: %31s\n", getAccountNumber());
        System.out.printf("SO TIEN: %29.1f\n", amount);
        System.out.printf("SO DU: %31.1f\n", getBalance());
        System.out.printf("PHI + VAT: %27.1f\n", transactionFee(amount));
        System.out.println("-------------+-----------+---------------");
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            double total = getBalance() - amount - transactionFee(amount);
            setBalance(total);
            log(amount);
            return true;
        }
        return false;
    }

    public double transactionFee(double amount) {
        if (!isPremium()) return amount * LOAN_ACCOUNT_WITHDRAW_FEE;
        return amount * LOAN_ACCOUNT_WITHDRAW_PREMIUM_FEE;
    }

    @Override
    public boolean isAccepted(double amount) {
        double total = getBalance() - amount - transactionFee(amount);
        if (amount >= LOAN_ACCOUNT_MAX_BALANCE) {
            System.out.println("Hạn mức không được qúa giới hạn 100.000.000đ");
            return false;
        }
        if (total < 50000) {
            System.out.println("Hạn mức còn lại sau khi rút tiền không được nhỏ hơn 50.000đ");
            return false;
        }
        return true;

    }


}
