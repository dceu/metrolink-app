package metrolink;

import java.time.*;
import java.time.temporal.ChronoUnit;
//import java.time.format.*;
//import java.time.format.DateTimeFormatter;
import java.util.*;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
import metrolink.entity.*;
import metrolink.dao.*;

public class ArrivalCalc{
    private ArrivalCalc(){};
    private static ArrivalCalc instance = new ArrivalCalc();
    public static ArrivalCalc getInstance(){
        return instance;
    }

    private AppOutput out = AppOutput.getInstance();

    // public static String getCurrentTime(){
    //         Calendar cal = Calendar.getInstance();
    //         Date date = cal.getTime();
    //         DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //         String formattedDate = dateFormat.format(date);
    //         return formattedDate;
    // }

    public String countdown(Stop s){
        LocalDateTime a = getArrivalTime(s);
        LocalDateTime now = LocalDateTime.now();
        Long min = ChronoUnit.MINUTES.between(now,a);
        int hours = 0;
        StringBuffer eta = new StringBuffer("ETA: ");
        while(min>60){
            hours++;
            min = min - (60 * hours);
        }
        if (hours > 1) eta.append(hours + " hours, ");
        if (hours == 1) eta.append(hours + " hour, ");

        if (min > 1) eta.append(min + " minutes.");
        if (min ==1) eta.append(min + " minute!");
        // compare difference between currentTime and arrivalTime
        // return difference
        return eta.toString();
    }

    public LocalDateTime getArrivalTime(Stop s){
        s.setArrivalTimes(SqliteJDBCDao.getArrivalTimes(s)); // --> controller
        Map<LocalDateTime, String> arrivalTimes = s.getArrivalTimes();
        LocalDateTime current = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        out.nowIs();
        StringBuffer nextArrival = new StringBuffer();
        LocalDateTime nextTime = current;
        for (LocalDateTime t : arrivalTimes.keySet()) {
            if (current.isAfter(t))  {
                // System.out.println("the " + t + " train has passed."); 
                continue;}
            else if (current.isEqual(t)){
                out.trainNow();
                continue;
            }
            else {
            nextTime = t;
            nextArrival.append("["+ arrivalTimes.get(t) + "]" + " @ " 
                + t.toLocalTime().toString());
            out.nextTrainIs(nextArrival.toString());
            break;
            }
        }
        return nextTime;
    }
}