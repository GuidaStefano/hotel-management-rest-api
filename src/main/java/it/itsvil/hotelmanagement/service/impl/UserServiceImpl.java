package it.itsvil.hotelmanagement.service.impl;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.repository.UserRepository;
import it.itsvil.hotelmanagement.service.UserService;
import it.itsvil.hotelmanagement.wrapper.LoginRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MAX_NAME_LENGTH = 32;
    private static final String EMAIL_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User signup(User user) {
        Objects.requireNonNull(user, "payload cannot be null");
        Objects.requireNonNull(user.getFirstName(), "first name cannot be null");
        Objects.requireNonNull(user.getLastName(), "last name cannot be null");
        Objects.requireNonNull(user.getEmail(), "email cannot be null");
        Objects.requireNonNull(user.getPassword(), "password cannot be null");

      if (user.getFirstName().length() < MIN_NAME_LENGTH || user.getFirstName().length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(
                    String.format("first name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH));

        if (user.getLastName().length() < MIN_NAME_LENGTH || user.getLastName().length() > MAX_NAME_LENGTH)
            throw new IllegalArgumentException(
                    String.format("last name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH));

        if (!user.getEmail().matches(EMAIL_PATTERN))
            throw new IllegalArgumentException("invalid email address");

        if (!user.getPassword().matches(PASSWORD_PATTERN))
            throw new IllegalArgumentException("invalid password");

        if (repository.existsByEmail(user.getEmail()))
            throw new IllegalArgumentException("email already exists");

        return repository.save(user);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        Objects.requireNonNull(loginRequest, "payload cannot be null");

        String email = loginRequest.email();
        String password = loginRequest.password();

        Objects.requireNonNull(email, "email cannot be null");
        Objects.requireNonNull(password, "password cannot be null");

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("user not found"));

        if (!password.equals(user.getPassword()))
            throw new IllegalArgumentException("incorrect password");

        return user;
    }

}
