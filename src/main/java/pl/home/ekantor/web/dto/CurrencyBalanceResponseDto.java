package pl.home.ekantor.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.NonNull;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record CurrencyBalanceResponseDto(

	@NonNull
	CurrencyCode currencyCode,

	@NonNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "0.00")
	BigDecimal amount

) {
}
