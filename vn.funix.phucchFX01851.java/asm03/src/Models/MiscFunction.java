package Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
//this class contain all function could be usable in other project future and not strictly relative to current project
public class MiscFunction {

    public static String getDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }
}
