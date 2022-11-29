import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<City> citys = new ArrayList<City>();
    static final String AUTHOR = "FX01851";
    static final String VERSION = "v1.0.0";
//
//   public static void main(String[] args) {
//
//
//        citys.add(new City(1, "Hà Nội", "001"));
//        citys.add(new City(2, "Hà Giang", "002"));
//        citys.add(new City(3, "Cao Bằng", "004"));
//        citys.add(new City(4, "Bắc Kạn", "006"));
//        citys.add(new City(5, "Tuyên Quang", "008"));
//        citys.add(new City(6, "Lao Cai", "010"));
//        citys.add(new City(7, "Điện Biên", "011"));
//        citys.add(new City(8, "Lai Châu", "012"));
//        citys.add(new City(9, "Sơn La", "014"));
//        citys.add(new City(10, "Yên Bái", "015"));
//        citys.add(new City(11, "Hòa Bình", "017"));
//        citys.add(new City(12, "Thái Nguyên", "019"));
//        citys.add(new City(13, "Lạng Sơn", "020"));
//        citys.add(new City(14, "Quảng Ninh", "022"));
//        citys.add(new City(15, "Bắc Giang", "024"));
//        citys.add(new City(16, "Phú Thọ", "025"));
//        citys.add(new City(17, "Vĩnh Phúc", "026"));
//        citys.add(new City(18, "Bắc Ninh", "027"));
//        citys.add(new City(19, "Hải Dương", "030"));
//        citys.add(new City(20, "Hải Phòng", "031"));
//        citys.add(new City(21, "Hưng Yên", "033"));
//        citys.add(new City(22, "Thái Bình", "034"));
//        citys.add(new City(23, "Hà Nam", "035"));
//        citys.add(new City(24, "Nam Định", "036"));
//        citys.add(new City(25, "Ninh Bình", "037"));
//        citys.add(new City(26, "Thanh Hóa", "038"));
//        citys.add(new City(27, "Nghệ An", "040"));
//        citys.add(new City(28, "Hà Tĩnh", "042"));
//        citys.add(new City(29, "Quảng Bình", "044"));
//        citys.add(new City(30, "Quảng Trị", "045"));
//        citys.add(new City(31, "Thừa Thiên Huế", "046"));
//        citys.add(new City(32, "Đà Nẵng", "048"));
//        citys.add(new City(33, "Quảng Nam", "049"));
//        citys.add(new City(34, "Quảng Ngãi", "051"));
//        citys.add(new City(35, "Bình Định", "052"));
//        citys.add(new City(36, "Phú Yên", "054"));
//        citys.add(new City(37, "Khánh Hòa", "056"));
//        citys.add(new City(38, "Ninh Thuận", "058"));
//        citys.add(new City(39, "Bình Thuận", "060"));
//        citys.add(new City(40, "Kom Tum", "062"));
//        citys.add(new City(41, "Gia Lai", "064"));
//        citys.add(new City(42, "Đắk Lắk", "066"));
//        citys.add(new City(43, "Đắk Nông", "067"));
//        citys.add(new City(44, "Lâm Đồng", "068"));
//        citys.add(new City(45, "Bình Phước", "070"));
//        citys.add(new City(46, "Tây Ninh", "072"));
//        citys.add(new City(47, "Bình Dương", "074"));
//        citys.add(new City(48, "Đồng Nai", "075"));
//        citys.add(new City(49, "Bà Rịa - Vũng Tàu", "077"));
//        citys.add(new City(50, "Sài Gòn", "079"));
//        citys.add(new City(51, "Long An", "080"));
//        citys.add(new City(52, "Tiền Giang", "082"));
//        citys.add(new City(53, "Bến Tre", "083"));
//        citys.add(new City(54, "Trà Vinh", "084"));
//        citys.add(new City(55, "Vĩnh Long", "086"));
//        citys.add(new City(56, "Đồng Tháp", "087"));
//        citys.add(new City(57, "An Giang", "089"));
//        citys.add(new City(58, "Kiên Giang", "091"));
//        citys.add(new City(59, "Cần Thơ", "092"));
//        citys.add(new City(60, "Hậu Giang", "093"));
//        citys.add(new City(61, "Sóc Trăng", "094"));
//        citys.add(new City(62, "Bạc Liêu", "095"));
//        citys.add(new City(63, "Cà Mau", "096"));
//
//
//        Scanner sc = new Scanner(System.in);
//        System.out.println("-----------------------------");
//        System.out.println("| NGAN HANG SO | "+AUTHOR+"@"+VERSION+"|");
//        System.out.println("-----------------------------");
//        System.out.println("| 1. Nhap CCCD                 |");
//        System.out.println("| 0. Thoat                     |");
//        int input = -1;
//        //Vong lap menu
//        while (input != 0) {
//            input = integerInput("Nhap menu:");
//            if (input == 1) {
//                botCheck();
//                String inputID = selfEnterUserIDnumber();
//                if (inputID != null) {
//                    checkDetailInfo(inputID);
//                }
//
//            } else if (input == 0) System.out.println("Thoat khoi he thong");
//            else System.out.println("Nhap sai, hay nhap lai");
//
//        }
//
//
//    }

    //method give random number and ask user to input repeat until its correct
    public static void botCheck() {
        double code1 = Math.random() * 900;
        int code = (int) code1 + 100;
        int input = 0;
        boolean firstTime = true;
        System.out.println("Nhap ma xac thuc [" + code + "]");
        while (input != code) {
            if (!firstTime)
                System.out.println("Ma xac thuc khong dung. Vui long thu lai.");
            input = integerInput("ma xac thuc: ");
            firstTime = false;
        }

    }
    //function ask user for id number input and return string value
    //this function wont end until user input correct or return null if user input 'NO'
    public static String selfEnterUserIDnumber() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vui long nhap so CCCD:");
        String input = sc.next();
        while (!input.matches("[0-9]+") || input.length() != 12) {
            if (!input.matches("[0-9]+")) System.out.println("hay nhap so!! (hoac nhap NO de thoat)");
            else if (input.length() != 12) System.out.println("hay nhap du 12 so (hoac nhap NO de thoat)");
            System.out.print("Vui long nhap so CCCD:");
            input = sc.next();
            if (input.equalsIgnoreCase("no")) return null;
        }

        return input;
    }

    //method ask for int input from user and return int
    public static int integerInput(String inputMessage) {
        Scanner sc = new Scanner(System.in);
        int number;
        System.out.print(inputMessage);
        while (!sc.hasNextInt()) {
            System.out.println("hay nhap so!!");
            System.out.print(inputMessage);
            sc.next();
        }
        number = sc.nextInt();
        return number;
    }
