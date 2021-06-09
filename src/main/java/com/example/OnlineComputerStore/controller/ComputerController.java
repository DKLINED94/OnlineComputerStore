package com.example.OnlineComputerStore.controller;

import com.example.OnlineComputerStore.dto.ComputerDto;
import com.example.OnlineComputerStore.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping(value = "computers")
public class ComputerController {
    private final ComputerService computerService;

    @Autowired
    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PutMapping(value = "/{id}")
    public ComputerDto update(@PathVariable Long id, @RequestBody ComputerDto computerDto) {
        return computerService.update(id, computerDto);
    }

    @PostMapping
    public ComputerDto save(@RequestBody @Valid ComputerDto computerDto) {
        return computerService.save(computerDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(Long id) {
        computerService.deleteById(id);
    }

    @GetMapping
    public Set<ComputerDto> findAll() {
        return computerService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ComputerDto findById(@PathVariable Long id) {
        return computerService.findById(id);
    }

    @GetMapping(value = "/motherboard/{motherBoard}")
    public Set<ComputerDto> findByMotherBoard(@PathVariable String motherBoard) {
        return computerService.findByMotherBoard(motherBoard);
    }

    @GetMapping(value = "/cpu/{cpu}")
    public Set<ComputerDto> findByCpu(@PathVariable String cpu) {
        return computerService.findByCpu(cpu);
    }

    @GetMapping(value = "/hdd/{hdd}")
    public Set<ComputerDto> findByHdd(@PathVariable String hdd) {
        return computerService.findByHdd(hdd);
    }

    @GetMapping(value = "/ram/{ram}")
    public Set<ComputerDto> findByRam(@PathVariable String ram) {
        return computerService.findByRam(ram);
    }
}

