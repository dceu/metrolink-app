package metrolink;
import metrolink.dao.*;
import metrolink.entity.Stop;
import metrolink.ArrivalCalc;;

/**
 * Hello world!
 *
 */
public class CommandLineApp 
{
    public static void fetchMetro(){
        AppOutput.print(SqliteJDBCDao.getInstance().getStopsMetroLinkStops().toString());
    }

    public static void prompt(){
        AppOutput.prompt();
    }

    public static void countdown(String s){
        Stop n = new Stop(); // move to factory class
        n.setName(s);

        AppOutput.print(ArrivalCalc.countdown(n));
    }



    public static void main( String[] args )
    {
        System.out.println( "Hello, this is a command line application to access generated MetroLink data via SQLite and SQLiteJDBC." );
        System.out.println(
            "This demonstration will be limited to accessing stop_name and arrival_time fields from the metrolink_stop view");
        //fetchMetro();
        prompt();
        countdown(AppInput.inputStation());
        }
}
