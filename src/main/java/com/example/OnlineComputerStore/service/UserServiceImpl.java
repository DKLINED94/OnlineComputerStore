package com.example.OnlineComputerStore.service;

import com.example.OnlineComputerStore.dto.RoleDto;
import com.example.OnlineComputerStore.dto.UserDto;
import com.example.OnlineComputerStore.exception.BadRequestException;
import com.example.OnlineComputerStore.exception.DuplicateException;
import com.example.OnlineComputerStore.exception.NotFoundException;
import com.example.OnlineComputerStore.model.Role;
import com.example.OnlineComputerStore.model.User;
import com.example.OnlineComputerStore.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository,RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public void deleteById(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public UserDto save(UserDto userDto) {
        try {
            userDto.setId(null);
            RoleDto roleDto = roleService.findByName("ROLE_CLIENT");
            User user = modelMapper.map(userDto, User.class);
            user.setRoles(modelMapper.map(roleDto, Role.class));
            User savedUser = userRepository.save(user);
            return modelMapper.map(savedUser, UserDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateException("User with name " + userDto.getName() + " already exists.");
        }
    }

    @Override
    public UserDto update(Long id, UserDto userDto) {
        Optional<User> maybeUser = userRepository.findById(id);
        if (maybeUser.isPresent()) {
            User savedUser = userRepository.save(maybeUser.get());
            return modelMapper.map(savedUser, UserDto.class);
        }
        throw new BadRequestException("User with id: " + id + " does not exists.");
    }

    @Override
    public Set<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper
                        .map(user, UserDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserDto findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException("User not found with id: " + id);
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException("User not found with Email: " + email);
    }

    @Override
    public UserDto findByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        throw new NotFoundException("User not found with Phone Number: " + phoneNumber);
    }
}
