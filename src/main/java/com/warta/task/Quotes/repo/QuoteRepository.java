package com.warta.task.Quotes.repo;


import com.warta.task.Quotes.model.Quote;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<Quote, Long> {
   List<Quote> findAll();


}
