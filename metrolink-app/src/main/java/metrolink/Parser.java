package metrolink;

import java.time.*;
public class Parser{
    Parser(){};
    private static Parser instance = new Parser();
    public static Parser getInstance(){
        return instance;
    }

    public LocalDateTime arrivalTimeParse(String s){
        String[] arrivalSplit = s.split(":");
                int hr = Integer.parseInt(arrivalSplit[0]);
                if (hr == 24) hr = 0;
                int min = Integer.parseInt(arrivalSplit[1]);
                int sec = Integer.parseInt(arrivalSplit[2]);
        return LocalDate.now().atTime(hr, min, sec);
    }
}