package pl.home.ekantor.domain.model;

import lombok.Builder;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Builder(toBuilder = true)
public record AccountBalance(

	@NonNull
	UUID uuid,

	@NonNull
	BigDecimal amount,

	@NonNull
	CurrencyCode currencyCode,

	@NonNull
	Instant createdAt,

	@NonNull
	Optional<Instant> updatedAt,

	@NonNull
	UUID accountUuid

) {
}
