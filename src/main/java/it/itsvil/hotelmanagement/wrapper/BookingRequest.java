package it.itsvil.hotelmanagement.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import static it.itsvil.hotelmanagement.util.DateUtil.DATE_PATTERN;

public record BookingRequest(long roomId,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN) Date checkInDate,
                             @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_PATTERN) Date checkOutDate) {

}
