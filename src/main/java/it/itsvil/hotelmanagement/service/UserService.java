package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.wrapper.LoginRequest;

public interface UserService {

    User signup(User user);

    User login(LoginRequest loginRequest);

}
