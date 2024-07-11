package com.inghub.stock.management.domain;

import com.inghub.stock.contract.model.amount.Amount;
import com.inghub.stock.contract.model.amount.CurrencyCodes;
import com.inghub.stock.contract.model.stock.StockRequest;
import com.inghub.stock.management.repository.entities.StockEntity;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class StockMockDataProvider {

  public static StockEntity initializeStockEntity() {
    StockEntity entity = new StockEntity();
    entity.setId(1L);
    entity.setName("Stock1");
    entity.setDescription("Stock 1");
    entity.setCurrency("TRY");
    entity.setPrice(BigDecimal.valueOf(50));
    entity.setLastModifiedOn(Instant.now());
    return entity;
  }

  public static StockRequest generateStockRequest() {
    StockRequest request = new StockRequest();
    request.setName("Stock1");
    request.setDescription("Stock 1");
    request.setAmount(generateAmount());
    return request;
  }

  public static Amount generateAmount() {
    Amount amount = new Amount();
    amount.setCurrency(CurrencyCodes.TRY);
    amount.setValue(50L);
    amount.setMinorUnit(0);
    return amount;
  }
}
