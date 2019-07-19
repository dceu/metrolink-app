package metrolink.dao;

import metrolink.AppOutput;
import java.util.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;



import metrolink.entity.Stop;

public class SqliteJDBCDao implements MetrolinkDao {
    private static SqliteJDBCDao instance = new SqliteJDBCDao();
    private SqliteJDBCDao(){};
    public static SqliteJDBCDao getInstance(){
        return instance;
    }

    private static DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss").withResolverStyle(ResolverStyle.LENIENT);

    public static final String JDBC_SQLITE_METROLINK_DB = 
        "jdbc:sqlite:C:/Users/Donovan/Documents/GitHub/metrolink-app/metrolink-app/src/main/resources/metrolink.db";
    public static final String ORG_SQLITE_JDBC = "org.sqlite.JDBC";

    private static AppOutput appOutput = AppOutput.getInstance();

    
    public List<Stop> getStopsAllStops() {
        appOutput.print("Fetching all stops...");
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM stops");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<>();
            while (resultSet.next()){
                Stop stop = new Stop();
                stop.setName(resultSet.getString("stop_name"));
                stops.add(stop);
            }
            return stops;
        } catch (SQLException e){
            throw new RuntimeException("Error retrieving stops",e);
        }
    }


    public List<Stop> getStopsMetroLinkStops() {
        appOutput.print("Fetching metrolink stations...");
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT "+
                "* FROM [metrolink_stops] WHERE stop_name LIKE \"%METROLINK STATION%\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Stop> stops = new ArrayList<>();
            while (resultSet.next()){
                Stop stop = new Stop();
                stop.setName(resultSet.getString("stop_name"));
                stops.add(stop);
            }
            return stops;
        } catch (SQLException e){
            throw new RuntimeException("Error retrieving stops", e);
        }        
    }

    private static Connection getConnection() throws SQLException {
        try {
            Class.forName(ORG_SQLITE_JDBC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find the JDBC driver class", e);
        }
        return DriverManager.getConnection(JDBC_SQLITE_METROLINK_DB);
    }

    public static Map<LocalDateTime, String> getArrivalTimes(Stop s) {
        appOutput.print("Finding arrival times for...");
        System.out.print(s.getName());
        try (Connection connection = getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT "+
                "arrival_time, trip_headsign FROM [metrolink_stops] WHERE stop_name LIKE \"%"+ s.getName() +"%\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            Map<LocalDateTime, String> arrivalTimes = new TreeMap<LocalDateTime, String>();
            while (resultSet.next()){
                 //TODO: move logic to another class
                LocalDateTime time;
                String headSign;
                String arrivalString = resultSet.getString("arrival_time");
                String[] arrivalSplit = arrivalString.split(":");
                int hr = Integer.parseInt(arrivalSplit[0]);
                if (hr == 24) hr = 0;
                int min = Integer.parseInt(arrivalSplit[1]);
                int sec = Integer.parseInt(arrivalSplit[2]);
                time = LocalDate.now().atTime(hr, min, sec);
                headSign = resultSet.getString("trip_headsign");
                //System.out.println("adding arrivalTime " + time.toString() );
                //time = LocalDate.now().atTime(LocalDateTime.parse(resultSet.getString("arrival_time"), format));


                arrivalTimes.put(time, headSign);
            }
            
            // System.out.println("Sorted arrival times: " + arrivalTimes);
            return arrivalTimes;
        } catch (SQLException e){
            throw new RuntimeException("Error retrieving stops", e);
        }

    }
}