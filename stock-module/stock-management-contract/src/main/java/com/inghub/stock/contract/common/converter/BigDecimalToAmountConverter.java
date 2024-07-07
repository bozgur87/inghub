package com.inghub.stock.contract.common.converter;

import com.inghub.stock.contract.model.amount.Amount;
import java.math.BigDecimal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class BigDecimalToAmountConverter implements Converter<BigDecimal, Amount> {

  /**
   * Convert the source object of type {@code S} to target type {@code T}
   */
  @Override
  public Amount convert(@NonNull BigDecimal source) {
    Amount amount = new Amount();
    amount.setValue(source.multiply(BigDecimal.TEN.pow(source.scale())).longValue());
    amount.setMinorUnit(source.scale());
    return amount;
  }

}
