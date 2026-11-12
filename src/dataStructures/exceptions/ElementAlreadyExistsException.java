package dataStructures.exceptions;

import java.io.Serial;

public class ElementAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ElementAlreadyExistsException() {
        super();
    }
}
