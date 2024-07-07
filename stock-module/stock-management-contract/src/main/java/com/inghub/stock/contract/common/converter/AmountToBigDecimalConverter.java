package com.inghub.stock.contract.common.converter;

import com.inghub.stock.contract.model.amount.Amount;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import static java.util.Objects.isNull;

@Component
public class AmountToBigDecimalConverter implements Converter<Amount, BigDecimal> {

  @Value("${default-minor-unit:0}")
  private int defaultMinorUnit;

  /** 
   * Convert the source object of type {@code S} to target type {@code T}
  */
  @Override
  public BigDecimal convert(@NonNull Amount source) {
    if (isNull(source.getValue())) {
      return null;
    }
    
    int minorUnit = source.getMinorUnit() == null ? defaultMinorUnit : source.getMinorUnit();
    return BigDecimal.valueOf(source.getValue())
                     .setScale(minorUnit, RoundingMode.HALF_DOWN)
                     .divide(BigDecimal.TEN.pow(minorUnit), RoundingMode.HALF_DOWN); 
  }
  
}
