package com.example.restservice.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table
@RequiredArgsConstructor
public class Elvl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double elvl;

    public Elvl(Long id, double elvl) {
        this.id = id;
        this.elvl = elvl;
    }
}
