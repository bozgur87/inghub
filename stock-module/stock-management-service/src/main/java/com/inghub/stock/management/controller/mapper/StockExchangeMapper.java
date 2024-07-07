package com.inghub.stock.management.controller.mapper;

import com.inghub.stock.contract.model.stockexchange.StockExchangeResponse;
import com.inghub.stock.management.repository.entities.StockExchangeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL,
        uses = StockMapper.class)
public interface StockExchangeMapper {
  
  @Mapping(target = "lastUpdate", source = "lastModifiedOn")
  StockExchangeResponse mapEntityToResponse(StockExchangeEntity entity);

}
