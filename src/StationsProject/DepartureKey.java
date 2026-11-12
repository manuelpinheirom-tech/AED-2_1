package StationsProject;

import java.io.Serializable;

public record DepartureKey(String departure, int index) implements Comparable<DepartureKey>, Serializable {

    @Override
    public int compareTo(DepartureKey other) {
        int byDeparture = this.departure.compareTo(other.departure);
        if (byDeparture != 0) {
            return byDeparture;
        }
        return Integer.compare(index, other.index);
    }

}
