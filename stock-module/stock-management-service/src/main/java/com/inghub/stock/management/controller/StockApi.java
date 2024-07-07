package com.inghub.stock.management.controller;

import com.inghub.stock.contract.model.amount.Amount;
import com.inghub.stock.contract.model.stock.StockRequest;
import com.inghub.stock.contract.model.stock.StockResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Validated
@Api(value = "Stock", tags = {"The Stock API"})
public interface StockApi {

    @ApiOperation(value = "", nickname = "create", notes = "Create stock", response = StockResponse.class,
                    tags = {"Stock"})
    @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED", response = StockResponse.class)})
    @PostMapping(value = "api/v1/stock", produces = {"application/json;charset=utf-8"})
    ResponseEntity<StockResponse> create(@RequestHeader Map<String, String> header,
                    @Valid @RequestBody StockRequest request);

    @ApiOperation(value = "", nickname = "delete", notes = "Delete stock", response = Void.class, tags = {"Stock"})
    @ApiResponses(value = {@ApiResponse(code = 204, message = "NO CONTENT", response = Void.class)})
    @DeleteMapping(value = "api/v1/stock/{id}", produces = {"application/json;charset=utf-8"})
    ResponseEntity<Void> delete(@RequestHeader Map<String, String> header, @Valid @PathVariable("id") Long id);

    @ApiOperation(value = "", nickname = "update", notes = "Update stock amount", response = Void.class,
                    tags = {"Stock"})
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS", response = StockResponse.class)})
    @PatchMapping(value = "api/v1/stock/{id}/amount", produces = {"application/json;charset=utf-8"})
    ResponseEntity<StockResponse> update(@RequestHeader Map<String, String> header, @Valid @PathVariable("id") Long id,
                    @Valid @RequestBody Amount amount);

}
