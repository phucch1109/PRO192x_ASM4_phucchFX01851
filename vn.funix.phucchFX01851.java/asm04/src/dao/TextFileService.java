package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
//WARNING ....... WIP
public class TextFileService {
    private static final String COMMA_DELIMITER = ",";
    public static List<List<String>> readFile(String filePath) {
        List<List<String>> listOfItems = new ArrayList<>();
        //reading file
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(filePath));
            //importing customer using comma as delimiter
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                List<String> listOfDetails = new ArrayList<>();
                System.out.println("new");
                String s = sc.next();
                listOfDetails.add(s);
                sc.skip(sc.delimiter());
                listOfItems.add(listOfDetails);
            }
            //catching ex
        }catch (FileNotFoundException e) {
            System.out.println("Tệp không tồn tại");
            System.out.println(e);
        }catch (InputMismatchException e) {
            System.out.println(e);
        }catch (NoSuchElementException e) {
            System.out.println(e);
        }
        //closing
        finally {
            if (sc != null) {
                sc.close();
            }
        }
    return listOfItems;
    }
}
