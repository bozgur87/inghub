package com.inghub.stock.management.service;

import com.inghub.stock.contract.common.exception.DatabaseException;
import com.inghub.stock.management.repository.StockExchangeRepository;
import com.inghub.stock.management.repository.entities.StockExchangeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockExchangeService {

  private final StockExchangeRepository stockExchangeRepository;
  private final StockService stockService;

  public StockExchangeEntity findStockExchange(String name) {
    return stockExchangeRepository.findById(name)
            .orElseThrow(() -> new DatabaseException("No data found by name:", name));
  }

  public StockExchangeEntity addStock(StockExchangeEntity entity, Long id) {
    return stockExchangeRepository.save(entity.addStock(stockService.findStock(id)));
  }

  public StockExchangeEntity deleteStock(StockExchangeEntity entity, Long id) {
    return stockExchangeRepository.save(entity.deleteStock(stockService.findStock(id)));
  }
}
