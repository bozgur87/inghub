package com.inghub.stock.contract.model.stock;

import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StockResponse extends Stock {
  private Long id;
  private Instant lastUpdate;
}
