package it.itsvil.hotelmanagement.repository;

import it.itsvil.hotelmanagement.entity.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("SELECT DISTINCT r FROM Room r LEFT JOIN r.bookings b WHERE b.checkInDate > :checkOutDate OR b.checkOutDate < :checkInDate OR b.id IS NULL")
    Set<Room> findAvailableRoomsBetweenDates(Date checkInDate, Date checkOutDate);

}
