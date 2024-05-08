package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.service.UserService;
import it.itsvil.hotelmanagement.wrapper.LoginRequest;
import it.itsvil.hotelmanagement.wrapper.ResponseMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User user, HttpSession session) {
        if (session.getAttribute("user") != null)
            throw new IllegalStateException("already logged in");

        User persistedUser = service.signup(user);
        session.setAttribute("user", user);
        return ResponseEntity.ok(persistedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        if (session.getAttribute("user") != null)
            throw new IllegalStateException("already logged in");

        User user = service.login(loginRequest);
        session.setAttribute("user", user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(HttpSession session) {
        session.invalidate();
        ResponseMessage wrapper = new ResponseMessage(HttpStatus.OK, "logged out successfully");
        return ResponseEntity.status(wrapper.statusCode()).body(wrapper);
    }

}
