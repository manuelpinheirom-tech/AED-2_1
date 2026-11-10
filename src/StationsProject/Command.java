package StationsProject;

/**
 * Enum representing the available commands in the application.
 * Space Complexity: O(1) auxiliary space, as it contains a strictly fixed
 * number of constants and does not scale with the input size.
 */
public enum Command {
    ADD_JOURNEY("addjourney"),
    FIND_JOURNEY("findjourney"),
    FIND_JOURNEY_MIN_STOPS("findjourneyminstops"),
    REMOVE_JOURNEY("removejourney"),
    HELP("help"),
    QUIT("quit"),
    UNKNOWN("");

    private final String text;

    /**
     * Constructor for the CityProject.Command enum.
     * Time Complexity:
     * - Best and Worst case: O(1).
     * @param text the string representation of the command.
     */
    Command(String text) {
        this.text = text;
    }

    /**
     * Parses a string and returns the corresponding CityProject.Command enum.
     * Time Complexity:
     * - Best case: O(1) (if the text is null or matches the first constant).
     * - Worst case: O(n) (the number of commands is fixed, so iterating through values()
     *   and comparing strings takes bounded, constant time).
     * @param input the input string to parse.
     * @return the corresponding CityProject.Command, or UNKNOWN if no match is found.
     */
    public static Command fromString(String input) {
        String normalized = input.trim().toLowerCase();
        for (Command command : values()) {
            if (command != UNKNOWN && command.text.equals(normalized)) {
                return command;
            }
        }
        return UNKNOWN;
    }
}