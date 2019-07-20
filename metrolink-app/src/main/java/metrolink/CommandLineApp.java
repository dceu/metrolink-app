package metrolink;
import metrolink.dao.*;
import metrolink.entity.Stop;
import metrolink.ArrivalCalc;
import java.lang.StringBuffer;
import java.util.*;

/**
 * Hello world!
 *
 */
public class CommandLineApp 
{
    static AppOutput out = AppOutput.getInstance();
    static ArrivalCalc ac = ArrivalCalc.getInstance();
    static AppInput in = AppInput.getInstance();

    public void fetchMetro(){
        String metroStops = SqliteJDBCDao.getInstance().getStopsMetroLinkStops().toString();
        out.print(metroStops);
    }

    public void prompt(){
        out.prompt();
    }

    public static void countdown(String s){
        Stop n = new Stop(); // move to factory class
        n.setName(s);
        StringBuffer countdownString = new StringBuffer(ac.countdown(n));
        out.countDown(countdownString.toString());
    }

    private static String userInput(){
        String input = in.inputStation();
        return input;
    }



    public static void main( String[] args )
    {
        out.greetings();
        out.about();
        out.prompt();
        countdown(userInput());
        }
}
