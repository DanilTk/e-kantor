package pl.home.ekantor.data.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.data.entity.TransactionEntity;
import pl.home.ekantor.data.jpa.AccountRepositoryJpa;
import pl.home.ekantor.data.jpa.TransactionRepositoryJpa;
import pl.home.ekantor.data.mapper.AccountTransactionMapper;
import pl.home.ekantor.domain.command.CreateTransactionCommand;
import pl.home.ekantor.domain.model.AccountTransaction;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

	private final AccountTransactionMapper accountTransactionMapper;

	private final TransactionRepositoryJpa transactionRepositoryJpa;

	private final AccountRepositoryJpa accountRepositoryJpa;

	@Transactional(propagation = MANDATORY)
	public AccountTransaction create(CreateTransactionCommand command) {
		AccountEntity accountEntity = accountRepositoryJpa.getById(command.accountUuid());
		TransactionEntity entity = accountTransactionMapper.map(command, accountEntity);
		entity = transactionRepositoryJpa.save(entity);
		return accountTransactionMapper.map(entity);
	}
}
