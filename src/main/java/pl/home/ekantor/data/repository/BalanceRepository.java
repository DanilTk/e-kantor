package pl.home.ekantor.data.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.home.ekantor.data.entity.AccountBalanceEntity;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.data.jpa.AccountRepositoryJpa;
import pl.home.ekantor.data.jpa.BalanceRepositoryJpa;
import pl.home.ekantor.data.mapper.AccountBalanceMapper;
import pl.home.ekantor.domain.command.CreateBalanceCommand;
import pl.home.ekantor.domain.exception.ObjectNotFoundException;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@RequiredArgsConstructor
public class BalanceRepository {

	private final BalanceRepositoryJpa balanceRepositoryJpa;

	private final AccountRepositoryJpa accountRepositoryJpa;

	private final AccountBalanceMapper accountBalanceMapper;

	@Transactional(propagation = MANDATORY)
	public AccountBalance create(CreateBalanceCommand command) {
		AccountEntity accountEntity = accountRepositoryJpa.getById(command.accountUuid());
		AccountBalanceEntity entity = accountBalanceMapper.map(command, accountEntity);
		entity = balanceRepositoryJpa.save(entity);
		return accountBalanceMapper.map(entity);
	}

	public List<AccountBalance> findAllByAccountUuid(UUID uuid) {
		return balanceRepositoryJpa.findAllByAccount_Id(uuid).stream()
			.map(accountBalanceMapper::map)
			.toList();
	}

	public Optional<AccountBalance> findAccountCurrencyBalance(UUID uuid, CurrencyCode currencyCode) {
		return balanceRepositoryJpa.findByCurrencyCodeAndAccount_Id(currencyCode, uuid)
			.map(accountBalanceMapper::map);
	}

	public void deposit(UUID accountUuid, CurrencyCode currencyCode, BigDecimal amount) {
		AccountBalanceEntity accountBalanceEntity = balanceRepositoryJpa.findByCurrencyCodeAndAccount_Id(currencyCode,
				accountUuid)
			.orElseThrow(() -> new ObjectNotFoundException("Balance not found."));
		BigDecimal totalAmount = accountBalanceEntity.getBalanceAmount().add(amount);
		accountBalanceEntity.setBalanceAmount(totalAmount);
	}

	public void withdraw(UUID accountUuid, CurrencyCode currencyCode, BigDecimal amount) {
		AccountBalanceEntity accountBalanceEntity = balanceRepositoryJpa.findByCurrencyCodeAndAccount_Id(currencyCode,
				accountUuid)
			.orElseThrow(() -> new ObjectNotFoundException("Balance not found."));
		BigDecimal totalAmount = accountBalanceEntity.getBalanceAmount().subtract(amount);
		accountBalanceEntity.setBalanceAmount(totalAmount);
	}
}
