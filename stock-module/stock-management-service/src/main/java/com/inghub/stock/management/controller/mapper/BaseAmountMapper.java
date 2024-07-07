package com.inghub.stock.management.controller.mapper;

import org.mapstruct.Mapping;

@Mapping(target = "price", source = "amount")
@Mapping(target = "currency", source = "amount.currency")
public @interface BaseAmountMapper {
}
