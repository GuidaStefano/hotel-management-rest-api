package it.itsvil.hotelmanagement.repository;

import it.itsvil.hotelmanagement.entity.Guest;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GuestRepository extends CrudRepository<Guest, Long> {

    boolean existsByEmail(String email);

    Optional<Guest> findByEmail(String email);

}
