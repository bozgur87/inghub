package com.inghub.stock.management.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

@EqualsAndHashCode(of = {"id"})
@Data
@Entity
@Table(name = "AUDIT")
public class AuditEntity {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@Column(columnDefinition = "TEXT")
	private String processType;

	@Column(columnDefinition = "TEXT")
	private String payload;

	@CreationTimestamp
	private Instant createdOn;

}
