package kata_5;

import java.io.File;
import java.sql.SQLException;


public class Kata_5 {

    public static void main(String[] args) throws SQLException {
        
        FlightStore store = new SqliteFlightStore(new File("flights.db"));
        Histogram<String> histogram = new Histogram();
        for (Flight flight : store.flights()) {
            histogram.increment(flight.getDayOfWeek());
        }
        HistogramDisplay histogramDisplay = new HistogramDisplay("Histogram", histogram);
        histogramDisplay.execute();
    }
}

