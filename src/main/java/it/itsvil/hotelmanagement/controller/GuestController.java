package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.Guest;
import it.itsvil.hotelmanagement.service.GuestService;
import it.itsvil.hotelmanagement.util.LoginRequest;
import it.itsvil.hotelmanagement.util.RestExceptionWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService service;

    public GuestController(GuestService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<Guest> signup(@RequestBody Guest guest, HttpSession session) {
        if (session.getAttribute("user") != null)
            throw new IllegalStateException("already logged in");

        Guest persistedGuest = service.signup(guest);
        session.setAttribute("user", guest);
        return ResponseEntity.ok(persistedGuest);
    }

    @PostMapping("/login")
    public ResponseEntity<Guest> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        if (session.getAttribute("user") != null)
            throw new IllegalStateException("already logged in");

        Guest guest = service.login(loginRequest.email(), loginRequest.password());
        session.setAttribute("user", guest);
        return ResponseEntity.ok(guest);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<RestExceptionWrapper> handleRuntimeException(RuntimeException exception) {
        RestExceptionWrapper wrapper = new RestExceptionWrapper(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(wrapper.statusCode()).body(wrapper);
    }

}
