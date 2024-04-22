package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.User;

public interface UserService {

    User createUser(String firstName, String lastName, String email, String password);

}
