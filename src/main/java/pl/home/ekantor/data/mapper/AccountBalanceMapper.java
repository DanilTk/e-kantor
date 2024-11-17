package pl.home.ekantor.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.home.ekantor.data.entity.AccountBalanceEntity;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.domain.command.CreateBalanceCommand;
import pl.home.ekantor.domain.model.AccountBalance;
import pl.home.ekantor.domain.util.TimeProvider;
import pl.home.ekantor.domain.util.UuidProvider;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountBalanceMapper {

	private final UuidProvider uuidProvider;

	private final TimeProvider timeProvider;

	public AccountBalance map(AccountBalanceEntity entity) {
		return AccountBalance.builder()
			.uuid(entity.getId())
			.amount(entity.getBalanceAmount())
			.currencyCode(entity.getCurrencyCode())
			.createdAt(entity.getCreatedAt())
			.updatedAt(Optional.ofNullable(entity.getUpdatedAt()))
			.accountUuid(entity.getAccount().getId())
			.build();
	}

	public AccountBalanceEntity map(CreateBalanceCommand command, AccountEntity accountEntity) {
		return AccountBalanceEntity.builder()
			.id(uuidProvider.generateUuid())
			.balanceAmount(command.amount())
			.currencyCode(command.currencyCode())
			.account(accountEntity)
			.createdAt(timeProvider.getInstant())
			.build();
	}

}
