package com.example.restservice.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@NoArgsConstructor
public class Elvl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double elvl;

    public void setElvl(double elvl) {
        this.elvl = elvl;
    }

    public double getElvl() {
        return elvl;
    }
}
