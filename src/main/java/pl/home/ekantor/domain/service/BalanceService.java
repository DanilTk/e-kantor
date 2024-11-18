package pl.home.ekantor.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.home.ekantor.data.repository.BalanceRepository;
import pl.home.ekantor.domain.command.CreateBalanceCommand;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BalanceService {

	private final BalanceRepository balanceRepository;

	public List<AccountBalance> findAccountBalances(UUID accountUuid) {
		return balanceRepository.findAllByAccountUuid(accountUuid);
	}

	public AccountBalance createCurrencyBalance(CurrencyCode currency, BigDecimal depositAmount, UUID accountUuid) {
		CreateBalanceCommand command = CreateBalanceCommand.builder()
			.accountUuid(accountUuid)
			.currencyCode(currency)
			.amount(depositAmount)
			.build();

		return balanceRepository.create(command);
	}

	public Optional<AccountBalance> findAccountCurrencyBalance(UUID accountUuid, CurrencyCode currencyCode) {
		return balanceRepository.findAccountCurrencyBalance(accountUuid, currencyCode);
	}

	public void depositBalance(UUID accountUuid, CurrencyCode currencyCode, BigDecimal targetAmount) {
		balanceRepository.deposit(accountUuid, currencyCode, targetAmount);
	}

	public void withdrawBalance(UUID accountUuid, CurrencyCode currencyCode, BigDecimal amount) {
		balanceRepository.withdraw(accountUuid, currencyCode, amount);
	}
}
