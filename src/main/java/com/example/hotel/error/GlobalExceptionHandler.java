package com.example.hotel.error;

import com.example.hotel.dto.response.APIResponse;
import com.example.hotel.exception.NotFoundException;
import com.example.hotel.exception.RoomNotAvailableException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<APIResponse<String>> argumentNotValid(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException){
        APIResponse<String> response = new APIResponse<>();
        response.setStatus("failure");
        response.setMessage("Date Invalid.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIResponse<String>> notFound(NotFoundException e){
        APIResponse<String> response = new APIResponse<>();
        response.setStatus("failure");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoomNotAvailableException.class)
    public ResponseEntity<APIResponse<String>> notFound(RoomNotAvailableException e){
        APIResponse<String> response = new APIResponse<>();
        response.setStatus("failure");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> globalExceptionHandling(Exception e){
        APIResponse<String> response = new APIResponse<>();
        response.setStatus("failure");
        response.setMessage("Internal Server Error");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
