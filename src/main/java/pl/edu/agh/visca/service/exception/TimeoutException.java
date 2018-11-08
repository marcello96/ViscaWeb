package pl.edu.agh.visca.service.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TimeoutException extends Exception {
    private String message;
    private Throwable cause;

    public TimeoutException(String message) {
        super(message);
    }
}