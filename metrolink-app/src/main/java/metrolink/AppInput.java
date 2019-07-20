package metrolink;

import java.util.*;

public class AppInput{

    private AppInput(){};
    private static AppInput instance = new AppInput();
    public static AppInput getInstance(){
        return instance;
    }



    public String inputStation(){
        Scanner reader = new Scanner(System.in);
        String s = reader.nextLine();
        reader.close();
        return s.toUpperCase();
    }
    
}