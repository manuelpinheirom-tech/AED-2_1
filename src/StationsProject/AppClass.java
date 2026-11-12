package StationsProject;

import dataStructures.Iterator;
import dataStructures.Map;
import dataStructures.SepChainHashTable;
import dataStructures.exceptions.*;

import java.io.*;

public class AppClass implements App, Serializable {
    private final Map<String, Station> stations;
    private final Map<String, Journey> journeys;
    private int journeyIndex;

    @Serial
    private static final long serialVersionUID = 1L;

    private static final int NO_JOURNEYS = -1;
    private static final int MIN_HOUR = 0;
    private static final int MIN_MINUTE = 0;
    private static final int MAX_HOUR = 23;
    private static final int MAX_MINUTE = 59;

    public AppClass() {
        this.journeyIndex = NO_JOURNEYS;
        stations = new SepChainHashTable<>();
        journeys = new SepChainHashTable<>();
    }

    public void saveApp(App app, String SERIAL_FILE) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIAL_FILE))) {
            oos.writeObject(app);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void addStation(String station) {
        stations.put(station.toLowerCase(), new StationClass(station, stations.size()));
    }

    public static App restoreApp(String SERIAL_FILE) {
        File file = new File(SERIAL_FILE);
        if (!file.exists()) {
            throw new NoSuchElementException();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (AppClass) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void addJourney(String id, int stationNumber) throws ElementAlreadyExistsException {
        if(journeys.get(id.toLowerCase()) != null) //ver se isto n dá merda
            throw new ElementAlreadyExistsException();
        journeyIndex ++;
        Journey journey = new JourneyClass(id, stationNumber, journeyIndex);
        journeys.put(id.toLowerCase(), journey);
    }

    @Override
    public void addStop(String id, String station, String arrival, String departure) {
        Journey journey = journeys.get(id.toLowerCase());
        Station s = stations.get(station.toLowerCase());
        journey.addStop(s, arrival, departure);
    }

    @Override
    public void addFirstStop(String id, String station, String departure) {
        Journey journey = journeys.get(id.toLowerCase());
        Station s = stations.get(station.toLowerCase());
        journey.addFirstStop(s, departure);
    }

    @Override
    public void addLastStop(String id, String station, String arrival) {
        Journey journey = journeys.get(id.toLowerCase());
        Station s = stations.get(station.toLowerCase());
        journey.addLastStop(s, arrival);
        updateDepartures(journey);
    }

    private static void updateDepartures(Journey journey) {
        for(int i = 0; i < journey.getStopsNumber() -1; i++) {
            Stop stop = journey.stopAtIdx(i);
            stop.station().addDeparture(stop);
        }
    }

    @Override
    public void removeJourney(String id) throws NoSuchElementException {
        if (journeyIndex ==  NO_JOURNEYS)
            throw new NoSuchElementException();
        Journey journey = journeys.remove(id.toLowerCase());
        if(journey == null)
            throw new NoSuchElementException();
        for(int i = 0; i < journey.getStopsNumber() -1 ; i++) {
            Stop stop = journey.stopAtIdx(i);
            stop.station().removeDeparture(stop);
        }
    }

    @Override
    public JourneyInfo findJourney(String origin, String destination, String time) throws InvalidStationException, InvalidTimeException, StationsOrderException, NoSuchElementException {
        Station originStation = stations.get(origin.toLowerCase());
        Station destinationStation = stations.get(destination.toLowerCase());
        if (originStation == null || destinationStation == null)
            throw new InvalidStationException();
        if (invalidTime(time))
            throw new InvalidTimeException();
        if (originStation.getPosition() >= destinationStation.getPosition())
            throw new StationsOrderException();
        if (journeys.isEmpty())
            return null;
        else {
            return getJourneyEarly(originStation, destinationStation, time);
        }
    }

    private Journey getJourneyEarly(Station originStation, Station destinationStation, String time) {
        Iterator<Map.Entry<DepartureKey, Stop>> it = originStation.departuresFrom(time);
        while (it.hasNext()) {
            Stop s = it.next().value();
            Stop arrival = s.journey().stopsAfter(destinationStation, s.position());
            if (arrival != null) {
                return s.journey();
            }
        }
        return null;
    }

    @Override
    public JourneyInfo findJourneyMinStops(String origin, String destination, String minTime, String maxTime) throws InvalidStationException, InvalidTimeException, InvalidIntervalException, StationsOrderException, NoSuchElementException {
        Station originStation = stations.get(origin.toLowerCase());
        Station destinationStation = stations.get(destination.toLowerCase());
        if (originStation == null || destinationStation == null)
            throw new InvalidStationException();
        if (invalidTime(minTime) || invalidTime(maxTime))
            throw new InvalidTimeException();
        if (minTime.compareTo(maxTime) > 0)
            throw new InvalidIntervalException();
        if (originStation.getPosition() >= destinationStation.getPosition())
            throw new StationsOrderException();
        if (journeys.isEmpty())
            return null;
        else {
            return getJourneyWithMinStops(originStation, destinationStation, minTime, maxTime);
        }
    }
    private static JourneyInfo getJourneyWithMinStops(Station originStation, Station destinationStation,String minTime, String maxTime ) {
        Iterator<Map.Entry<DepartureKey, Stop>> it = originStation.departuresFrom(minTime);
        Journey bestJourney = null;
        while (it.hasNext()) {
            Stop s = it.next().value();
            if (s.departure().compareTo(maxTime) > 0)
                return bestJourney;

            Journey candidate = s.journey();
            if (candidate.stopsAfter(destinationStation, s.position()) != null
                    && (bestJourney == null || candidate.compareTo(bestJourney, originStation, destinationStation) < 0))
                bestJourney = candidate;
        }
        return bestJourney;
    }

    private static boolean invalidTime(String time){
        if (time == null || time.length() != 5 || time.charAt(2) != ':')
            return true;
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(3, 5));
            return hour < MIN_HOUR || hour > MAX_HOUR || minute < MIN_MINUTE || minute > MAX_MINUTE;
        }catch (NumberFormatException e){
            return true;
        }
    }
}