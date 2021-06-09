package com.example.OnlineComputerStore.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ToString.Exclude
    @OneToMany(mappedBy = "roles")
    private List<User> usersList;
}
