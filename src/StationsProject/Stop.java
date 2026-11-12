package StationsProject;

import java.io.Serial;
import java.io.Serializable;

final class Stop implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Journey journey;
    private final Station station;
    private final int position;
    private final String arrival;
    private final String departure;

    Stop(Journey journey, Station station, int position, String arrival, String departure) {
        this.journey = journey;
        this.station = station;
        this.position = position;
        this.arrival = arrival;
        this.departure = departure;
    }
    Journey journey()  { return journey; }
    Station station()  { return station; }
    int position()     { return position; }
    String arrival()   { return arrival; }
    String departure() { return departure; }
}