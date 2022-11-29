package dao;

import Asm2.Customer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomerDao {
private final static String FILE_PATH = ".\\vn.funix.phucchFX01851.java\\store\\customers.dat";
public static void save(List<Customer> customers) throws IOException {
    File file = new File(FILE_PATH);
    file.createNewFile();
    System.out.println("save in: " + file.getAbsolutePath());
    BinaryFileService.writeFile(file.getAbsolutePath(),customers);
}
public static List<Customer> list() {
    File yourFile = new File(FILE_PATH);
    return BinaryFileService.readFile(yourFile.getAbsolutePath());
}

}
