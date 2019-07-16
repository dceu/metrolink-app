package metrolink.entity;

import java.util.List;
import java.time.*;

public class Stop{
    private String name;
    private List<LocalDateTime> arrivalTimes;

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

    

    public List<LocalDateTime> getArrivalTimes(){
        return this.arrivalTimes;
    }

    public void setArrivalTimes(List<LocalDateTime> t){
        this.arrivalTimes = t;
    }

    @Override
    public String toString(){
        return name;
    }

    
    
    
}