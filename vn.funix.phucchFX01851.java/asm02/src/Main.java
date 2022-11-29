import java.util.*;

public class Main {
    static final String AUTHOR = "FX01851";
    static final String VERSION = "v2.0.0";
    static Bank bank;
    static Scanner sc = new Scanner(System.in);
//
//    public static void main(String[] args) {
//        bank= new Bank();
//        bank.addCustomer(new Customer("Trigger Happy", "111111111111"));
//        bank.addCustomer(new Customer("Rabbit Fire", "222222222222"));
//        bank.addCustomer(new Customer("Happy Maker", "333333333333"));
//        bank.addAccount("111111111111", "123456", 90000);
//        bank.addAccount("111111111111", "154875", 20000000);
//        bank.addAccount("222222222222", "155478", 100000);
//        int input = -1;
//        boolean firstInput = true;
//        //Vong lap menu
//        while (input != 0) {
//            if (firstInput) displayMenu();
//            input = MiscFunction.integerInput(sc, "Nhap menu:");
//            firstInput = false;
//            switch (input) {
//                case 1:
//                    addCustomerFunc();
//                    firstInput = true;
//                    break;
//                case 2:
//                    addAccountFunc();
//                    firstInput = true;
//                    break;
//                case 3:
//                    showAllUserAccount();
//                    firstInput = true;
//                    break;
//                case 4:
//                    firstInput = true;
//                    findUserByID();
//                    break;
//                case 5:
//                    firstInput = true;
//                    findUserByName();
//                    break;
//                case 0:
//                    System.out.println("Ket thuc chuong trinh");
//                    break;
//                default:
//                    System.out.println("Nhap sai gia tri, hay nhap lai");
//                    break;
//            }
//
//
//        }
//
//    }


    //function ask user for id number input and return string value
    //this function wont end until user input correct or return null if user input 'NO'
    public static String selfEnterUserIDnumber() {
        //Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.print("Vui long nhap so CCCD:");
            input = sc.next();
            if (!input.matches("[0-9]+")) System.out.println("hay nhap so!! (hoac nhap NO de thoat)");
            else if (input.length() != 12) System.out.println("hay nhap du 12 so (hoac nhap NO de thoat)");
            if (input.equalsIgnoreCase("no")) return null;
        } while (!input.matches("[0-9]+") || input.length() != 12);
        return input;
    }

    //function ask user for number input and return number-only string
    //this function wont end until user input correct or return null if user input 'NO'
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
        System.out.println("| NGAN HANG SO |   " + AUTHOR + "@" + VERSION + "  |");
        System.out.println("+----------+-----------+-----------+");
        System.out.println("| 1. Them khach hang               |");
        System.out.println("| 2. Them tai khoan cho khach hang |");
        System.out.println("| 3. Hien thi danh sach khach hang |");
        System.out.println("| 4. Tim theo CCCD                 |");
        System.out.println("| 5. Tim theo ten khach hang       |");
        System.out.println("| 0. Thoat                         |");
        System.out.println("+----------+-----------+-----------+");
    }

    //first function

    public static void addCustomerFunc() {
        String name = "";
        String userID;
        Customer customer;
        //nhap va kiem tra ten khach hang
        do {
            if (name.matches(".*[0-9].*")) System.out.println("ten khach hang khong duoc chua ky tu so");
            System.out.println("Nhap ten khach hang: ");
            name = sc.next();
        } while (name.matches(".*[0-9].*"));
        //nhap va kiem tra so CMT
        do {
            userID = selfEnterUserIDnumber();
            if (userID == null) return;
            customer = bank.getCustomerById(userID);
            if (customer != null) System.out.println("so CMT nay` da ton tai trong database");
        } while (customer != null);
        bank.addCustomer(new Customer(name, userID));
        System.out.println("Khach hang moi (" + name + ") voi CMT " + userID + " da duoc them vao he thong");
    }
    //second function
    public static void addAccountFunc() {
        String userID;
        Customer customer;
        String accountNumber;
        //nhap va kiem tra so CMT
        do {
            userID = selfEnterUserIDnumber();
            if (userID == null) return;
            customer = bank.getCustomerById(userID);
            if (customer == null) System.out.println("so CMT nay` ko ton tai trong database");
        } while (customer == null);
        //nhap va kiem tra ma STK gom 6 chu so
        boolean existed;
        do {
            accountNumber = selfEnterUserIDnumber(6);
            if (accountNumber == null) return;
            existed = bank.isAccountNumberExisted(accountNumber);
            if (existed) System.out.println("ma STK nay` da ton tai trong database");
        } while (existed);
        //nhap va kiem tra so du
        double balance;
        do {
            balance = MiscFunction.doubleInput(sc, "Nhap so tien ban dau: ");
            if (balance < 50000) System.out.println("so du khong duoc nho hon 50.000 VND");
        } while (balance < 50000);
        bank.addAccount(userID, accountNumber, balance);
    }
    //third function
    public static void showAllUserAccount() {
        if (bank.getCustomers().size()==0) System.out.println("This bank has no customer!!");
        else
        for (Customer customer : bank.getCustomers()) {
            customer.displayInformation();
        }

        System.out.println("bam enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }

    //Forth function
    public static void findUserByID() {
        String userID;
        Customer customer;
        //nhap va kiem tra so CMT
        do {
            userID = selfEnterUserIDnumber();
            if (userID == null) return;
            customer = bank.getCustomerById(userID);
            if (customer == null) System.out.println("so CMT nay` ko ton tai trong database");
        } while (customer == null);
        customer.displayInformation();
        System.out.println("bam enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }

    public static void findUserByName() {
        String name = "";
        
         //nhap va kiem tra ten khach hang
        do {
            if (name.matches(".*[0-9].*")) System.out.println("ten khach hang khong duoc chua ky tu so");
            System.out.println("Nhap ten khach hang: ");
            name = sc.next();
        } while (name.matches(".*[0-9].*"));
        ArrayList<Customer> result = bank.getCustomerByName(name);
        if (result.size()==0) System.out.println("Khong tim thay ket qua");
        else for (Customer customer:result) {
            customer.displayInformation();
        }
        System.out.println("bam enter de tiep tuc");
        sc.nextLine();
        sc.nextLine();
    }




}