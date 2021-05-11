package com.example.restservice.controllers;

import com.example.restservice.entities.Quote;
import com.example.restservice.services.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/quote")
@RequiredArgsConstructor
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<List<Quote>> getAllQuotes() {
        return ResponseEntity.ok(quoteService.getAllQuotes());
    }

    @PostMapping
    public ResponseEntity<Quote> saveQuote(@RequestBody @Valid Quote quote) {
        return ResponseEntity.ok(quoteService.saveQuote(quote));
    }

    @DeleteMapping
    public void deleteAllQuotes() {
        quoteService.deleteAllQuotes();
    }
}
