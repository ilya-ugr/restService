package com.example.restservice.services;

import com.example.restservice.dao.quote.QuoteRepository;
import com.example.restservice.entities.Elvl;
import com.example.restservice.entities.Quote;
import com.example.restservice.exceptions.PriceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Validated
@Service
@RequiredArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final QuoteRepository quoteRepository;

    @Override
    public Quote saveQuote(@Valid Quote quote) {
        return quoteRepository.save(Objects.requireNonNull(calculateElvl(quote)));
    }

    @Override
    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    @Override
    public void deleteAllQuotes() {
        quoteRepository.deleteAll();
    }

    private Quote calculateElvl(Quote quote) {
        List<Quote> quoteList = quoteRepository.findAll();
        boolean isBaseContainsQuote = quoteList.contains(quote);
        Double bid = quote.getBid();
        double ask = quote.getAsk();
        if (bid != null && ask <= bid) {
            try {
                throw new PriceException("Ask must be more then bid");
            } catch (PriceException e) {
                e.printStackTrace();
                return null;
            }
        }
        if (isBaseContainsQuote) {
            Quote quoteWithSameIsinFromBase = quoteList.stream().filter(q -> q.equals(quote)).reduce((q1, q2) -> q2).get();
            Elvl elvlFromBase = quoteWithSameIsinFromBase.getElvl();
            double elvlFromQuoteWithSameIsinFromBase = elvlFromBase.getElvl();
            quote.setElvl(elvlFromBase);
            if (ask < elvlFromQuoteWithSameIsinFromBase || bid == null) {
                quote.getElvl().setElvl(ask);
            } else if (bid > elvlFromQuoteWithSameIsinFromBase) {
                quote.getElvl().setElvl(bid);
            }
        } else {
            Elvl elvl = new Elvl();
            elvl.setElvl(bid);
            quote.setElvl(elvl);
        }
        return quote;
    }
}
