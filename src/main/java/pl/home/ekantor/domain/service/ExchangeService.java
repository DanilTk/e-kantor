package pl.home.ekantor.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.home.ekantor.data.repository.AccountRepository;
import pl.home.ekantor.data.repository.TransactionRepository;
import pl.home.ekantor.domain.command.CreateTransactionCommand;
import pl.home.ekantor.domain.exception.InsufficientFundsException;
import pl.home.ekantor.domain.exception.NoBalanceExistsException;
import pl.home.ekantor.domain.model.Account;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.domain.model.AccountTransaction;
import pl.home.ekantor.domain.model.CurrencyCode;
import pl.home.ekantor.domain.provider.FxRateProvider;
import pl.home.ekantor.domain.provider.RateDetail;
import pl.home.ekantor.domain.util.DateUtil;
import pl.home.ekantor.web.dto.ExchangeMoneyRequestDto;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;
import static pl.home.ekantor.domain.model.CurrencyCode.PLN;
import static pl.home.ekantor.domain.model.TransactionType.EXCHANGE;
import static pl.home.ekantor.domain.provider.RateDetail.*;

@Service
@RequiredArgsConstructor
public class ExchangeService {

	private final FxRateProvider fxRateProvider;

	private final BalanceService balanceService;

	private final AccountRepository accountRepository;

	private final TransactionRepository transactionRepository;

	@Transactional(isolation = REPEATABLE_READ)
	public AccountTransaction exchange(UUID accountUuid, ExchangeMoneyRequestDto dto) {
		CurrencyCode conversionCurrency = determineFxCurrency(dto);

		RateDetail rateDetail = fxRateProvider.getRateDetail(conversionCurrency);
		FxRate fxRate = rateDetail.getFxRates().get(0);

		Account account = accountRepository.getByUuid(accountUuid);
		AccountBalance accountBalance = balanceService
			.findAccountCurrencyBalance(account.uuid(), dto.baseCurrency())
			.orElseThrow(() -> new NoBalanceExistsException(dto.baseCurrency()));

		validateBalanceLevel(accountBalance.amount(), dto.amount());
		BigDecimal targetAmount;

		if (dto.baseCurrency().name().equals(rateDetail.getCode().toUpperCase())) {
			targetAmount = dto.amount().multiply(fxRate.getAvgRate());
		} else {
			targetAmount = dto.amount().divide(fxRate.getAvgRate(), MathContext.DECIMAL64);
		}

		balanceService.withdrawBalance(accountUuid, dto.baseCurrency(), dto.amount());

		Optional<AccountBalance> quoteCurrencyBalanceOptional = balanceService
			.findAccountCurrencyBalance(accountUuid, dto.quoteCurrency());
		if (quoteCurrencyBalanceOptional.isEmpty()) {
			balanceService.createCurrencyBalance(dto.quoteCurrency(), targetAmount, accountUuid);
		} else {
			balanceService.depositBalance(accountUuid, dto.quoteCurrency(), targetAmount);
		}

		CreateTransactionCommand command = CreateTransactionCommand.builder()
			.transactionType(EXCHANGE)
			.baseCurrency(dto.baseCurrency())
			.quoteCurrency(dto.quoteCurrency())
			.fxRate(fxRate.getAvgRate())
			.fxDate(DateUtil.convert(fxRate.getEffectiveDate()))
			.baseAmount(dto.amount())
			.fxAmount(targetAmount)
			.accountUuid(accountUuid)
			.build();

		return transactionRepository.create(command);
	}

	private static CurrencyCode determineFxCurrency(ExchangeMoneyRequestDto dto) {
		CurrencyCode conversionCurrency;
		if (dto.baseCurrency() == PLN) {
			conversionCurrency = dto.quoteCurrency();
		} else {
			conversionCurrency = dto.baseCurrency();
		}
		return conversionCurrency;
	}

	private void validateBalanceLevel(BigDecimal balanceAmount, BigDecimal exchangeAmount) {
		if (balanceAmount.compareTo(exchangeAmount) < 0) {
			throw new InsufficientFundsException();
		}

	}

}
