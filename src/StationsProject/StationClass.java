package StationsProject;

import dataStructures.*;

import java.io.Serial;
import java.io.Serializable;


public class StationClass implements Station, Serializable {
    private final String name;
    private final int position;
    private final BSTreeFrom<DepartureKey, Stop> departures;

    @Serial
    private static final long serialVersionUID = 1L;

    public StationClass(String name, int position) {
        this.name = name;
        this.position = position;
        this.departures = new BSTreeFrom<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public void addDeparture(Stop stop) {
        DepartureKey key = new DepartureKey(stop.departure(), stop.journey().getPosition());
        departures.put(key, stop);

    }

    @Override
    public void removeDeparture(Stop stop) {
        DepartureKey key = new DepartureKey(stop.departure(),stop.journey().getPosition());
        departures.remove(key);

    }

    @Override
    public Iterator<Map.Entry<DepartureKey, Stop>> departuresFrom(String time) {
        return departures.iteratorFrom(new DepartureKey(time, Integer.MIN_VALUE));
    }

}
