package pl.home.ekantor.web.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.home.ekantor.domain.model.Account;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.domain.service.AccountService;
import pl.home.ekantor.web.dto.AccountBalanceResponseDto;
import pl.home.ekantor.web.dto.AccountDetailResponseDto;
import pl.home.ekantor.web.dto.CreateAccountRequestDto;
import pl.home.ekantor.web.mapper.AccountDtoMapper;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountHandler {

	private final AccountService accountService;

	public AccountDetailResponseDto createAccount(CreateAccountRequestDto dto) {
		Account account = accountService.createAccount(dto);
		return AccountDtoMapper.map(account);
	}

	public AccountDetailResponseDto getAccountDetail(UUID accountId) {
		Account account = accountService.getAccountDetails(accountId);
		return AccountDtoMapper.map(account);
	}

	public AccountBalanceResponseDto findAccountBalances(UUID accountId) {
		List<AccountBalance> accountBalances = accountService.findAccountBalances(accountId);
		return AccountDtoMapper.map(accountBalances);
	}
}
