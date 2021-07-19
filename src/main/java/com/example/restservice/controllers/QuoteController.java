package com.example.restservice.controllers;

import com.example.restservice.entities.Quote;
import com.example.restservice.services.QuoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
        Lock lock = new ReentrantLock();
        lock.lock();
        Quote responseQuote = quoteService.saveQuote(quote);
        lock.unlock();
        if (responseQuote != null) {
            return ResponseEntity.ok(responseQuote);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public void deleteAllQuotes() {
        quoteService.deleteAllQuotes();
    }
}
