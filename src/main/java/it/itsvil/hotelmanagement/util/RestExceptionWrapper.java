package it.itsvil.hotelmanagement.util;

import org.springframework.http.HttpStatusCode;

public record RestExceptionWrapper(HttpStatusCode statusCode, String errorMessage) {

}
