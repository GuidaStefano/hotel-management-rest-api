package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.Room;

import java.util.Date;
import java.util.Set;

public interface RoomService {

    Room getRoom(long id);

    Set<Room> getRooms();

    Set<Room> getAvailableRooms(Date checkInDate, Date checkOutDate);

}
