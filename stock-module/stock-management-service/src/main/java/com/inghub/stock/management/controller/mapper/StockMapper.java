package com.inghub.stock.management.controller.mapper;

import com.inghub.stock.contract.common.converter.AmountToBigDecimalConverter;
import com.inghub.stock.contract.common.converter.BigDecimalToAmountConverter;
import com.inghub.stock.contract.model.amount.Amount;
import com.inghub.stock.contract.model.amount.CurrencyCodes;
import com.inghub.stock.contract.model.stock.StockRequest;
import com.inghub.stock.contract.model.stock.StockResponse;
import com.inghub.stock.management.repository.entities.StockEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        uses = {BigDecimalToAmountConverter.class, AmountToBigDecimalConverter.class})
public interface StockMapper {

  @BaseAmountMapper
  StockEntity mapRequestToEntity(StockRequest request);

  StockEntity mapIdToEntity(Long id);

  @Mapping(target = "amount", source = "price")
  @Mapping(target = "lastUpdate", source = "lastModifiedOn")
  StockResponse mapEntityToResponse(StockEntity entity);

  @AfterMapping
  default void setCurrency(StockEntity source, @MappingTarget StockResponse target) {
    target.getAmount().setCurrency(mapCurrency(source.getCurrency()));
  }

  @ValueMapping(target = MappingConstants.NULL, source = MappingConstants.ANY_REMAINING)
  CurrencyCodes mapCurrency(String currency);

  @BaseAmountMapper
  StockEntity updateAmountPartially(@MappingTarget StockEntity entity, Amount amount);

}
