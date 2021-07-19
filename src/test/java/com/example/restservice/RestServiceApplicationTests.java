package com.example.restservice;

import com.example.restservice.entities.Quote;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class RestServiceApplicationTests {
    private static final String URI = "/quote";
    private static final String URI_ISIN = "/";
    private static final String URI_ALL_ELVL = "/getAllElvl";

    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Test
    public void test() {
        Quote quoteFirst = new Quote("RU000A0JX0J2", 100.2, 101.9);
        Quote quoteSecond = new Quote("RU000A0JX0J2", 100.5, 101.9);
        mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(quoteFirst)))
                .andExpect(status().isOk());
        mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(quoteSecond)))
                .andExpect(status().isOk());

        mockMvc.perform(get(URI_ISIN).param("isin", "RU000A0JX0J2"))
                .andExpect(status().isOk());
        mockMvc.perform(get(URI_ISIN).param("isin", "notExists"))
                .andExpect(status().isNoContent());

        Quote quoteThird = new Quote("RU000A0JX0J2", null, 101.9);
        mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(quoteThird)))
                .andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testGetAllQuotes() {
        mockMvc.perform(get(URI)).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testGetAllElvl() {
        mockMvc.perform(get(URI_ALL_ELVL)).andExpect(status().isOk());
    }

    @SneakyThrows
    @Test
    public void testDeleteAllQuotes() {
        mockMvc.perform(delete(URI)).andExpect(status().isOk());
    }

    @Test
    void testExpectedException() {
        Assertions.assertThrows(NestedServletException.class, () -> {
            Quote quote = new Quote("RU000A0JX0J2", 100.2, 100);
            mockMvc.perform(post(URI).contentType(MediaType.APPLICATION_JSON)
                    .content(new ObjectMapper().writeValueAsString(quote)))
                    .andExpect(status().isBadRequest());
        });
    }
}
