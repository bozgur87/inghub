package com.inghub.stock.management.controller;

import com.inghub.stock.contract.model.stockexchange.StockExchangeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Validated
@Api(value = "Stock Exchange", tags = {"The Stock Exchange API"})
public interface StockExchangeApi {

  @ApiOperation(value = "", nickname = "Get", notes = "Get stock exchange", response = StockExchangeResponse.class,
          tags = {"Stock Exchange"})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS", response = StockExchangeResponse.class)})
  @GetMapping(value = "api/v1/stock-exchange/{name}", produces = {"application/json;charset=utf-8"})
  ResponseEntity<StockExchangeResponse> getStockExchangeByName(@RequestHeader Map<String, String> header,
          @Valid @PathVariable("name") String name);

  @ApiOperation(value = "", nickname = "Modify", notes = "Add stock to stock exchange",
          response = StockExchangeResponse.class, tags = {"Stock Exchange"})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS", response = StockExchangeResponse.class)})
  @PatchMapping(value = "api/v1/stock-exchange/{name}/stock/{id}", produces = {"application/json;charset=utf-8"})
  ResponseEntity<StockExchangeResponse> addStockToStockExchange(@RequestHeader Map<String, String> header,
          @Valid @PathVariable("name") String name, @Valid @PathVariable("id") Long id);

  @ApiOperation(value = "", nickname = "Modify", notes = "Delete stock to stock exchange",
          response = StockExchangeResponse.class, tags = {"Stock Exchange"})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS", response = StockExchangeResponse.class)})
  @DeleteMapping(value = "api/v1/stock-exchange/{name}/stock/{id}", produces = {"application/json;charset=utf-8"})
  ResponseEntity<StockExchangeResponse> deleteStockFromStockExchange(@RequestHeader Map<String, String> header,
          @Valid @PathVariable("name") String name, @Valid @PathVariable("id") Long id);


}
