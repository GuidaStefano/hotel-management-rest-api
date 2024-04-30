package it.itsvil.hotelmanagement.repository;

import it.itsvil.hotelmanagement.entity.Guest;
import org.springframework.data.repository.CrudRepository;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    boolean existsByEmail(String email);

}
