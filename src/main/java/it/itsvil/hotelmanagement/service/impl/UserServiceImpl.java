package it.itsvil.hotelmanagement.service.impl;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.repository.UserRepository;
import it.itsvil.hotelmanagement.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 32;
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\\\d)(?=.*[@#$%^&+=]).{8,}$";

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password) {
        Objects.requireNonNull(firstName, "first name cannot be null");
        Objects.requireNonNull(lastName, "last name cannot be null");
        Objects.requireNonNull(email, "email cannot be null");
        Objects.requireNonNull(password, "password cannot be null");

        if (firstName.length() < MIN_NAME_LENGTH || firstName.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(
                    String.format("first name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH));

        if (lastName.length() < MIN_NAME_LENGTH || lastName.length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(
                    String.format("last name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH));

        if (!email.matches(EMAIL_PATTERN))
            throw new IllegalArgumentException("invalid email address");

        if (!password.matches(PASSWORD_PATTERN))
            throw new IllegalArgumentException("invalid password");

        User user = new User(firstName, lastName, email, password);
        return repository.save(user);
    }

}
