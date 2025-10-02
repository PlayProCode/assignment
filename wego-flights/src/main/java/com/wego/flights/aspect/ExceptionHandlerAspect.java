package com.wego.flights.aspect;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.wego.flights.constants.ParkingConstants;
/**
 * Exception Handler Advice
 * LLM see /llm/prompts.md#p3
 * @author sumit kumar
 *
 */
@ControllerAdvice
public class ExceptionHandlerAspect {


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String, String>> handleMissingParams(MissingServletRequestParameterException ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ParkingConstants.ERROR, ParkingConstants.MISSING_PARAM + ex.getParameterName());
        error.put(ParkingConstants.ERROR_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleExcption(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ParkingConstants.ERROR, ParkingConstants.INTERNAL_SERVER_ERROR);
        error.put(ParkingConstants.ERROR_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        Map<String, String> error = new HashMap<>();
        error.put(ParkingConstants.ERROR, ParkingConstants.INVALID_PARAM + ex.getName());
        error.put(ParkingConstants.ERROR_MESSAGE, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
