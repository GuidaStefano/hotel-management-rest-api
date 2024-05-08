package it.itsvil.hotelmanagement.repository;

import it.itsvil.hotelmanagement.entity.Booking;
import it.itsvil.hotelmanagement.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    Set<Booking> findByUser(User user);

}
