package pl.home.ekantor.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.data.entity.TransactionEntity;
import pl.home.ekantor.domain.command.CreateTransactionCommand;
import pl.home.ekantor.domain.model.AccountTransaction;
import pl.home.ekantor.domain.util.TimeProvider;
import pl.home.ekantor.domain.util.UuidProvider;

@Component
@RequiredArgsConstructor
public class AccountTransactionMapper {

	private final UuidProvider uuidProvider;

	private final TimeProvider timeProvider;

	public AccountTransaction map(TransactionEntity entity) {
		return AccountTransaction.builder()
			.uuid(entity.getId())
			.fxRate(entity.getFxRate())
			.fxBaseAmount(entity.getFxBaseAmount())
			.fxQuoteAmount(entity.getFxQuoteAmount())
			.baseCurrency(entity.getBaseCurrency())
			.quoteCurrency(entity.getQuoteCurrency())
			.transactionType(entity.getTransactionType())
			.createdAt(entity.getCreatedAt())
			.accountUuid(entity.getAccount().getId())
			.build();
	}

	public TransactionEntity map(CreateTransactionCommand command, AccountEntity accountEntity) {
		return TransactionEntity.builder()
			.id(uuidProvider.generateUuid())
			.baseCurrency(command.baseCurrency())
			.quoteCurrency(command.quoteCurrency())
			.fxBaseAmount(command.baseAmount())
			.fxQuoteAmount(command.fxAmount())
			.fxDate(command.fxDate())
			.fxRate(command.fxRate())
			.transactionType(command.transactionType())
			.account(accountEntity)
			.createdAt(timeProvider.getInstant())
			.build();
	}

}
