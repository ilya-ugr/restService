package com.example.restservice.services;

import com.example.restservice.entities.Elvl;
import com.example.restservice.exceptions.IsinNotFoundException;

import java.util.Map;

public interface ElvlService {
    Double getElvl(String isin) throws IsinNotFoundException;

    Map<String, Elvl> getAllElvl();

}
