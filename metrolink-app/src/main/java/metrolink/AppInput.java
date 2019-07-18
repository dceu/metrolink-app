package metrolink;

import java.util.*;

public class AppInput{


    public static String inputStation(){
        Scanner reader = new Scanner(System.in);
        String s = reader.nextLine();
        reader.close();
        return s.toUpperCase();
    }
    
}