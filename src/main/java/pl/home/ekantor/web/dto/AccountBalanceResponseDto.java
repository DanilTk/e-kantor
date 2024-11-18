package pl.home.ekantor.web.dto;

import lombok.Builder;
import lombok.NonNull;

import java.util.Set;
import java.util.UUID;

@Builder(toBuilder = true)
public record AccountBalanceResponseDto(

	@NonNull
	UUID accountId,

	@NonNull
	Set<CurrencyBalanceResponseDto> balances

) {
}
