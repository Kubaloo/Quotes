package com.warta.task.Quotes;

import com.warta.task.Quotes.controller.QuoteController;
import com.warta.task.Quotes.dto.QuoteDto;
import com.warta.task.Quotes.dto.mapper.QuoteDtoMapper;
import com.warta.task.Quotes.model.Quote;
import com.warta.task.Quotes.repo.QuoteRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class RestControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private QuoteController quoteController;

    @Mock
    private QuoteRepository quoteRepository;

    @Mock
    private QuoteDtoMapper quoteDtoMapper;

    private ModelMapper modelMapper = new ModelMapper();


    @Before
    public void setUp() throws Exception {
        Quote q1 = Quote.builder()
                .id(1)
                .authorFirstName("Jakub")
                .authorLastName("Suski")
                .content("Drink a lot of water")
                .build();

        Quote q2 = Quote.builder()
                .id(2)
                .authorFirstName("Andrzej")
                .authorLastName("Andrzejowy")
                .content("bbbbb")
                .build();
        Quote q3 = Quote.builder()
                .id(3)
                .authorFirstName("Test")
                .authorLastName("test2")
                .content("aaaa")
                .build();

        ArrayList<Quote> quotes = new ArrayList<>();
        quotes.add(q1);
        quotes.add(q2);
        quotes.add(q3);

        when(quoteDtoMapper.convertToDto(q1)).thenReturn(modelMapper.map(q1, QuoteDto.class));
        when(quoteDtoMapper.convertToDto(q2)).thenReturn(modelMapper.map(q2, QuoteDto.class));
        when(quoteDtoMapper.convertToDto(q3)).thenReturn(modelMapper.map(q3, QuoteDto.class));

        when(quoteRepository.findAll()).thenReturn(quotes);
        when(quoteRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(q1));

        mockMvc = MockMvcBuilders.standaloneSetup(quoteController)
                .build();
    }

    @Test
    public void getAllQuotesEndpointTest() throws Exception {
        mockMvc.perform(
                get("/api/quotes")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(3)))
                .andExpect(content().json("[{\"id\":1,\"authorFirstName\":\"Jakub\",\"authorLastName\":\"Suski\",\"content\":\"Drink a lot of water\"},{\"id\":2,\"authorFirstName\":\"Andrzej\",\"authorLastName\":\"Andrzejowy\",\"content\":\"bbbbb\"},{\"id\":3,\"authorFirstName\":\"Test\",\"authorLastName\":\"test2\",\"content\":\"aaaa\"}]"));
    }

    @Test
    public void getQuoteByIdEndpointTest() throws Exception {
        mockMvc.perform(
                get("/api/quotes/1")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(4)))
                .andExpect(content().json("{\"id\":1,\"authorFirstName\":\"Jakub\",\"authorLastName\":\"Suski\",\"content\":\"Drink a lot of water\"}"));

        mockMvc.perform(
                get("/api/quotes/4")
        ).andExpect(status().isNotFound());

    }

    @Test
    public void saveNewQuoteEndpointTest() throws Exception {
        mockMvc.perform(
                post("/api/quotes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":5,\"authorFirstName\":\"Test\",\"authorLastName\":\"test2\",\"content\":\"cccccc\"}")
        ).andExpect(status().isOk());

        verify(quoteRepository, times(1)).save(any());
    }

    @Test
    public void putQuoteEndpointTest() throws Exception {
        mockMvc.perform(
                put("/api/quotes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"authorFirstName\":\"Test\",\"authorLastName\":\"test2\",\"content\":\"cccccc\"}")
        ).andExpect(status().isNoContent());

        verify(quoteRepository, times(1)).save(any());

        mockMvc.perform(
                put("/api/quotes/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"authorFirstName\":\"Test\",\"authorLastName\":\"test2\",\"content\":\"cccccc\"}")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void deleteQuoteEndpointTest() throws Exception {
        mockMvc.perform(
                delete("/api/quotes/1")
        ).andExpect(status().isNoContent());

        verify(quoteRepository, times(1)).deleteById(1L);

        mockMvc.perform(
                delete("/api/quotes/5")
        ).andExpect(status().isNotFound());

    }
}
