import Asm2.Account;
import Asm2.Customer;
import Models.DigitalCustomer;
import Models.MiscFunction;
import Models.DigitalBank;
import Models.Transaction;
import dao.AccountDao;
import dao.CustomerDao;
import dao.TextFileService;
import dao.TransactionDao;
import exception.CustomerIdNotValidException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Asm04 {
    static final String AUTHOR = "FX01851";
    static final String VERSION = "v4.0.0";
    private static DigitalBank digitalBank;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws FileNotFoundException {
        digitalBank = new DigitalBank();
       // initialTestData();
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
                    addNewATMAccount();
                    firstInput = true;
                    break;
                case 4:
                    transferAccount();
                    firstInput = true;
                    break;
                case 5:
                    withdrawAccount();
                    firstInput = true;
                    break;
                case 6:
                    showListTransaction();
                    firstInput = true;
                    break;
                case 0:
                    System.out.println("Ket thuc chuong trinh");
                    break;
                case 666:
                    unseenDepositFunc();
                    firstInput = true;
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
    public static String selfEnterUserIDnumber(int length,String extraDescription) {
        String input;
        do {
            System.out.print("Vui long nhap ma STK "+ extraDescription +" gom " + length + " chu so:");
            input = sc.next();
            if (!input.matches("[0-9]+")) System.out.println("hay nhap so!! (hoac nhap NO de thoat)");
            else if (input.length() != length)
                System.out.println("hay nhap du " + length + " so (hoac nhap NO de thoat)");
            if (input.equalsIgnoreCase("no")) return null;
        } while (!input.matches("[0-9]+") || input.length() != length);
        return input;
    }

    public static String selfEnterUserIDnumber() {
        //Scanner sc = new Scanner(System.in);
        String input;
        do {
            System.out.print("Vui long nhap so CCCD :");
            input = sc.next();
            if (!input.matches("[0-9]+")) System.out.println("hay nhap so!! (hoac nhap NO de thoat)");
            else if (input.length() != 12) System.out.println("hay nhap du 12 so (hoac nhap NO de thoat)");
            if (input.equalsIgnoreCase("no")) return null;
        } while (!input.matches("[0-9]+") || input.length() != 12);
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
        DigitalBank.showCustomers();
    }

    //second function
    public static void importNewCustomers() throws FileNotFoundException, InputMismatchException {
        //ask for filepath
        System.out.println("Nhập đường dẫn đến tệp: ");
        String inputPath = sc.next();
        DigitalBank.addCustomer(inputPath);
    }

    //third function
    public static void addNewATMAccount() {
        //get customerID
        List<Customer> customerList = CustomerDao.list();
        String customerId;
        boolean customerExisted = false;
        do {
            customerId = selfEnterUserIDnumber();
            if (customerId == null) return;
            try{
                customerExisted = DigitalBank.isCustomerIdExisted(customerList, customerId);
            }catch (CustomerIdNotValidException ex){
                System.out.println("Không có khách hàng nào có số CMT trên");
            }
        } while (!customerExisted);
        //get account number from user input
        String accountNumber;
        double amount;
        boolean existed;
        List<Account> accountList = AccountDao.list();
        do {
            accountNumber = selfEnterUserIDnumber(6,"");
            if (accountNumber == null) return;
            existed = DigitalBank.isAccountNumberExisted(accountList, accountNumber);
            if (existed) System.out.println("số TK này đã tồn tại, hãy nhập số khác");
        } while (existed);
        //get amount
        do {
            amount = MiscFunction.doubleInput(sc, "Nhập số dư : ");
            if (amount == 0) return;
            if(amount<50000) System.out.println("Số dư phải lớn hơn 50.000đ");
        } while (amount<50000);
        //Execute
        DigitalBank.addSavingAccount(customerId,accountNumber,amount);
        System.out.println("tạo tài khoản ATM thành công");
    }
    //Forth function
    public static void transferAccount() {
        //Check if is there enough data to work with
        List<Customer> customerList = CustomerDao.list();
        List<Account> accountList = AccountDao.list();
        if (customerList.size()<1 || accountList.size()<2) {
            System.out.println("Không có đủ khách hàng/tài khoản để thực hiện ");
            return;
        }
        //get customerID
        String customerId;
        boolean customerExisted =false;
        do {
            customerId = selfEnterUserIDnumber();
            if (customerId == null) return;
            try{
                customerExisted = DigitalBank.isCustomerIdExisted(customerList, customerId);
            }catch (CustomerIdNotValidException ex){
                System.out.println("Không có khách hàng nào có số CMT trên");
            }
        } while (!customerExisted);
        //display sender's account
        Customer customer = DigitalBank.getCustomerById(customerList,customerId);
        customer.displayInformation();
        //get sender account number from user input
        String accountNumber;
        boolean existed;
        do {
            accountNumber = selfEnterUserIDnumber(6,"của ngưởi gửi");
            if (accountNumber == null) return;
            existed = customer.isAccountExisted(accountNumber);
            if (!existed) System.out.println("khách hàng không có số tài khoản đó");
        } while (!existed);
        //get receiver account number from user input
        String accountNumber2;
        do {
            accountNumber2 = selfEnterUserIDnumber(6,"của người nhận");
            if (accountNumber2 == null) return;
            existed = DigitalBank.isAccountNumberExisted(accountList,accountNumber2);
            if (!existed) System.out.println("khách hàng không có số tài khoản đó");
            if(accountNumber2.equals(accountNumber)){
                existed = false;
                System.out.println("Tài khoản nhận không được trùng với tài khoản gửi");
            }
        } while (!existed);
        //get amount
        Account senderAccount = DigitalBank.getAccountByAccountNumber(accountList,accountNumber);
        double amount;
        do {
            amount = MiscFunction.doubleInput(sc, "Nhập số dư : ");
            if (amount == 0) return;
            if((senderAccount.getBalance()-amount)<50000) System.out.println("Số dư còn lại phải lớn hơn 50.000đ");
        } while ((senderAccount.getBalance()-amount)<50000);
        //confirming transfer
        System.out.println("Nhập Y để xác nhận chuyển " + amount + "đ từ TK "+accountNumber+ " đến TK " +accountNumber2);
        sc.nextLine();
        String confirmInput = sc.nextLine();
        if (!confirmInput.equalsIgnoreCase("y")) return;
        //executing
        Account receiverAccount = DigitalBank.getAccountByAccountNumber(accountList,accountNumber2);
        senderAccount.setBalance(senderAccount.getBalance()-amount);
        AccountDao.update(senderAccount);
        receiverAccount.setBalance(receiverAccount.getBalance()+amount);
        AccountDao.update(receiverAccount);
        List<Transaction> transactionList = TransactionDao.list();
        transactionList.add(new Transaction(accountNumber,-(amount),true,"Transfers"));
        transactionList.add(new Transaction(accountNumber2,amount,true,"Transfers"));
        TransactionDao.save(transactionList);
        System.out.println("Đã chuyển tiền thành công, biên lai giao dịch:");
        DigitalBank.printReceipt(accountNumber,accountNumber2,amount,senderAccount.getBalance());
    }


    //Fifth function
    public static void withdrawAccount() {
        List<Customer> customerList = CustomerDao.list();
        //get customerID
        String customerId;
        boolean customerExisted = false;
        do {
            customerId = selfEnterUserIDnumber();
            if (customerId == null) return;
            try{
                customerExisted = DigitalBank.isCustomerIdExisted(customerList, customerId);
            }catch (CustomerIdNotValidException ex){
                System.out.println("Không có khách hàng nào có số CMT trên");
            }
        } while (!customerExisted);
        //display sender's account
        Customer customer = DigitalBank.getCustomerById(customerList,customerId);
        customer.displayInformation();
        //get account number from user input
        String accountNumber;
        double amount;
        boolean existed;
        do {
            accountNumber = selfEnterUserIDnumber(6,"");
            if (accountNumber == null) return;
            existed = customer.isAccountExisted(accountNumber);
            if (!existed) System.out.println("so TK nay khong ton tai");
        } while (!existed);
        //get amount from user input, loop until withdraw successfully or input 0 to exit
        do {
            amount = MiscFunction.doubleInput(sc, "Nhap so tien rut: ");
            if (amount == 0) return;
        } while (!DigitalBank.withdrawAccount(customer, accountNumber, amount));

    }

    //Sixth function
    public static void showListTransaction() {
        //get customer id
        List<Customer> customerList = CustomerDao.list();
        String customerId;
        boolean customerExisted;
        do {
            customerId = selfEnterUserIDnumber();
            if (customerId == null) return;
            customerExisted = DigitalBank.isCustomerIdExisted(customerList, customerId);
            if(!customerExisted) System.out.println("Không có khách hàng nào có số CMT trên");
        } while (!customerExisted);
        //display customer's account
        Customer customer = DigitalBank.getCustomerById(customerList,customerId);
        customer.displayInformation();
        DigitalBank.showTransactionByCustomer(customer);

    }


    //Text method
    public static void initialTestData() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new DigitalCustomer("Happy Maker", "111111111111"));
        CustomerDao.save(customerList);
        DigitalBank.addSavingAccount("111111111111", "123456", 50000000);
        DigitalBank.addSavingAccount("111111111111", "123476", 50000000);
    }

    public static void unseenDepositFunc() {

    }
}