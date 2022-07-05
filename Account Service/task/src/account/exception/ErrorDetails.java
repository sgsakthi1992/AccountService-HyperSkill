package account.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public final class ErrorDetails {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
}
