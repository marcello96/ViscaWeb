package pl.edu.agh.visca.controller;

import com.sun.javaws.exceptions.InvalidArgumentException;
import jssc.SerialPortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ViscaAdviceController {

    @ExceptionHandler(value = {InvalidArgumentException.class, IOException.class, SerialPortException.class})
    public ResponseEntity<String> handleInvalidArgument(Exception e) {
        return ResponseEntity.badRequest().body("Invalid command:" + e.getMessage());
    }

}
