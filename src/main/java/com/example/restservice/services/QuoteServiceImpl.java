package com.example.restservice.services;

import com.example.restservice.dao.quote.QuoteRepository;
import com.example.restservice.entities.Elvl;
import com.example.restservice.entities.Quote;
import com.example.restservice.exceptions.IsinNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;

    @Override
    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(calculateElvl(quote));
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public Double getElvl(String isin) throws IsinNotFoundException {
        Quote quote = quoteRepository.findAll().stream().filter(q -> q.getIsin().equals(isin)).reduce((q1, q2) -> q2).orElseThrow(IsinNotFoundException::new);
        return quote.getElvl().getElvl();
    }

    private Quote calculateElvl(Quote quote) {
        List<Quote> quoteList = quoteRepository.findAll();
        boolean isBaseContainsQuote = quoteList.contains(quote);
        Double bid = quote.getBid();
        double ask = quote.getAsk();
        Elvl elvl = new Elvl();
        quote.setElvl(elvl);
        if (!isBaseContainsQuote) {
            elvl.setElvl(bid);
        } else {
            Quote quoteWithSameIsinFromBase = quoteList.stream().filter(q -> q.equals(quote)).findFirst().get();
            double elvlFromQuoteWithSameIsinFromBase = quoteWithSameIsinFromBase.getElvl().getElvl();
            if (ask < elvlFromQuoteWithSameIsinFromBase || bid == null) {
                quote.getElvl().setElvl(ask);
            } else if (bid > elvlFromQuoteWithSameIsinFromBase) {
                quote.getElvl().setElvl(bid);
            }
        }
        return quote;
    }
}
