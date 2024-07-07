package com.inghub.stock.management.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import static java.util.Objects.nonNull;

@EqualsAndHashCode(of = {"name"})
@Data
@Entity
@Table(name = "STOCK_EXCHANGE")
@RequiredArgsConstructor
@AllArgsConstructor
public class StockExchangeEntity {

	@Id
	private String name;

	private String description;

	private Boolean liveInMarket;

	@CreationTimestamp
	@Column(updatable = false)
	private Instant createdOn;

	@UpdateTimestamp
	private Instant lastModifiedOn;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "EXCHANGE_NAME", referencedColumnName = "NAME", nullable = true)
	private List<StockEntity> stocks;

	public StockExchangeEntity addStock(final StockEntity stock) {
		if (nonNull(stocks)) {
			stocks.add(stock);
			if (stocks.size() >= 5) {
				liveInMarket = Boolean.TRUE;
			}
		}
		return this;
	}

	public StockExchangeEntity deleteStock(final StockEntity stock) {
		if (nonNull(stocks)) {
			stocks.remove(stock);
			if (stocks.size() < 5) {
				liveInMarket = Boolean.FALSE;
			}
		}
		return this;
	}

}
