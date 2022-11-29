

import java.util.Scanner;

import Asm2.MiscFunction;
import Models.DigitalBank;
import Models.DigitalCustomer;


public class Main {
    static final String AUTHOR = "FX01851";
    static final String VERSION = "v2.0.0";
    private static final String CUSTOMER_ID = "111111111111";
    private static final String CUSTOMER_NAME = "Happy Maker";
    private static DigitalBank digitalBank;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        digitalBank = new DigitalBank();
        digitalBank.addCustomer(CUSTOMER_ID, CUSTOMER_NAME);
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
                    customerDetailFunc();
                    firstInput = true;
                    break;
                case 2:
                    addATMAccountFunc();
                    firstInput = true;
                    break;
                case 3:
                    addLoanAccountFunc();
                    firstInput = true;
                    break;
                case 4:
                    withdrawFunc();
                    firstInput = true;
                    break;
                case 5:
                    transactionHistory();
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
        System.out.println("| NGAN HANG DIEN TU | " + AUTHOR + "@" + VERSION + "  |");
        System.out.println("+----------+-----------+-----------+");
        System.out.println("| 1. Thong tin khach hang          |");
        System.out.println("| 2. Them tai khoan ATM            |");
        System.out.println("| 3. Them tai khoan tin dung       |");
        System.out.println("| 4. Rut tien                      |");
        System.out.println("| 5. lich su giao dich             |");
        System.out.println("| 0. Thoat                         |");
        System.out.println("+----------+-----------+-----------+");
    }

    //first function
    public static void customerDetailFunc() {
        DigitalCustomer customer = digitalBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            customer.displayInformation();
        }
        System.out.println("bam Enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }

    //second function
    public static void addATMAccountFunc() {
        //nhap va kiem tra ma STK gom 6 chu so
        String accountNumber;
        boolean existed;
        do {
            accountNumber = selfEnterUserIDnumber(6);
            if (accountNumber == null) return;
            existed = digitalBank.isAccountNumberExisted(accountNumber);
            if (existed) System.out.println("ma STK nay` da ton tai trong database");
        } while (existed);
        //nhap va kiem tra so du
        double balance;
        do {
            balance = MiscFunction.doubleInput(sc, "Nhap so tien ban dau: ");
            if (balance < 50000) System.out.println("so du khong duoc nho hon 50.000 VND");
        } while (balance < 50000);
        // execute add saving account method, print result
        if (digitalBank.addSavingAccount(CUSTOMER_ID, accountNumber, balance))
            System.out.println("da them tai khoan " + accountNumber + " thanh cong");
        System.out.println("bam Enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }

    //third function
    public static void addLoanAccountFunc() {
        //nhap va kiem tra ma STK gom 6 chu so
        String accountNumber;
        boolean existed;
        do {
            accountNumber = selfEnterUserIDnumber(6);
            if (accountNumber == null) return;
            existed = digitalBank.isAccountNumberExisted(accountNumber);
            if (existed) System.out.println("ma STK nay` da ton tai trong database");
        } while (existed);
        // execute add loan account method, print result
        if (digitalBank.addLoanAccount(CUSTOMER_ID, accountNumber))
            System.out.println("da them tai khoan " + accountNumber + " thanh cong");
        System.out.println("bam Enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }

    //Forth function
    public static void withdrawFunc() {
        String accountNumber;
        double amount;
        boolean existed;
        //get account number from user input
        do {
            accountNumber = selfEnterUserIDnumber(6);
            if (accountNumber == null) return;
            existed = digitalBank.isAccountNumberExisted(accountNumber, CUSTOMER_ID);
            if (!existed) System.out.println("so TK nay khong ton tai");
        } while (!existed);
        //get amount from user input, loop until withdraw successfully or input 0 to exit
        do {
            amount = MiscFunction.doubleInput(sc, "Nhap so tien rut: ");
            if (amount == 0) return;
        } while (!digitalBank.withdrawAccount(CUSTOMER_ID, accountNumber, amount));
        System.out.println("bam Enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }

    //Fifth function
    public static void transactionHistory() {
        DigitalCustomer customer = digitalBank.getCustomerById(CUSTOMER_ID);
        if (customer != null) {
            System.out.println("+------------------+------------------+----------------+");
            System.out.println("| LICH SU GIAO DICH                                    |");
            System.out.println("+------------------+------------------+----------------+");
            customer.displayInformation();
            customer.showTransaction();
        }
        System.out.println("bam Enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }
}