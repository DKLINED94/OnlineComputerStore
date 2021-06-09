package com.example.OnlineComputerStore.controller;

import com.example.OnlineComputerStore.dto.UserDto;
import com.example.OnlineComputerStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "ususersers")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto userDto) {
        return userService.update(id, userDto);
    }

    @PostMapping
    public UserDto save(@RequestBody @Valid UserDto userDto) {
        return userService.save(userDto);

    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping()
    public Set<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/email/{email}")
    public UserDto findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/phoneNumber/{phoneNumber}")
    public UserDto findByPhoneNumber(@PathVariable String phoneNumber) {
        return userService.findByPhoneNumber(phoneNumber);
    }
}
