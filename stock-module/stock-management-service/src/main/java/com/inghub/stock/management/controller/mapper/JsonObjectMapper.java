package com.inghub.stock.management.controller.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inghub.stock.contract.model.stock.Stock;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonObjectMapper {

  private final ObjectMapper objectMapper;

  @Named("jsonConverter")
  @SneakyThrows
  public String mapBookingToJson(Stock dto) {
    return objectMapper.writeValueAsString(dto);
  }

}
