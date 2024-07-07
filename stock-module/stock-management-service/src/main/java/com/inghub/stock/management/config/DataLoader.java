package com.inghub.stock.management.config;

import com.inghub.stock.management.repository.StockExchangeRepository;
import com.inghub.stock.management.repository.entities.StockExchangeEntity;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {
  private final StockExchangeRepository stockExchangeRepository;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Instant now = Instant.now();
    StockExchangeEntity entity = new StockExchangeEntity("EX-1", "Exchange 1", false, now, now, null);
    stockExchangeRepository.save(entity);
  }

}
