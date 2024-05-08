package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.wrapper.LoginRequest;
import it.itsvil.hotelmanagement.wrapper.SignupRequest;

public interface UserService {

    User signup(SignupRequest signupRequest);

    User login(LoginRequest loginRequest);

}
