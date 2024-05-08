package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.Room;
import it.itsvil.hotelmanagement.service.RoomService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;

import static it.itsvil.hotelmanagement.util.DateUtil.DATE_PATTERN;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable long id) {
        Room room = service.getRoom(id);
        return ResponseEntity.ok(room);
    }

    @GetMapping
    public ResponseEntity<Set<Room>> getRooms() {
        Set<Room> rooms = service.getRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/available")
    public ResponseEntity<Set<Room>> getAvailableRooms(@RequestParam("check_in") @DateTimeFormat(pattern = DATE_PATTERN) Date checkInDate,
                                       @RequestParam("check_out") @DateTimeFormat(pattern = DATE_PATTERN) Date checkOutDate) {
        Set<Room> availableRooms = service.getAvailableRooms(checkInDate, checkOutDate);
        return ResponseEntity.ok(availableRooms);
    }

}
