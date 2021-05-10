package com.example.restservice.controllers;

import com.example.restservice.exceptions.IsinNotFoundException;
import com.example.restservice.services.QuoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Slf4j
@AllArgsConstructor
public class IsinController {
    private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<Double> getElvl(@RequestParam(value = "isin", required = true) String isin) {
        try {
            return ResponseEntity.ok(quoteService.getElvl(isin));
        } catch (IsinNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }
}
