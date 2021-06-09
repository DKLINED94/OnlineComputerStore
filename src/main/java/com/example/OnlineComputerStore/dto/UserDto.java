package com.example.OnlineComputerStore.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String email;

    @NotEmpty
    @Size(min = 8, max = 16, message = "Password have to be between 8 and 16 symbols.")
    private String password;

    @NotEmpty
    @Size(min = 10, max = 13, message = "Phone number have to be 10 or 13 symbols.")
    private String phoneNumber;

    private RoleDto roles;

    private Set<ComputerDto> computers;
}
