package StationsProject;

import dataStructures.exceptions.*;

import java.io.Serializable;

public interface App extends Serializable {



    void addJourney(String id, int n) throws ElementAlreadyExistsException;

    void removeJourney(String id) throws NoSuchElementException;

    JourneyInfo findJourney(String origin, String destination, String time)
            throws InvalidStationException, InvalidTimeException,
            StationsOrderException, NoSuchElementException;

    JourneyInfo findJourneyMinStops(String origin, String destination, String minTime, String maxTime)
            throws InvalidStationException, InvalidTimeException, InvalidIntervalException,
            StationsOrderException, NoSuchElementException;

    void saveApp(App app, String SERIAL_FILE);

    void addStop(String id, String station, String arrival, String departure);

    void addFirstStop(String id, String station, String departure);

    void addLastStop(String id, String station, String arrival);

    void addStation(String station);


}