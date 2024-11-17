package pl.home.ekantor.domain.command;

import lombok.Builder;
import lombok.NonNull;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(toBuilder = true)
public record CreateBalanceCommand(

	@NonNull
	UUID accountUuid,

	@NonNull
	BigDecimal amount,

	@NonNull
	CurrencyCode currencyCode

) {
}
