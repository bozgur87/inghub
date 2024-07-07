package com.inghub.stock.management.repository;

import com.inghub.stock.management.repository.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {

}
