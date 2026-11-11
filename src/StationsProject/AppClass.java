package StationsProject;

import dataStructures.Map;
import dataStructures.SepChainHashTable;
import dataStructures.exceptions.NoSuchElementException;

import java.io.Serializable;

public class AppClass implements App, Serializable {
    private final Map<String, Station> stations;
    private final Map<String, Journey> journeys;
    private final long nextOrder;

    public AppClass(long nextOrder) {
        this.nextOrder = nextOrder;
        stations = new SepChainHashTable<>();
        journeys = new SepChainHashTable<>();
    }


    @Override
    public Journey newJourney(String id, int n) {


    }

    @Override
    public void addJourney(Journey journey) throws ElementAlreadyExistsException {

    }

    @Override
    public void removeJourney(String id) throws NoSuchElementException {

    }

    @Override
    public JourneyInfo findJourney(String origin, String destination, String time) throws InvalidStationException, InvalidTimeException, StationsOrderException, NoSuchElementException {
        return null;
    }

    @Override
    public JourneyInfo findJourneyMinStops(String origin, String destination, String minTime, String maxTime) throws InvalidStationException, InvalidTimeException, InvalidIntervalException, StationsOrderException, NoSuchElementException {
        return null;
    }
}

//ainda falta de classes:
//Stop, departurekey, departure, time, avltree nas datastructures,