//function take id number parameter and wait for user input to start 3 information id checking function
    public static void checkDetailInfo(String input) {
        System.out.println("    |1.kiem tra noi sinh");
        System.out.println("    |2.kiem tra tuoi, gioi tinh");
        System.out.println("    |3.kiem tra so ngau nhien");
        System.out.println("    |0.thoat");
        int infoType = integerInput("Nhap chuc nang: ");
        while (infoType != 0) {
            switch (infoType) {
                case 1: {
                    String x = input.substring(0, 3);
                    boolean resultFound = false;
                    for (City i : citys) {
                        if (i.getCode().equalsIgnoreCase(x)) {
                            System.out.println("Noi sinh: " + i.getName());
                            resultFound = true;
                            break;
                        }
                    }
                    if (!resultFound) System.out.println("Ma tinh ko khop voi database");
                    break;
                }
                case 2: {
                    String x = input.substring(3,4);
                    int x1 = Integer.parseInt(x);
                    if (x1 % 2 == 0) System.out.print("Nam |");
                    else System.out.print("Nu |");
                    String y = input.substring(4, 6);
                    switch (x1) {
                        case 0:
                        case 1:
                            System.out.println(" 19" + y);
                            break;
                        case 2:
                        case 3:
                            System.out.println(" 20" + y);
                            break;
                        case 4:
                        case 5:
                            System.out.println(" 21" + y);
                            break;
                        case 6:
                        case 7:
                            System.out.println(" 22" + y);
                            break;
                        case 8:
                        case 9:
                            System.out.println(" 23" + y);
                            break;
                    }

                    break;
                }
                case 3: {
                    String x = input.substring(6, 12);
                    System.out.println("So ngau nhieu: " + x);
                    break;
                }
                case 0:break;
                default:
                    System.out.println("nhap sai gia tri!");

            }
            infoType = integerInput("Nhap chuc nang: ");

        }


    }
}