package pl.home.ekantor.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.NonNull;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record FxOperationResponseDto(

	@NonNull
	UUID transactionId,

	@NonNull
	UUID accountId,

	@NonNull
	CurrencyCode baseCurrency,

	@NonNull
	CurrencyCode quoteCurrency,

	@NonNull
	BigDecimal baseAmount,

	@NonNull
	BigDecimal quoteAmount,

	@NonNull
	BigDecimal exchangeRate,

	@NonNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	Instant createdAt

) {
}
