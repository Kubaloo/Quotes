package com.warta.task.Quotes.controller;


import com.warta.task.Quotes.dto.QuoteDto;
import com.warta.task.Quotes.dto.mapper.QuoteDtoMapper;
import com.warta.task.Quotes.model.Quote;
import com.warta.task.Quotes.repo.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quotes")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteRepository quoteRepository;
    private final QuoteDtoMapper quoteDtoMapper;

    @GetMapping
    public ResponseEntity<Collection<QuoteDto>> getQuotes() {
        List<Quote> allQuotes = quoteRepository.findAll();
        List<QuoteDto> result = allQuotes.stream()
                .map(quoteDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{quoteId}")
    public ResponseEntity<QuoteDto> getQuoteById(@PathVariable Long quoteId) {
        Optional<Quote> q =
                quoteRepository.findById(quoteId);
        if (q.isPresent()) {
            QuoteDto quoteDto = quoteDtoMapper.convertToDto(q.get());
            return new ResponseEntity<>(quoteDto,
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null,
                    HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Quote saveNewQuote(@Valid @RequestBody QuoteDto quoteDto) {
        Quote entity = quoteDtoMapper.convertToEntity(quoteDto);
        return quoteRepository.save(entity);
    }

    @PutMapping("/{quoteId}")
    public ResponseEntity updateQuote(@Valid @PathVariable Long quoteId, @RequestBody QuoteDto quoteDto) {
        Optional<Quote> currentQuote =
                quoteRepository.findById(quoteId);
        if (currentQuote.isPresent()) {
            quoteDto.setId(quoteId);
            Quote entity = quoteDtoMapper.convertToEntity(quoteDto);
            quoteRepository.save(entity);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{quoteId}")
    public ResponseEntity deleteQuote(@PathVariable Long quoteId) {
        Optional<Quote> currentQuote =
                quoteRepository.findById(quoteId);
        if (currentQuote.isPresent()) {
            quoteRepository.deleteById(quoteId);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
