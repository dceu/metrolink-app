package metrolink;

import java.time.*;
import java.time.temporal.ChronoUnit;
//import java.time.format.*;
//import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import metrolink.entity.*;
import metrolink.dao.*;

public class ArrivalCalc{

    // public static String getCurrentTime(){
    //         Calendar cal = Calendar.getInstance();
    //         Date date = cal.getTime();
    //         DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //         String formattedDate = dateFormat.format(date);
    //         return formattedDate;
    // }

    public static String countdown(Stop s){
        LocalDateTime a = getArrivalTime(s);
        LocalDateTime now = LocalDateTime.now();
        Long count = ChronoUnit.MINUTES.between(now,a);

        // compare difference between currentTime and arrivalTime
        // return difference
        return "The next train arrives in " + Long.toString(count) + " minutes!";
    }

    public static LocalDateTime getArrivalTime(Stop s){
        s.setArrivalTimes(SqliteJDBCDao.getArrivalTimes(s));
        List<LocalDateTime> arrivalTimes = s.getArrivalTimes();
        LocalDateTime current = LocalDateTime.now();
        String nextArrival = "";
        for (LocalDateTime t : arrivalTimes) {
            if (current.compareTo(t) < 0) continue; 
            nextArrival = t.toString();
            AppOutput.print(nextArrival);
        }
        return LocalDateTime.parse(nextArrival);
    }
}