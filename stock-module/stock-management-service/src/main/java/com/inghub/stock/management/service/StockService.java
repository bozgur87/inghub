package com.inghub.stock.management.service;

import com.inghub.stock.contract.common.exception.DatabaseException;
import com.inghub.stock.management.repository.StockRepository;
import com.inghub.stock.management.repository.entities.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

  private final StockRepository stockRepository;

  public StockEntity findStock(Long id) {
    return stockRepository.findById(id).orElseThrow(() -> new DatabaseException("No data found by id:", id));
  }

  public StockEntity saveStock(StockEntity stockEntity) {
    return stockRepository.save(stockEntity);
  }

  public void deleteStock(StockEntity stockEntity) {
    stockRepository.delete(stockEntity);
  }

  public StockEntity updateStock(StockEntity stockEntity) {
    return stockRepository.saveAndFlush(stockEntity);
  }

}
