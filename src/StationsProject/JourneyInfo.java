package StationsProject;

public interface JourneyInfo {

    String getId();

    String getOriginName();

    String getDestinationName();

    String getDepartureTime(String station);

    String getArrivalTime(String station);

    int getStops(String origin, String destination);
}
