package Asm2;

import java.util.Scanner;
//this class contain all function could be usable in other project future and not strictly relative to current project
public class MiscFunction {
    public static String randomNumberString(int length) {
        double code1 = Math.random() * Math.pow(10,length);
        int code = (int) code1;
        String string = code+"";
        if (string.length()<length) {
            int n = length - string.length();
            for (int i=0;i<n;i++) {
                string = "0" + string;
            }
        }
        return string;
    }
    public static int integerInput(Scanner sc, String inputMessage) {

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

    public static double doubleInput(Scanner sc, String inputMessage) {
        double number;
        System.out.print(inputMessage);
        while (!sc.hasNextDouble()) {
            System.out.println("hay nhap so!!");
            System.out.print(inputMessage);
            sc.next();
        }
        number = sc.nextDouble();
        return number;
    }
}
