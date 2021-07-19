package com.example.restservice.controllers;

import com.example.restservice.entities.Elvl;
import com.example.restservice.exceptions.IsinNotFoundException;
import com.example.restservice.services.ElvlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping
@Slf4j
@AllArgsConstructor
public class ElvlController {
    private final ElvlService elvlService;

    @GetMapping
    public ResponseEntity<Double> getElvl(@RequestParam(value = "isin", required = true) String isin) {
        try {
            return ResponseEntity.ok(elvlService.getElvl(isin));
        } catch (IsinNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(path = "/getAllElvl")
    public ResponseEntity<Map<String, Elvl>> gelAllElvl() {
        return ResponseEntity.ok(elvlService.getAllElvl());
    }
}
