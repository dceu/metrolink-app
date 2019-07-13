package metrolink;

/**
 * Hello world!
 *
 */
public class CommandLineApp 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello, this is a command line application to access generated MetroLink data via SQLite and SQLiteJDBC." );
        System.out.println(
            "This demonstration will be limited to accessing stop_name and arrival_time fields from the metrolink_stop view");
    }
}
