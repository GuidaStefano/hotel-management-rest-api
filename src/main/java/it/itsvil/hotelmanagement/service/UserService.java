package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.User;

public interface UserService {

    User signup(User user);

    User login(String email, String password);

}
