import StationsProject.*;
import StationsProject.App;
import StationsProject.AppClass;
import dataStructures.Iterator;

import java.io.*;
import dataStructures.exceptions.*;
import java.util.Scanner;

/**
 * Authors:
 * - Joana Brito, 74082
 * - Manuel Mendes, 74538
 */
public class Main {

    private static final String SERIAL_FILE = "1_.ser";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        App app = loadApp(SERIAL_FILE, args[0]);
        if (app == null) {
            System.out.print(Message.NO_LINE);
        } else {
            commandInterpreter(in, app);
        }
        in.close();
    }

    private static void commandInterpreter(Scanner in, App app) {
        Command command = Command.fromString(in.nextLine());
        while (command != Command.QUIT) {
            switch (command) {
                case HELP -> help();
                case ADD_JOURNEY -> addJourney(in, app);
                case FIND_JOURNEY -> findJourney(in, app);
                case FIND_JOURNEY_MIN_STOPS -> findJourneyMinStops(in, app);
                case REMOVE_JOURNEY -> removeJourney(in, app);
            }
            command = Command.fromString(in.nextLine());
        }
        quit(app);
    }

    private static void help() {
        System.out.print(Message.HELP);
    }


    private static void quit(App app) {
        app.saveApp(app, SERIAL_FILE);
        System.out.print(Message.QUIT);
    }


    private static App loadApp(String SERIAL_FILE, String lineFile) {
        try{
            App app = AppClass.restoreApp(SERIAL_FILE);
            System.out.print(Message.RESTORED);
            return app;
        }catch(NoSuchElementException e){
            return createApp(lineFile);
        }
    }

    private static App createApp(String path) {
        if(path == null) {
            return null;
        }
        try (Scanner file = new Scanner(new File(path))) {
            int n = file.nextInt();
            file.nextLine();
            App app = new AppClass();
            for (int i = 0; i < n; i++) {
                app.addStation(file.nextLine().trim());
            }
            System.out.print(Message.NOT_FOUND);
            System.out.printf(Message.LOADING.toString(), n);
            return app;
        } catch (Exception e) {
            App app = new AppClass();
            System.out.printf(Message.NO_LINE.toString());
            return app;
        }
    }

    private static void addJourney(Scanner in, App app) {
        String id = in.nextLine();
        int n =  in.nextInt();
        in.nextLine();
        try {
        app.addJourney(id, n);
        app.addFirstStop(id, in.nextLine(), in.nextLine().trim());
        for (int i = 1; i < n - 1; i++) {
            String station = in.nextLine();
            String arrival = in.nextLine().trim();
            String departure = in.nextLine().trim();
            app.addStop(id, station, arrival, departure);
        }
        app.addLastStop(id, in.nextLine(), in.nextLine().trim());
        System.out.print(Message.ADDED);
        } catch (ElementAlreadyExistsException e) {
            System.out.print(Message.ALREADY_EXISTS);
            clearBuffer(in, n * 3 -2);
            // meter aqui contantes ig
        }
    }

    private static void findJourney(Scanner in, App app) {
        String origin = in.nextLine();
        String destination = in.nextLine();
        String time = in.nextLine().trim();
        try {
            JourneyInfo journey = app.findJourney(origin, destination, time);
            if(journey == null)
                System.out.print(Message.NO_DIRECT_JOURNEY);
            else {
                System.out.printf(Message.JOURNEY.toString() ,
                        journey.getId(),
                        journey.getOriginName(),
                        journey.getDepartureTime(origin),
                        journey.getDestinationName(),
                        journey.getArrivalTime(destination));
            }
        } catch (InvalidStationException e) {
            System.out.print(Message.INVALID_STATION);
        } catch (InvalidTimeException e) {
            System.out.print(Message.INVALID_TIME);
        } catch (StationsOrderException e) {
            System.out.print(Message.WRONG_ORDER);
        }
    }

    private static void findJourneyMinStops(Scanner in, App app) {
        String origin = in.nextLine();
        String destination = in.nextLine();
        String intervalStart = in.next().trim();
        String intervalEnd = in.next().trim();
        in.nextLine();
        try {
            JourneyInfo journey = app.findJourneyMinStops(origin, destination, intervalStart, intervalEnd);
            if(journey == null)
                System.out.print(Message.NO_DIRECT_JOURNEY);
            else {
                System.out.printf(Message.JOURNEY_STOPS.toString(),
                        journey.getId(),
                        journey.getOriginName(),
                        journey.getDepartureTime(origin),
                        journey.getDestinationName(),
                        journey.getArrivalTime(destination),
                        journey.getStopsBetween(origin, destination));
            }
        } catch (InvalidStationException e) {
            System.out.print(Message.INVALID_STATION);
        } catch (InvalidTimeException e) {
            System.out.print(Message.INVALID_TIME);
        } catch (InvalidIntervalException e) {
            System.out.print(Message.INVALID_INTERVAL);
        } catch (StationsOrderException e) {
            System.out.print(Message.WRONG_ORDER);
        }
    }

    private static void removeJourney(Scanner in, App app) {
        String id = in.nextLine();
        try {
            app.removeJourney(id);
            System.out.print(Message.REMOVED);
        } catch (NoSuchElementException e) {
            System.out.print(Message.INVALID_JOURNEY);
        }
    }

    private static void clearBuffer(Scanner in, int n){
        for(int i = 0; i < n; i++){
            in.nextLine();
        }
    }
}