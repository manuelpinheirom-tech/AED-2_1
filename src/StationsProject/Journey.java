package StationsProject;

public interface Journey extends JourneyInfo {

    int getPosition();

    Stop stopAtIdx(int index);

    Stop stopsAfter(Station station, int index);

    Stop findStop(String stop);

    int indexOf(Station station, int from);

    void addFirstStop(Station station, String departure);

    void addStop(Station station, String arrival, String departure);

    void addLastStop(Station station, String arrival);

    int compareTo(Journey other, Station origin, Station destination);
}
