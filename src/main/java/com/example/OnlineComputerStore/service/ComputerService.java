package com.example.OnlineComputerStore.service;

import com.example.OnlineComputerStore.dto.ComputerDto;

import java.util.Set;

public interface ComputerService {
    ComputerDto save(ComputerDto ComputerDto);

    ComputerDto update(Long id, ComputerDto ComputerDto);

    ComputerDto findById(Long id);

    Set<ComputerDto> findByMotherBoard(String motherBoard);

    Set<ComputerDto> findByCpu(String cpu);

    Set<ComputerDto> findByHdd(String hdd);

    Set<ComputerDto> findByRam(String ram);

    Set<ComputerDto> findAll();

    void deleteById(Long id);

}