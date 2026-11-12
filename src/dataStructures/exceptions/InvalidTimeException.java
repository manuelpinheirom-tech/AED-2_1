package dataStructures.exceptions;

import java.io.Serial;

public class InvalidTimeException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public InvalidTimeException() {
        super();
    }
}
