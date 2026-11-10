package StationsProject;

public class StationClass implements Station {
    private final String name;
    private final int position;
    private final SortedMap<DepartureKey, Departure> departures;

    public StationClass(String name, int position) {
        this.name = name;
        this.position = position;
    }
}
