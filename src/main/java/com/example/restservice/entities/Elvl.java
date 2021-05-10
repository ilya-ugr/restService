package com.example.restservice.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "elvls")
public class Elvl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double elvl;
}
