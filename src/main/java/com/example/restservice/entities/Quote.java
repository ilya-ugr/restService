package com.example.restservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isin;

    @Column
    private Double bid;

    @Column(nullable = false)
    private double ask;

    @ManyToOne(cascade = {CascadeType.ALL})
    private Elvl elvl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return isin.equals(quote.isin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isin);
    }
}
