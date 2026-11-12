package dataStructures.exceptions;

import java.io.Serial;

public class EmptyMapException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public EmptyMapException() {
        super();
    }
}
