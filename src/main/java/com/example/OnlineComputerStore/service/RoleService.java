package com.example.OnlineComputerStore.service;

import com.example.OnlineComputerStore.dto.RoleDto;

import java.util.Set;

public interface RoleService {
    RoleDto save(RoleDto roleDto);

    RoleDto update(Long id, RoleDto roleDto);

    Set<RoleDto> findAll();

    RoleDto findById(Long id);

    RoleDto findByName(String name);

    void deleteById(Long id);
}
