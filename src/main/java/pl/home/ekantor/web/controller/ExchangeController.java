package pl.home.ekantor.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.home.ekantor.web.dto.ExchangeMoneyRequestDto;
import pl.home.ekantor.web.dto.FxOperationResponseDto;
import pl.home.ekantor.web.handler.ExchangeHandler;

import java.util.UUID;

@RestController
@RequestMapping("/api/exchange")
@RequiredArgsConstructor
public class ExchangeController {

	private final ExchangeHandler handler;

	@PostMapping("/{accountId}")
	public FxOperationResponseDto exchange(@PathVariable UUID accountId,
										   @Valid @RequestBody ExchangeMoneyRequestDto dto) {
		return handler.exchange(accountId, dto);
	}

}