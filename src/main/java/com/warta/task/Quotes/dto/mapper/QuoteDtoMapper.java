package com.warta.task.Quotes.dto.mapper;


import com.warta.task.Quotes.dto.QuoteDto;
import com.warta.task.Quotes.model.Quote;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class QuoteDtoMapper {

    private final ModelMapper modelMapper;

    public QuoteDto convertToDto(Quote q) {
        return modelMapper.map(q, QuoteDto.class);
    }

    public Quote convertToEntity(QuoteDto dto) {
        return modelMapper.map(dto, Quote.class);
    }
}
