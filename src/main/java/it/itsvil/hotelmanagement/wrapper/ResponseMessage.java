package it.itsvil.hotelmanagement.wrapper;

import org.springframework.http.HttpStatus;

public record ResponseMessage(HttpStatus statusCode,
                              String errorMessage) {

}
