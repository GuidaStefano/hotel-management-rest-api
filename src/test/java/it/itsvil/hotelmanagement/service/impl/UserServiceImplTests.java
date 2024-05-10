package it.itsvil.hotelmanagement.service.impl;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.repository.UserRepository;
import it.itsvil.hotelmanagement.service.UserService;
import it.itsvil.hotelmanagement.wrapper.LoginRequest;
import it.itsvil.hotelmanagement.wrapper.SignupRequest;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static it.itsvil.hotelmanagement.util.SecurityUtil.sha256;
import static it.itsvil.hotelmanagement.service.impl.UserServiceImpl.MAX_NAME_LENGTH;
import static it.itsvil.hotelmanagement.service.impl.UserServiceImpl.MIN_NAME_LENGTH;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTests {

    private UserRepository repository;
    private UserService service;

    @BeforeEach
    public void setup() {
        repository = mock(UserRepository.class);
        service = new UserServiceImpl(repository);
    }

    @Test
    public void testSignupSuccess() {
        SignupRequest request = new SignupRequest("Stefano", "Guida", "stefanoguida.gs@gmail.com", "Password@1_");

        when(repository.existsByEmail(request.email())).thenReturn(false);
        when(repository.save(any(User.class))).thenReturn(new User());

        User user = service.signup(request);
        assertNotNull(user);
    }

    @Test
    public void testSignupWithInvalidFirstName() {
        SignupRequest request = new SignupRequest("S", "Guida", "stefanoguida.gs@gmail.com", "Password@1_");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.signup(request));
        assertEquals(String.format("first name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH), exception.getMessage());
    }

    @Test
    public void testSignupWithInvalidLastName() {
        SignupRequest request = new SignupRequest("Stefano", "G", "stefanoguida.gs@gmail.com", "Password@1_");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.signup(request));
        assertEquals(String.format("last name must be between %d and %d characters", MIN_NAME_LENGTH, MAX_NAME_LENGTH), exception.getMessage());
    }

    @Test
    public void testSignupWithInvalidEmail() {
        SignupRequest request = new SignupRequest("Stefano", "Guida", "stefanoguida.gs@gmail", "Password@1_");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.signup(request));
        assertEquals("invalid email address", exception.getMessage());
    }

    @Test
    public void testSignupWithInvalidPassword() {
        SignupRequest request = new SignupRequest("Stefano", "Guida", "stefanoguida.gs@gmail.com", "password");
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.signup(request));
        assertEquals("invalid password", exception.getMessage());
    }

    @Test
    public void testSignupWithExistingEmail() {
        SignupRequest request = new SignupRequest("Stefano", "Guida", "stefanoguida.gs@gmail.com", "Password@1_");

        when(repository.existsByEmail(request.email())).thenReturn(true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.signup(request));
        assertEquals("email already exists", exception.getMessage());
    }

    @Test
    public void testLoginSuccess() {
        String email = "stefanoguida.gs@gmail.com";
        String password = "Password@1_";

        LoginRequest request = new LoginRequest(email, password);
        User user = new User("Stefano", "Guida", email, sha256(password));

        when(repository.findByEmail(request.email())).thenReturn(Optional.of(user));

        User loggedInUser = service.login(request);

        assertNotNull(loggedInUser);
        assertEquals(user, loggedInUser);
    }

    @Test
    public void testLoginWhenUserNotFound() {
        LoginRequest request = new LoginRequest("notexist@gmail.com", "Password@1_");

        when(repository.findByEmail(request.email())).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> service.login(request));
        assertEquals("user not found", exception.getMessage());
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        String email = "stefanoguida.gs@gmail.com";
        String password = "Password@1_";
        String correctPassword = "Password@2_";

        LoginRequest request = new LoginRequest(email, password);
        User user = new User("Stefano", "Guida", email, sha256(correctPassword));

        when(repository.findByEmail(request.email())).thenReturn(Optional.of(user));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> service.login(request));
        assertEquals("incorrect password", exception.getMessage());
    }

}
