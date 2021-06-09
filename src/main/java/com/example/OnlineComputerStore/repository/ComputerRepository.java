package com.example.OnlineComputerStore.repository;

import com.example.OnlineComputerStore.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    List<Computer> findByMotherBoard(String motherBoard);

    List<Computer> findByCpu(String cpu);

    List<Computer> findByHdd(String hdd);

    List<Computer> findByRam(String ram);
}
