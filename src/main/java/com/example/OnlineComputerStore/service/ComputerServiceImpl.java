package com.example.OnlineComputerStore.service;

import com.example.OnlineComputerStore.dto.ComputerDto;
import com.example.OnlineComputerStore.exception.BadRequestException;
import com.example.OnlineComputerStore.exception.DuplicateException;
import com.example.OnlineComputerStore.exception.NotFoundException;
import com.example.OnlineComputerStore.model.Computer;
import com.example.OnlineComputerStore.repository.ComputerRepository;
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
public class ComputerServiceImpl implements ComputerService {
    private final ComputerRepository computerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ComputerServiceImpl(ComputerRepository ComputerRepository, ModelMapper modelMapper) {
        this.computerRepository = ComputerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void deleteById(Long id) {
        try {
            computerRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Computer not found with id of : " + id);
        }
    }
    @Override
    public ComputerDto save(ComputerDto computerDto) {
        try {
            computerDto.setId(null);
            Computer computer = modelMapper.map(computerDto, Computer.class);
            Computer savedComputer = computerRepository.save(computer);
            return modelMapper.map(savedComputer, ComputerDto.class);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateException("The computer already exists.");
        }
    }
    @Override
    public ComputerDto update(Long id, ComputerDto computerDto) {
        Optional<Computer> maybeComputer = computerRepository.findById(id);
        if (maybeComputer.isPresent()) {
            modelMapper.map(computerDto, maybeComputer.get());
            Computer savedComputer = computerRepository.save(maybeComputer.get());
            return modelMapper.map(savedComputer, ComputerDto.class);
        }
        throw new BadRequestException("Computer with id: " + id + " does not exist.");
    }

    @Override
    public ComputerDto findById(Long id) {
        Optional<Computer> maybeComputer = computerRepository.findById(id);
        if (maybeComputer.isPresent()) {
            return modelMapper.map(maybeComputer.get(), ComputerDto.class);
        }
        throw new NotFoundException("Computer not found with id: " + id);
    }

    @Override
    public Set<ComputerDto> findByMotherBoard(String motherBoard) {
        if (computerRepository.findByMotherBoard(motherBoard).isEmpty()) {
            throw new NotFoundException("Computers not found with motherboard: " + motherBoard);
        }
        return computerRepository.findByMotherBoard(motherBoard)
                .stream().map(computer -> modelMapper
                        .map(computer, ComputerDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComputerDto> findByCpu(String cpu) {
        if (computerRepository.findByCpu(cpu).isEmpty()) {
            throw new NotFoundException("Computers not found with cpu: " + cpu);
        }
        return computerRepository.findByCpu(cpu)
                .stream().map(computer -> modelMapper
                        .map(computer, ComputerDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComputerDto> findByHdd(String hdd) {
        if (computerRepository.findByHdd(hdd).isEmpty()) {
            throw new NotFoundException("Computers not found with hdd: " + hdd);
        }
        return computerRepository
                .findByHdd(hdd)
                .stream().map(computer -> modelMapper
                        .map(computer, ComputerDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComputerDto> findByRam(String ram) {
        if (computerRepository.findByRam(ram).isEmpty()) {
            throw new NotFoundException("Computers not found with ram: " + ram);
        }
        return computerRepository
                .findByRam(ram)
                .stream().map(computer -> modelMapper
                        .map(computer, ComputerDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<ComputerDto> findAll() {
        if (computerRepository.findAll().isEmpty()) {
            throw new NotFoundException("Computers not found.");
        }
        return computerRepository.findAll()
                .stream().map(computer -> modelMapper
                        .map(computer, ComputerDto.class))
                .collect(Collectors.toSet());
    }
}
