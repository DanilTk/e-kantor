package pl.home.ekantor.domain.command;

import lombok.Builder;
import lombok.NonNull;
import pl.home.ekantor.domain.model.CurrencyCode;
import pl.home.ekantor.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder(toBuilder = true)
public record CreateTransactionCommand(

	@NonNull
	CurrencyCode baseCurrency,

	@NonNull
	CurrencyCode quoteCurrency,

	@NonNull
	BigDecimal baseAmount,

	@NonNull
	BigDecimal fxAmount,

	@NonNull
	BigDecimal fxRate,

	@NonNull
	LocalDate fxDate,

	@NonNull
	TransactionType transactionType,

	@NonNull
	UUID accountUuid

) {
}
