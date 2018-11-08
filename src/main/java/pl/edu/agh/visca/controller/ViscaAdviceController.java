package pl.edu.agh.visca.controller;

import jssc.SerialPortException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class ViscaAdviceController {

    @ExceptionHandler(value = {IOException.class, SerialPortException.class})
    public ResponseEntity<String> handleInvalidArgument(Exception e) {
        return ResponseEntity.badRequest().body("Invalid command:" + e.getMessage());
    }

}
