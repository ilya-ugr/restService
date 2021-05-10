package com.example.restservice.services;

import com.example.restservice.entities.Quote;
import com.example.restservice.exceptions.IsinNotFoundException;

import java.util.List;

public interface QuoteService {
    Quote saveQuote(Quote quote);

    List<Quote> getAllQuotes();

    Double getElvl(String isin) throws IsinNotFoundException;
}
