package com.warta.task.Quotes;


import com.warta.task.Quotes.model.Quote;
import com.warta.task.Quotes.repo.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final QuoteRepository quoteRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Quote q1 = Quote.builder()
                .authorFirstName("Jakub")
                .authorLastName("Suski")
                .content("Drink a lot of water")
                .build();

        Quote q2 = Quote.builder()
                .authorFirstName("Andrzej")
                .authorLastName("Andrzejowy")
                .content("bbbbb")
                .build();



        Quote q3 = Quote.builder()
                .authorFirstName("Test")
                .authorLastName("test2")
                .content("aaaa")
                .build();

        quoteRepository.saveAll(Arrays.asList(q1, q2, q3));
    }
}
