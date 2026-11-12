package StationsProject;

public interface JourneyInfo {

    String getId();

    String getOriginName();

    String getDestinationName();

    String getDepartureTime(String station);

    String getDepartureTime(Stop stop);

    String getArrivalTime(String station);

    String getArrivalTime(Stop stop);

    int getStopsNumber();

    int getStopsBetween(Stop origin, Stop destination);

    int getStopsBetween(String origin, String destination);

}
