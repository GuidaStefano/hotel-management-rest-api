package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.Room;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return null;
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable int id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable int id) {

    }

}
