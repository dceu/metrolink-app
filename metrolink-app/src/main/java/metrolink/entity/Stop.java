package metrolink.entity;

import java.util.*;
import java.time.*;

public class Stop{
    private String name;
    private Map<LocalDateTime, String> arrivalTimes = new TreeMap<LocalDateTime, String>();

    public Stop() {};
    private Stop stop;


    public Stop getInstance(){
        if (stop == null){
            stop = new Stop();
        }
        return stop;
    }
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    public Map<LocalDateTime, String> getArrivalTimes(){
        return this.arrivalTimes;
    }

    public void setArrivalTimes(Map<LocalDateTime, String> t){
        this.arrivalTimes = t;
    }

    @Override
    public String toString(){
        return name;
    }

    
    
    
}