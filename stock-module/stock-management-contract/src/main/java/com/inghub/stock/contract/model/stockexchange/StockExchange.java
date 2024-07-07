package com.inghub.stock.contract.model.stockexchange;

import com.inghub.stock.contract.model.stock.Stock;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockExchange {
    @NotBlank(message = "Stock exchange name is mandatory")
    private String name;
    private String description;
    private boolean liveInMarket = false;
    private List<Stock> stocks;
}
