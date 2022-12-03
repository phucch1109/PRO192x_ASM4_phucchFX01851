package dao;

import Asm2.Account;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final static String FILE_PATH = ".\\vn.funix.phucchFX01851.java\\store\\accounts.dat";
    public static void save(List<Account> accounts) {
        File file = new File(FILE_PATH);
        System.out.println("Accounts data saved in: " + file.getAbsolutePath());
        BinaryFileService.writeFile(file.getAbsolutePath(),accounts);
    }
    public static List<Account> list() {
        File yourFile = new File(FILE_PATH);
        return BinaryFileService.readFile(yourFile.getAbsolutePath());
    }

    public static void update(Account editAccount) {
        boolean isNew = true;
        List<Account> accounts = new ArrayList<>();
        for (Account account: list()) {
            if(account.getAccountNumber().equals(editAccount.getAccountNumber())){
                accounts.add(editAccount);
                isNew = false;
            } else {
                accounts.add(account);
            }
        }
        if(isNew) {
            accounts.add(editAccount);
        }
            save(accounts);
    }
}
