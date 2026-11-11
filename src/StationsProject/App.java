package StationsProject;

import dataStructures.exceptions.ElementAlreadyExistsException;
import dataStructures.exceptions.NoSuchElementException;

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




}