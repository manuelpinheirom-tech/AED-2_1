package StationsProject;

import java.io.Serial;
import java.io.Serializable;

public class JourneyClass implements Journey, Serializable {
    private final String id;
    private final int position;
    private final Stop[] stops; //mudar este array para arrayclass ?
    private final int stopsNumber;

    @Serial
    private static final long serialVersionUID = 1L;

    private int stopIdx;

    public JourneyClass(String id, int stationNumber, int position) {
        this.id = id;
        this.position = position;
        this.stops = new Stop[stationNumber];
        this.stopsNumber = stationNumber;
        this.stopIdx = -1;
    }

    @Override
    public Stop stopsAfter(Station destination, int position) {
        int bot = position + 1;
        int top = this.stopsNumber - 1;
        while (bot <= top) {
            int mid = bot + (top - bot) / 2;
            Stop stop = stops[mid];
            int index = stop.station().getPosition();
            if (index == destination.getPosition()) {
                return stop;
            }
            if (index < destination.getPosition()) {
                bot = mid + 1;
            } else {
                top = mid - 1;
            }
        }
        return null;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getRealName(String name) {
        Stop stop = findStop(name);
        return stop.station().getName();
    }

    @Override
    public String getDepartureTime(String stop) {
        return findStop(stop).departure();
    }

    @Override
    public String getDepartureTime(Stop stop) {
        return stop.departure();
    }

    @Override
    public String getArrivalTime(String stop) {
        return findStop(stop).arrival();
    }

    @Override
    public String getArrivalTime(Stop stop) {
        return stop.arrival();
    }

    @Override
    public Stop findStop(String stop) {
        for(int i = 0; i < stopsNumber; i++) {
            if(stops[i] != null) {
                if (stops[i].station().getName().equalsIgnoreCase(stop)){
                    return stops[i];
                }
            }
        }
        return null;
    }

    @Override
    public int getStopsNumber() {
        return stopsNumber;
    }

    public int indexOf(Station station, int from) {
        int bot = from;
        int top = stops.length - 1;
        int pos = station.getPosition();
        while (bot <= top) {
            int mid = bot + (top - bot) / 2;
            int p = stops[mid].station().getPosition();
            if (p == pos) return mid;
            if (p < pos) bot = mid + 1;
            else top = mid - 1;
        }
        return -1;
    }

    @Override
    public void addFirstStop(Station station, String departure) {
        addStop(station, null, departure);
    }

    @Override
    public void addStop(Station station, String arrival, String departure) {
        stopIdx ++;
        stops[stopIdx] = new Stop(this, station, this.stopIdx, arrival, departure);
    }

    @Override
    public void addLastStop(Station station, String arrival) {
        addStop(station, arrival, null);
    }


    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Stop stopAtIdx(int index) {
        return stops[index];
    }

    @Override
    public int getStopsBetween(Stop origin, Stop destination) {
        return indexOf(destination.station(), 0) - indexOf(origin.station(), 1);
    }

    @Override
    public int getStopsBetween(String origin, String destination) {
        Stop originStop = findStop(origin);
        Stop destinationStop = findStop(destination);
        return getStopsBetween(originStop, destinationStop);
    }

    @Override
    public int compareTo(Journey other, Station origin, Station destination) {
        Stop stop1 = this.findStop(origin.getName());
        Stop d1 = this.findStop(destination.getName());
        Stop stop2 = other.findStop(origin.getName());
        Stop d2 = other.findStop(destination.getName());
        int cmp = Integer.compare(this.getStopsBetween(stop1, d1), other.getStopsBetween(stop2, d2));
        if(cmp != 0)
            return cmp;
        cmp = (this.getArrivalTime(d1)).compareTo(other.getArrivalTime(d2));
        if(cmp != 0)
            return cmp;
        cmp = (this.getDepartureTime(stop1)).compareTo(other.getDepartureTime(stop2));
        if(cmp != 0)
            return cmp;
        return Integer.compare(this.getPosition(), other.getPosition());
    }

}
