package StationsProject;

import dataStructures.SepChainHashTable;

public class AppClass {
    private final SepChainHashTable<String, Station> stations;
    private final SepChainHashTable<String, Journey> journeys;
    private final long nextOrder;

    public AppClass(long nextOrder) {
        this.nextOrder = nextOrder;
        stations = new SepChainHashTable<>();
        journeys = new SepChainHashTable<>();
    }
}

//ainda falta de classes:
//Stop, departurekey, departure, time, avltree nas datastructures,
