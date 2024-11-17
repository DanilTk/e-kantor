package pl.home.ekantor.data.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.domain.model.Account;
import pl.home.ekantor.domain.util.TimeProvider;
import pl.home.ekantor.domain.util.UuidProvider;
import pl.home.ekantor.web.dto.CreateAccountRequestDto;

@Component
@RequiredArgsConstructor
public class AccountMapper {

	private final UuidProvider uuidProvider;

	private final TimeProvider timeProvider;

	public Account map(AccountEntity entity) {
		return Account.builder()
			.uuid(entity.getId())
			.userName(entity.getName())
			.userSurname(entity.getSurname())
			.createdAt(entity.getCreatedAt())
			.build();
	}

	public AccountEntity map(CreateAccountRequestDto dto) {
		return AccountEntity.builder()
			.id(uuidProvider.generateUuid())
			.name(dto.name())
			.surname(dto.surname())
			.createdAt(timeProvider.getInstant())
			.build();
	}
}
