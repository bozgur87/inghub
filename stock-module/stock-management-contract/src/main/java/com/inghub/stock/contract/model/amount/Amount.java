package com.inghub.stock.contract.model.amount;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Amount {
  private CurrencyCodes currency;
  private Long value;
  private Integer minorUnit;

}
