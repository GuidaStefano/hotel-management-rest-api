package it.itsvil.hotelmanagement.repository;

import it.itsvil.hotelmanagement.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
