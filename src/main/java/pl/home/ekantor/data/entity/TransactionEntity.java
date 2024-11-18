package pl.home.ekantor.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.home.ekantor.domain.model.CurrencyCode;
import pl.home.ekantor.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class TransactionEntity {

	@Id
	@Column(nullable = false)
	@NonNull
	private UUID id;

	@NonNull
	@Enumerated(STRING)
	@Column(name = "base_currency", nullable = false, length = 3)
	private CurrencyCode baseCurrency;

	@NonNull
	@Enumerated(STRING)
	@Column(name = "quote_currency", nullable = false, length = 3)
	private CurrencyCode quoteCurrency;

	@NonNull
	@Enumerated(STRING)
	private TransactionType transactionType;

	@NonNull
	@Column(name = "fx_rate", nullable = false)
	private BigDecimal fxRate;

	@NonNull
	@Column(name = "fx_date", nullable = false)
	private LocalDate fxDate;

	@NonNull
	@Column(name = "fx_base_amount", nullable = false)
	private BigDecimal fxBaseAmount;

	@NonNull
	@Column(name = "fx_quote_amount", nullable = false)
	private BigDecimal fxQuoteAmount;

	@NonNull
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;

}


