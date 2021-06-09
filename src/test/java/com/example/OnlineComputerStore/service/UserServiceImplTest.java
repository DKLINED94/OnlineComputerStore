package com.example.OnlineComputerStore.service;

import com.example.OnlineComputerStore.model.User;
import com.example.OnlineComputerStore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    private RoleService roleService;
    private UserServiceImpl userServiceImpl;

    @BeforeEach
    public void setUp() {
        userServiceImpl = new UserServiceImpl(userRepository,roleService);
    }

    @Test
    void saveExpectNPE() {
        NullPointerException nullPointerException = assertThrows(NullPointerException.class, () -> userServiceImpl.save(null));
        String actual = null;
        String expected = nullPointerException.getMessage();
        assertEquals(expected, null);
    }

    @Test
    void getUsersTest() {
    when(userRepository.findAll()).thenReturn(Stream.of(
            User.builder().name("blagovest").email("blagoevst@abv.bg").password("123321").phoneNumber("089321312").build())
                    .collect(Collectors.toList()));

    assertEquals(1,userServiceImpl.findAll().size());
    }
}