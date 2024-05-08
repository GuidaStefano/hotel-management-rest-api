package it.itsvil.hotelmanagement.service.impl;

import it.itsvil.hotelmanagement.entity.Room;
import it.itsvil.hotelmanagement.repository.RoomRepository;
import it.itsvil.hotelmanagement.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;

    public RoomServiceImpl(RoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public Room getRoom(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("room not found"));
    }

    @Override
    public Set<Room> getRooms() {
        Set<Room> rooms = new HashSet<>();
        for (Room room : repository.findAll())
            rooms.add(room);

        return rooms;
    }

    @Override
    public Set<Room> getAvailableRooms(Date checkInDate, Date checkOutDate) {
        Objects.requireNonNull(checkInDate, "check in date cannot be null");
        Objects.requireNonNull(checkOutDate, " check out date cannot be null");

        return repository.findAvailableRoomsBetweenDates(checkInDate, checkOutDate);
    }

}
