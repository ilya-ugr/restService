package com.example.restservice.services;

import com.example.restservice.entities.Quote;

import java.util.List;

public interface QuoteService {
    Quote saveQuote(Quote quote);

    List<Quote> getAllQuotes();

    void deleteAllQuotes();

}
