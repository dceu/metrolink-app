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
        Map<LocalDateTime, String> arrivalTimes = s.getArrivalTimes();
        LocalDateTime current = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        AppOutput.print("The time is: " + current.toString());
        // AppOutput.print("\n searching for arrival time from" + arrivalTimes);
        StringBuffer nextArrival = new StringBuffer();
        LocalDateTime nextTime = current;
        // sort s.arrivalTimes chronologically
        for (LocalDateTime t : arrivalTimes.keySet()) {
            if (current.isAfter(t))  {
                System.out.println("the " + t + " train has passed.");
                continue;}
            else if (current.isEqual(t)){
                AppOutput.print("The train is arriving now!!!");
                continue;
            }
            else {
            nextTime = t;
            nextArrival.append(arrivalTimes.get(t) + " @ " + t.toString());
            AppOutput.print("Next Arriving Train is at: " + nextArrival);
            break;
            }
        }
        return nextTime;
    }
}