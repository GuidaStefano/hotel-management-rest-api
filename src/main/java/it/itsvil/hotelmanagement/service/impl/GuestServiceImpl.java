package it.itsvil.hotelmanagement.service.impl;

import it.itsvil.hotelmanagement.entity.Guest;
import it.itsvil.hotelmanagement.repository.GuestRepository;
import it.itsvil.hotelmanagement.service.GuestService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class GuestServiceImpl implements GuestService {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 32;
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

    private final GuestRepository repository;

    public GuestServiceImpl(GuestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Guest createGuest(Guest guest) {
        Objects.requireNonNull(guest, "payload cannot be null");
        Objects.requireNonNull(guest.getFirstName(), "first name cannot be null");
        Objects.requireNonNull(guest.getLastName(), "last name cannot be null");
        Objects.requireNonNull(guest.getEmail(), "email cannot be null");
        Objects.requireNonNull(guest.getPassword(), "password cannot be null");

      if (guest.getFirstName().length() < MIN_NAME_LENGTH || guest.getFirstName().length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(
                    String.format("first name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH));

        if (guest.getLastName().length() < MIN_NAME_LENGTH || guest.getLastName().length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(
                    String.format("last name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH));

        if (!guest.getEmail().matches(EMAIL_PATTERN))
            throw new IllegalArgumentException("invalid email address");

        if (!guest.getPassword().matches(PASSWORD_PATTERN))
            throw new IllegalArgumentException("invalid password");

        if (repository.existsByEmail(guest.getEmail()))
            throw new IllegalArgumentException("email already exists");

        return repository.save(guest);
    }

}
