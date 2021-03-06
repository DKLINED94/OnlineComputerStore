package com.example.OnlineComputerStore.service;


import com.example.OnlineComputerStore.dto.RoleDto;
import com.example.OnlineComputerStore.exception.BadRequestException;
import com.example.OnlineComputerStore.exception.DuplicateException;
import com.example.OnlineComputerStore.exception.NotFoundException;
import com.example.OnlineComputerStore.model.Role;
import com.example.OnlineComputerStore.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteById(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Role not found with id of: " + id);
        }
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        try {
            roleDto.setId(null);
            Role role = modelMapper.map(roleDto, Role.class);
            roleRepository.save(role);
            return modelMapper.map(role, RoleDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateException("Role with name " + roleDto.getName() + " already exists.");
        }
    }

    @Override
    public RoleDto update(Long id, RoleDto roleDto) {
        Optional<Role> maybeRole = roleRepository.findById(id);
        if (maybeRole.isPresent()) {
            maybeRole.get().setName(roleDto.getName());
            Role savedRole = roleRepository.save(maybeRole.get());
            return modelMapper.map(savedRole, RoleDto.class);
        }
        throw new BadRequestException("Role with id: " + id + " does not exists.");
    }

    @Override
    public Set<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> modelMapper
                        .map(role, RoleDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RoleDto findById(Long id) {
        Optional<Role> maybeRole = roleRepository.findById(id);
        if (maybeRole.isPresent()) {
            return modelMapper.map(maybeRole.get(), RoleDto.class);
        }
        throw new NotFoundException("Role not found with id: " + id);
    }

    @Override
    public RoleDto findByName(String name) {
        Optional<Role> maybeRole = roleRepository.findByName(name);
        if (maybeRole.isPresent()) {
            return modelMapper.map(maybeRole.get(), RoleDto.class);
        }
        throw new NotFoundException("Role not found with name: " + name);
    }
}
