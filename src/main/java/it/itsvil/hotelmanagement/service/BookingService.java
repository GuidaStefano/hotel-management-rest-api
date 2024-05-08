package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.Booking;
import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.wrapper.BookingRequest;

import java.util.Set;

public interface BookingService {

    Booking createBooking(User user, BookingRequest bookingRequest, boolean simulate);

    Booking createBooking(User user, BookingRequest bookingRequest);

    Booking getBooking(User user, long id);

    Set<Booking> getBookings(User user);

    void deleteBooking(User user, long id);

}
