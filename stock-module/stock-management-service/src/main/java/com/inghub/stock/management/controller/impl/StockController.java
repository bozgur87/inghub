package com.inghub.stock.management.controller.impl;

import com.inghub.stock.contract.model.amount.Amount;
import com.inghub.stock.contract.model.stock.StockRequest;
import com.inghub.stock.contract.model.stock.StockResponse;
import com.inghub.stock.management.controller.StockApi;
import com.inghub.stock.management.controller.mapper.StockMapper;
import com.inghub.stock.management.service.StockService;
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
public class StockController implements StockApi {

  private final StockService stockService;
  private final StockMapper stockMapper;

  @Override
  public ResponseEntity<StockResponse> create(Map<String, String> header, @Valid StockRequest request) {
    log.info("Proceeding POST to create stock with request data: [{}]", request);
    return new ResponseEntity<>(
            stockMapper.mapEntityToResponse(stockService.saveStock(stockMapper.mapRequestToEntity(request))),
            HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Void> delete(Map<String, String> header, @Valid Long id) {
    log.info("Proceeding DELETE to delete stock with id: ({})", id);
    stockService.deleteStock(stockMapper.mapIdToEntity(id));
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Override
  public ResponseEntity<StockResponse> update(Map<String, String> header, @Valid Long id, @Valid Amount amount) {
    log.info("Proceeding PATCH to update stock amount with id: ({}) and amount: [{}]", id, amount);
    return new ResponseEntity<>(
            stockMapper.mapEntityToResponse(
                    stockService.updateStock(stockMapper.updateAmountPartially(stockService.findStock(id), amount))),
            HttpStatus.OK);
  }

}
