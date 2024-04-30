package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.Guest;
import it.itsvil.hotelmanagement.service.GuestService;
import it.itsvil.hotelmanagement.util.RestExceptionWrapper;
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

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest) {
        Guest persistedGuest = service.createGuest(guest);
        return ResponseEntity.ok(persistedGuest);
    }

    @ExceptionHandler({IllegalArgumentException.class, NullPointerException.class})
    public ResponseEntity<RestExceptionWrapper> handleRuntimeException(RuntimeException exception) {
        RestExceptionWrapper wrapper = new RestExceptionWrapper(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(wrapper.statusCode()).body(wrapper);
    }

}
