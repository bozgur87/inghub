package com.inghub.stock.management.service;

import com.inghub.stock.management.repository.AuditRepository;
import com.inghub.stock.management.repository.entities.AuditEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditService {

  private final AuditRepository auditRepository;

  @SneakyThrows
  public void saveAudit(AuditEntity auditEntity) {
    auditRepository.save(auditEntity);
  }
}
