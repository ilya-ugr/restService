package com.example.restservice.services;

import com.example.restservice.dao.quote.QuoteRepository;
import com.example.restservice.entities.Elvl;
import com.example.restservice.entities.Quote;
import com.example.restservice.exceptions.IsinNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElvlServiceImpl implements ElvlService {
    private final QuoteRepository quoteRepository;

    @Override
    public Double getElvl(String isin) throws IsinNotFoundException {
        Quote quote = quoteRepository.findAll().stream().filter(q -> q.getIsin().equals(isin)).findAny()
                .orElseThrow(IsinNotFoundException::new);
        return quote.getElvl().getElvl();
    }

    @Override
    public Map<String, Elvl> getAllElvl() {
        Set<Quote> setQuotes = quoteRepository.findAll().stream().distinct().collect(Collectors.toSet());
        Map<String, Elvl> collect = setQuotes.stream().collect(Collectors.toMap(quote -> quote.getIsin(), quote -> quote.getElvl()));
        return collect;
    }
}
