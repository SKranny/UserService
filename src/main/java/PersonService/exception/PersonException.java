package PersonService.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class PersonException extends RuntimeException{
    private final HttpStatus httpStatus;

    public PersonException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public PersonException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public PersonException(Throwable ex, HttpStatus httpStatus) {
        this(ex.getMessage(), httpStatus);
    }
}
