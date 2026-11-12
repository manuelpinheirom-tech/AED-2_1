package StationsProject;
import dataStructures.Iterator;
import dataStructures.Map;
import java.io.Serializable;

public interface Station extends Serializable{
    String getName();

    int getPosition();

    void addDeparture(Stop stop);

    void removeDeparture(Stop stop);

    Iterator<Map.Entry<DepartureKey, Stop>> departuresFrom(String time);
}
