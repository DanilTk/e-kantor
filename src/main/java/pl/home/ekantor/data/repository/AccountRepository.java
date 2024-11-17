package pl.home.ekantor.data.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.data.jpa.AccountRepositoryJpa;
import pl.home.ekantor.data.mapper.AccountMapper;
import pl.home.ekantor.domain.exception.ObjectNotFoundException;
import pl.home.ekantor.domain.model.Account;
import pl.home.ekantor.web.dto.CreateAccountRequestDto;

import java.util.UUID;

import static org.springframework.transaction.annotation.Propagation.MANDATORY;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

	private final AccountRepositoryJpa accountRepositoryJpa;

	private final AccountMapper accountMapper;

	@Transactional(propagation = MANDATORY)
	public Account create(CreateAccountRequestDto dto) {
		AccountEntity entity = accountMapper.map(dto);
		entity = accountRepositoryJpa.save(entity);
		return accountMapper.map(entity);
	}

	public Account getByUuid(UUID uuid) {
		return accountRepositoryJpa.findById(uuid)
			.map(accountMapper::map)
			.orElseThrow(() -> ObjectNotFoundException.byUUID(uuid, Account.class));
	}
}
