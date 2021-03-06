package com.example.restservice.entities;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table
@RequiredArgsConstructor
public class Quote {

    public Quote(String isin, Double bid, double ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "Not empty")
    @Size(min = 12, max = 12)
    private String isin;

    @Column
    private Double bid;

    @Column(nullable = false)
    @NotNull
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

    public void setElvl(Elvl elvl) {
        this.elvl = elvl;
    }

    public String getIsin() {
        return isin;
    }

    public Double getBid() {
        return bid;
    }

    public double getAsk() {
        return ask;
    }

    public Elvl getElvl() {
        return elvl;
    }
}
