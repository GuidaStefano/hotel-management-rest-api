package it.itsvil.hotelmanagement.service;

import it.itsvil.hotelmanagement.entity.Guest;

public interface GuestService {

    Guest signup(Guest guest);

    Guest login(String email, String password);

}
