package com.inghub.stock.contract.model.stock;

import com.inghub.stock.contract.model.amount.Amount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Stock {
    @NotBlank(message = "Stock name is mandatory")
    private String name;
    private String description;
    @NotNull(message = "Amount is mandatory")
    private Amount amount;
}
