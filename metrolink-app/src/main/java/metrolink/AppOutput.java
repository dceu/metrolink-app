package metrolink;

import metrolink.entity.Stop;
import java.time.*;
import java.time.temporal.ChronoUnit;

public class AppOutput{

    private final static String FINDING = "Acessing arrival times for...\n";
    private final static String NOW = "\nThe time is: ";
    private final static String TRAINNOW = "The train is arriving now!!!";
    private final static String NEXTIS = "The next arriving train is: \n";
    private final static String PROMPT = "What station are you currently at?";
    private final static String GREET =
         "Hello, this is a command line application to access generated MetroLink data via SQLite and SQLiteJDBC.";
    private final static String ABOUT =
    "This demonstration will be limited to accessing stop_name and arrival_time fields from the metrolink_stop view.";
         private LocalTime current = 
        LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).toLocalTime();


    AppOutput(){};

    private static AppOutput instance = new AppOutput();

    public static AppOutput getInstance() {
        return instance;
    }

    

    public void print(String s){
        System.out.println("\n" + s);
    }
    
    public void printStop(Stop s){
        instance.print(s.toString());
    }

    public void prompt(){
        instance.print(PROMPT);
    }

    public void greetings(){
        instance.print(GREET);
    }

    public void about(){
        instance.print(ABOUT);
    }

    public void countDown(String s){ //redundant?
        instance.print(s);
    }

    public void findingArrival(Stop s){
        instance.print(FINDING + s.getName() + " METROLINK STATION");
    }

    public void nowIs(){ 
        instance.print( NOW + current.toString());
    }

    public void trainNow(){
        instance.print(TRAINNOW);
    }

    public void nextTrainIs(String s){
        instance.print(NEXTIS + s);
    }


}


