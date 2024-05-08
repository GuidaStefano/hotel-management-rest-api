package it.itsvil.hotelmanagement.controller.exception;

import it.itsvil.hotelmanagement.wrapper.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({RuntimeException.class})
    public ResponseEntity<ResponseMessage> handleRuntimeException(RuntimeException exception) {
        ResponseMessage wrapper = new ResponseMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(wrapper.statusCode()).body(wrapper);
    }

}
