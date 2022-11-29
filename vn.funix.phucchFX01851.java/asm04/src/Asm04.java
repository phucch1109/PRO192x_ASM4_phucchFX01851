import Asm2.Account;
import Asm2.Customer;
import Models.DigitalCustomer;
import Models.MiscFunction;
import Models.DigitalBank;
import dao.AccountDao;
import dao.CustomerDao;
import dao.TextFileService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Asm04 {
    static final String AUTHOR = "FX01851";
    static final String VERSION = "v4.0.0";
    private static final String CUSTOMER_ID = "111111111111";
    private static final String CUSTOMER_NAME = "Happy Maker";
    private static DigitalBank digitalBank;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        digitalBank = new DigitalBank();
        digitalBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
        digitalBank.addCustomer("v");
        //pre-create data for testing, plz delete if needed
        digitalBank.addLoanAccount(CUSTOMER_ID, "234567");
        digitalBank.addSavingAccount(CUSTOMER_ID, "123456", 50000000);
//        digitalBank.withdrawAccount(CUSTOMER_ID,"151651",500000);
//        digitalBank.withdrawAccount(CUSTOMER_ID,"895545",500000);

        int input = -1;
        boolean firstInput = true;
        //Vong lap menu
        while (input != 0) {
            if (firstInput) displayMenu();
            input = MiscFunction.integerInput(sc, "Nhap menu:");
            firstInput = false;
            switch (input) {
                case 1:
                    showCustomerList();
                    firstInput = true;
                    break;
                case 2:
                    importNewCustomers();
                    firstInput = true;
                    break;
                case 3:

                    firstInput = true;
                    break;
                case 4:

                    firstInput = true;
                    break;
                case 5:

                    firstInput = true;
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh");
                    break;
                default:
                    System.out.println("Nhap sai gia tri, hay nhap lai");
                    break;
            }


        }

    }

    //function ask user for number input and return number-only string
    //this function won't end until user input correct or return null if user input 'NO'
    //param int length: value used for measure string length
    public static String selfEnterUserIDnumber(int length) {
        String input;
        do {
            System.out.print("Vui long nhap ma STK gom " + length + " chu so:");
            input = sc.next();
            if (!input.matches("[0-9]+")) System.out.println("hay nhap so!! (hoac nhap NO de thoat)");
            else if (input.length() != length)
                System.out.println("hay nhap du " + length + " so (hoac nhap NO de thoat)");
            if (input.equalsIgnoreCase("no")) return null;
        } while (!input.matches("[0-9]+") || input.length() != length);
        return input;
    }

    //display menu function, the name explained itself
    public static void displayMenu() {
        System.out.println("+----------+-----------+-----------+");
        System.out.println("| NGÂN HÀNG ĐIỆN TỬ | " + AUTHOR + "@" + VERSION + "  |");
        System.out.println("+----------+-----------+-----------+");
        System.out.println("| 1. Danh sách khách hàng          |");
        System.out.println("| 2. Nhập danh sách khách hàng     |");
        System.out.println("| 3. Thêm tài khoản ATM            |");
        System.out.println("| 4. Chuyển tiền                   |");
        System.out.println("| 5. Rút tiền                      |");
        System.out.println("| 6. Tra cứu lịch sử giao dịch     |");
        System.out.println("| 0. Thoát                         |");
        System.out.println("+----------+-----------+-----------+");
    }

    //first function
    public static void showCustomerList() {
        digitalBank.setCustomers(CustomerDao.list());
        if(digitalBank.getCustomers().size()==0) {
            System.out.println("Ngân hàng không có khách hàng!");
            return;
        }
        List<Account> accountList = AccountDao.list();
        //add account into customer
        for (Account acc:accountList) {
            for(Customer customer : digitalBank.getCustomers()) {
                if(customer.getCustomerId().equals(acc.getCustomerId())) {
                    customer.addAccount(acc);
                    break;
                }
            }
        }
        //displaying loop
        for (Customer customer: digitalBank.getCustomers()
             ) {
            if (customer instanceof DigitalCustomer)  {
                DigitalCustomer dCuscomer = (DigitalCustomer) customer;
                dCuscomer.displayInformation();
            }else customer.displayInformation();
        }

    }

    //second function
    public static void importNewCustomers() throws FileNotFoundException, InputMismatchException {
        //ask for filepath
        System.out.println("Nhập đường dẫn đến tệp: ");
        String inputPath = sc.next();
        digitalBank.addCustomer(inputPath);

    }
        //third function

        //Forth function

        //Fifth function


}