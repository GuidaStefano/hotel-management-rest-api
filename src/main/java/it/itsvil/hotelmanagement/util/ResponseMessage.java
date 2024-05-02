package it.itsvil.hotelmanagement.util;

import org.springframework.http.HttpStatus;

public record ResponseMessage(HttpStatus statusCode, String errorMessage) {

}
