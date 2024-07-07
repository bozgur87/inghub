package com.inghub.stock.contract.model.stockexchange;

import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StockExchangeResponse extends StockExchange {
  private Instant lastUpdate;
}
