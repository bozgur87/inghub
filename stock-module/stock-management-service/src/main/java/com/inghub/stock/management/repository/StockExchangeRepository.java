package com.inghub.stock.management.repository;

import com.inghub.stock.management.repository.entities.StockExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, String> {

}
