package metrolink.dao;

import metrolink.AppOutput;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import metrolink.entity.Stop;

public class SqliteJDBCDao implements MetrolinkDao {
    private static SqliteJDBCDao instance = new SqliteJDBCDao();
    private SqliteJDBCDao(){};
    public static SqliteJDBCDao getInstance(){
        return instance;
    };

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
}