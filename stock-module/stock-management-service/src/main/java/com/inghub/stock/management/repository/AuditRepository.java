package com.inghub.stock.management.repository;

import com.inghub.stock.management.repository.entities.AuditEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepository extends JpaRepository<AuditEntity, UUID> {

}
