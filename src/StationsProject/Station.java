package StationsProject;
import dataStructures.Iterator;
import dataStructures.Map;
import java.io.Serializable;

public interface Station extends Serializable{
    String getName();

    int getPosition();

    void addDeparture(Journey journey, int stopIndex);

    void removeDeparture(Journey journey, int stopIndex);

    Iterator<Map.Entry<DepartureKey, Departure>> departuresFrom(int time);

}
