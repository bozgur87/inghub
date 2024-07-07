package com.inghub.stock.management.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@EqualsAndHashCode(of = {"id"})
@Data
@Entity
@Table(name = "STOCK")
@RequiredArgsConstructor
public class StockEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;

	private String description;

	private String currency;

	private BigDecimal price;

	@CreationTimestamp
	@Column(updatable = false)
	private Instant createdOn;

	@UpdateTimestamp
	private Instant lastModifiedOn;

}
