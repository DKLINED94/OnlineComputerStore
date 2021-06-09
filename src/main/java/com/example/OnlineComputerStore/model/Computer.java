package com.example.OnlineComputerStore.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "computers")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String motherBoard;

    @Column(nullable = false)
    private String cpu;

    @Column(nullable = false)
    private String hdd;

    @Column(nullable = false)
    private String ram;

    @ToString.Exclude
    @ManyToOne()
    private User users;
}
