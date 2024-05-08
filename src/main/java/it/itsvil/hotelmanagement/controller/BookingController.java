package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.Booking;
import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.service.BookingService;
import it.itsvil.hotelmanagement.wrapper.BookingRequest;
import it.itsvil.hotelmanagement.wrapper.ResponseMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody BookingRequest bookingRequest, HttpSession session) {
        User user = getUser(session);

        Booking booking = service.createBooking(user, bookingRequest);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/simulate")
    public ResponseEntity<Booking> simulateBooking(@RequestBody BookingRequest bookingRequest) {
        Booking booking = service.createBooking(null, bookingRequest, true);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable long id, HttpSession session) {
        User user = getUser(session);

        Booking booking = service.getBooking(user, id);
        return ResponseEntity.ok(booking);
    }

    @GetMapping
    public ResponseEntity<Set<Booking>> getBookings(HttpSession session) {
        User user = getUser(session);

        Set<Booking> bookings = service.getBookings(user);
        return ResponseEntity.ok(bookings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteBooking(@PathVariable long id, HttpSession session) {
        User user = getUser(session);

        service.deleteBooking(user, id);

        ResponseMessage wrapper = new ResponseMessage(HttpStatus.OK,
                String.format("booking (id: %d) deleted successfully", id));
        return ResponseEntity.status(wrapper.statusCode()).body(wrapper);
    }

    private User getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            throw new IllegalStateException("you must login first");

        return user;
    }

}
