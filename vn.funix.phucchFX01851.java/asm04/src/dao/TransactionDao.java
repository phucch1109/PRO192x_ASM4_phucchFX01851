package dao;

import Models.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = ".\\vn.funix.phucchFX01851.java\\store\\transactions.dat";
    public static void save(List<Transaction> customers) {
        File file = new File(FILE_PATH);
        System.out.println("Transaction data saved in: " + file.getAbsolutePath());
        BinaryFileService.writeFile(FILE_PATH,customers);
    }
    public static List<Transaction> list() {
        File yourFile = new File(FILE_PATH);
        return BinaryFileService.readFile(yourFile.getAbsolutePath());
    }
}
