package dao;

import Models.Transaction;

import java.io.IOException;
import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "store\\customers.dat";
    public static void save(List<Transaction> customers) throws IOException {
        BinaryFileService.writeFile(FILE_PATH,customers);
    }
    public static List<Transaction> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
