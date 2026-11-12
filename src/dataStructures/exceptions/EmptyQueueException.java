package dataStructures.exceptions;

import java.io.Serial;

public class EmptyQueueException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public EmptyQueueException() {
        super();
    }
}
