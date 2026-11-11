package StationsProject;

public interface Journey {

    int NO_TIME = -1;

    long order();

    int size();

    StopClass stopAt(int index);

    int indexOf(Station station, int from);

    void addFirstStop(String station, String departure);

    void addStop(String station, String arrival, String departure);

    void addLastStop(String station, String arrival);
}
