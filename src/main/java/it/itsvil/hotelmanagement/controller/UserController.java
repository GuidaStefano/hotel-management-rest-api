package it.itsvil.hotelmanagement.controller;

import it.itsvil.hotelmanagement.entity.User;
import it.itsvil.hotelmanagement.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User createUser(@RequestBody String firstName, @RequestBody String lastName, @RequestBody String email, @RequestBody String password) {
        return service.createUser(firstName, lastName, email, password);
    }

}
