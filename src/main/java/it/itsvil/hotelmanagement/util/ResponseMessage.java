package it.itsvil.hotelmanagement.util;

import org.springframework.http.HttpStatusCode;

public record ResponseMessage(HttpStatusCode statusCode, String errorMessage) {

}
