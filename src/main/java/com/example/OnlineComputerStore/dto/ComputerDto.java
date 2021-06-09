package com.example.OnlineComputerStore.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ComputerDto {


    private Long id;

    @NotEmpty
    private String motherBoard;

    @NotEmpty
    private String cpu;

    @NotEmpty
    private String hdd;

    @NotEmpty
    private String ram;

    private Long users_id;
}
