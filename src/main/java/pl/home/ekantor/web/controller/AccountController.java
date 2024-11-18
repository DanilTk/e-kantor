package pl.home.ekantor.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.home.ekantor.web.dto.AccountBalanceResponseDto;
import pl.home.ekantor.web.dto.AccountDetailResponseDto;
import pl.home.ekantor.web.dto.CreateAccountRequestDto;
import pl.home.ekantor.web.handler.AccountHandler;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

	private final AccountHandler handler;

	@PostMapping
	public AccountDetailResponseDto createAccount(@Valid CreateAccountRequestDto dto) {
		return handler.createAccount(dto);
	}

	@GetMapping("/{accountId}")
	public AccountDetailResponseDto getAccountDetail(@PathVariable UUID accountId) {
		return handler.getAccountDetail(accountId);
	}

	@GetMapping("/{accountId}/balances")
	public AccountBalanceResponseDto getAccountBalance(@PathVariable UUID accountId) {
		return handler.findAccountBalances(accountId);
	}

}
