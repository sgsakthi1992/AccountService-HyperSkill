package account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotValidOperationException extends RuntimeException {
    public NotValidOperationException(String message) {
        super(message);
    }
}
