package pl.home.ekantor.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.home.ekantor.data.repository.AccountRepository;
import pl.home.ekantor.data.repository.TransactionRepository;
import pl.home.ekantor.domain.command.CreateTransactionCommand;
import pl.home.ekantor.domain.model.Account;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.domain.model.CurrencyCode;
import pl.home.ekantor.web.dto.CreateAccountRequestDto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static pl.home.ekantor.domain.model.CurrencyCode.PLN;
import static pl.home.ekantor.domain.model.TransactionType.DEPOSIT;

@Service
@RequiredArgsConstructor
public class AccountService {

	private static final BigDecimal NO_CONVERSION_FX_RATE = BigDecimal.ONE;

	private static final CurrencyCode DEFAULT_CURRENCY = PLN;

	private static final LocalDate DEFAULT_FX_DATE = LocalDate.now();

	private final BalanceService balanceService;

	private final AccountRepository accountRepository;

	private final TransactionRepository transactionRepository;

	@Transactional
	public Account createAccount(CreateAccountRequestDto dto) {
		Account account = accountRepository.create(dto);
		depositInitialAmount(dto.depositAmount(), account.uuid());
		return account;
	}

	public Account getAccountDetails(UUID accountUuid) {
		return accountRepository.getByUuid(accountUuid);
	}

	public List<AccountBalance> findAccountBalances(UUID accountUuid) {
		return balanceService.findAccountBalances(accountUuid);
	}

	private void depositInitialAmount(BigDecimal depositAmount, UUID accountUuid) {
		balanceService.createCurrencyBalance(DEFAULT_CURRENCY, depositAmount, accountUuid);

		CreateTransactionCommand command = CreateTransactionCommand.builder()
			.transactionType(DEPOSIT)
			.baseCurrency(DEFAULT_CURRENCY)
			.quoteCurrency(DEFAULT_CURRENCY)
			.fxRate(NO_CONVERSION_FX_RATE)
			.fxDate(DEFAULT_FX_DATE)
			.baseAmount(depositAmount)
			.fxAmount(depositAmount)
			.accountUuid(accountUuid)
			.build();

		transactionRepository.create(command);
	}

}
