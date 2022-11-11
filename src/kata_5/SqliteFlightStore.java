package kata_5;

import java.io.File;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Iterator;

public class SqliteFlightStore implements FlightStore {
    private final File file;
    private final Connection connection;
    
    public SqliteFlightStore(File file) throws SQLException {
        this.file = file;
        this.connection = DriverManager.getConnection("jdbc:sqlite:" + this.file.getAbsolutePath());
    }

    @Override
    public Iterable<Flight> flights() {
        ResultSet rs = getResultSet("SELECT * FROM flights");
        return () -> new Iterator<Flight>() {
            Flight flight = nextFlight(rs);
            
            @Override
            public boolean hasNext() {
                return flight != null;
            }
            
            @Override
            public Flight next() {
                Flight result = flight;
                flight = nextFlight(rs);
                return result;
            }
        };
    }
    
    private ResultSet getResultSet(String query) {
        try {
            return connection.createStatement().executeQuery(query);
        } catch(SQLException e) {
            return null;
        }
    }
    
    private Flight nextFlight(ResultSet rs) {
        try {
            rs.next();
            return new Flight(
                    DayOfWeek.of(rs.getInt("DAY_OF_WEEK")),
                    LocalTime.of(rs.getInt("DEP_TIME")/100%24, rs.getInt("DEP_TIME")%100),
                    rs.getInt("DEP_DELAY"),
                    LocalTime.of(rs.getInt("ARR_TIME")/100%24, rs.getInt("ARR_TIME")%100),
                    rs.getInt("ARR_DELAY"),
                    rs.getInt("CANCELLED") == 1,
                    rs.getInt("DIVERTED") == 1,
                    rs.getInt("AIR_TIME"),
                    rs.getInt("DISTANCE")
                    );
        } catch(SQLException e) {
            return null;
        }
    }
}
