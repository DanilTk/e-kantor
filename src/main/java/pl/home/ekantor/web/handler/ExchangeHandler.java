package pl.home.ekantor.web.handler;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.home.ekantor.domain.model.AccountTransaction;
import pl.home.ekantor.domain.service.ExchangeService;
import pl.home.ekantor.web.dto.ExchangeMoneyRequestDto;
import pl.home.ekantor.web.dto.FxOperationResponseDto;
import pl.home.ekantor.web.mapper.TransactionDtoMapper;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ExchangeHandler {

	private final ExchangeService exchangeService;

	public FxOperationResponseDto exchange(UUID accountId, @Valid ExchangeMoneyRequestDto dto) {
		AccountTransaction accountTransaction = exchangeService.exchange(accountId, dto);
		return TransactionDtoMapper.map(accountTransaction);
	}
}
