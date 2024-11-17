package pl.home.ekantor.domain.model;

import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record AccountTransaction(

	@NonNull
	UUID uuid,

	@NonNull
	BigDecimal fxRate,

	@NonNull
	BigDecimal fxBaseAmount,

	@NonNull
	BigDecimal fxQuoteAmount,

	@NonNull
	CurrencyCode baseCurrency,

	@NonNull
	CurrencyCode quoteCurrency,

	@NonNull
	TransactionType transactionType,

	@NonNull
	Instant createdAt,

	@NonNull
	UUID accountUuid

) {
}
