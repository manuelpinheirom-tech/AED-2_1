package StationsProject;

/**
 * Enum representing the various output messages of the application.
 * Space Complexity: O(1) auxiliary space, as it stores a fixed number
 * of predefined string constants that do not scale with input size.
 */
public enum Message {
    NO_LINE("No line definition.\n"),
    RESTORED("Transportation network restored successfully.\n"),
    NOT_FOUND("Transportation network not found.\n"),
    LOADING("Loading line definition. %d stations loaded.\n"),
    QUIT("Transportation network saved.\nGoodbye.\n"),

    HELP("addJourney: Inserts a new journey into the transportation network.\n"
            + "findJourney: Finds the earliest journey that allows a passenger to travel from one station to another.\n"
            + "findJourneyMinStops: Finds the direct journey with the minimum number of stops.\n"
            + "removeJourney: Removes the given journey from the transportation network.\n"
            + "help: Displays the list of available commands.\n"
            + "quit: Saves the transportation network and terminates the application.\n"),

    ADDED("Journey successfully added.\n"),
    ALREADY_EXISTS("Journey already exists.\n"),
    REMOVED("Journey successfully removed.\n"),
    INVALID_JOURNEY("Invalid journey.\n"),

    INVALID_STATION("Invalid station.\n"),
    INVALID_TIME("Invalid time.\n"),
    INVALID_INTERVAL("Invalid interval time.\n"),
    WRONG_ORDER("Stations are not in the correct order.\n"),

    NO_DIRECT_JOURNEY("No direct journey is available.\n"),
    JOURNEY("Journey: %s\nDeparture station: %s\nDeparture time: %s\n"
            + "Arrival station: %s\nArrival time: %s\n"),
    JOURNEY_STOPS("Journey: %s\nDeparture station: %s\nDeparture time: %s\n"
            + "Arrival station: %s\nArrival time: %s\nStops: %d\n"),

    MIN_TRANSFERS("Minimum number of transfers: %d\n"),
    UNREACHABLE("Destination is unreachable.\n");

    private final String message;

    /**
     * Constructor for the CityProject.Message enum.
     * Time Complexity:
     * - Best and Worst case: O(1).
     * @param message the string representation of the message.
     */
    Message(String message) {
        this.message = message;
    }

    /**
     * Returns the string representation of the message.
     * Time Complexity:
     * - Best and Worst case: O(1).
     * @return the message text.
     */
    @Override
    public String toString() {
        return message;
    }
}