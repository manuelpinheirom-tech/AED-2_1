package StationsProject;

import dataStructures.Iterator;
import dataStructures.Map;

public class StationClass implements Station {
    private final String name;
    private final int position;
    private final SortedMap<DepartureKey, Departure> departures;

    public StationClass(String name, int position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public void addDeparture(Journey journey, int stopIndex) {

    }

    @Override
    public void removeDeparture(Journey journey, int stopIndex) {

    }

    @Override
    public Iterator<Map.Entry<DepartureKey, Departure>> departuresFrom(int time) {
        return null;
    }
}
