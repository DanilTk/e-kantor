package pl.home.ekantor.web.mapper;

import pl.home.ekantor.domain.model.Account;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.web.dto.AccountBalanceResponseDto;
import pl.home.ekantor.web.dto.AccountDetailResponseDto;
import pl.home.ekantor.web.dto.CurrencyBalanceResponseDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class AccountDtoMapper {

	public static AccountDetailResponseDto map(Account account) {
		return AccountDetailResponseDto.builder()
			.accountId(account.uuid())
			.name(account.userName())
			.surname(account.userSurname())
			.createdAt(account.createdAt())
			.build();
	}

	public static AccountBalanceResponseDto map(List<AccountBalance> balances) {
		if (balances.isEmpty()) {
			throw new IllegalArgumentException("Balances list cannot be empty");
		}

		UUID accountId = balances.get(0).accountUuid();

		Set<CurrencyBalanceResponseDto> currencyBalances = balances.stream()
			.map($ -> map($))
			.collect(Collectors.toCollection(HashSet::new));

		return AccountBalanceResponseDto.builder()
			.accountId(accountId)
			.balances(currencyBalances)
			.build();
	}

	private static CurrencyBalanceResponseDto map(AccountBalance accountBalance) {
		return CurrencyBalanceResponseDto.builder()
			.currencyCode(accountBalance.currencyCode())
			.amount(accountBalance.amount())
			.build();
	}
}
