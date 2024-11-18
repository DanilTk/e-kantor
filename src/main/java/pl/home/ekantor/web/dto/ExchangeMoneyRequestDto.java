package pl.home.ekantor.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record ExchangeMoneyRequestDto(

	@NotNull
	CurrencyCode baseCurrency,

	@NotNull
	CurrencyCode quoteCurrency,

	@NotNull
	@DecimalMin("0.01")
	BigDecimal amount

) {
}
