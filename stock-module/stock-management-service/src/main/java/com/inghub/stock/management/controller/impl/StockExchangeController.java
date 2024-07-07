package com.inghub.stock.management.controller.impl;

import com.inghub.stock.contract.model.stockexchange.StockExchangeResponse;
import com.inghub.stock.management.controller.StockExchangeApi;
import com.inghub.stock.management.controller.mapper.StockExchangeMapper;
import com.inghub.stock.management.service.StockExchangeService;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StockExchangeController implements StockExchangeApi {

  private final StockExchangeService stockExchangeService;
  private final StockExchangeMapper stockExchangeMapper;

  @Override
  public ResponseEntity<StockExchangeResponse> getStockExchangeByName(Map<String, String> header, @Valid String name) {
    log.info("Proceeding GET to list stock exchange with name: ({})", name);
    return new ResponseEntity<>(stockExchangeMapper.mapEntityToResponse(stockExchangeService.findStockExchange(name)),
            HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StockExchangeResponse> addStockToStockExchange(Map<String, String> header, @Valid String name,
          @Valid Long id) {
    log.info("Proceeding PATCH to add the stock into stock exchange with name: ({}), id: ({})", name, id);
    return new ResponseEntity<>(stockExchangeMapper.mapEntityToResponse(
            stockExchangeService.addStock(stockExchangeService.findStockExchange(name), id)), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<StockExchangeResponse> deleteStockFromStockExchange(Map<String, String> header,
          @Valid String name, @Valid Long id) {
    log.info("Proceeding DELETE to delete the stock from stock exchange with name: ({}), id: ({})", name, id);
    return new ResponseEntity<>(stockExchangeMapper.mapEntityToResponse(
            stockExchangeService.deleteStock(stockExchangeService.findStockExchange(name), id)), HttpStatus.OK);
  }

}
