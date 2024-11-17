package pl.home.ekantor.data.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.home.ekantor.data.entity.AccountEntity;
import pl.home.ekantor.domain.exception.ObjectNotFoundException;

import java.util.UUID;

public interface AccountRepositoryJpa extends CrudRepository<AccountEntity, UUID> {

	default AccountEntity getById(UUID uuid) {
		return findById(uuid).orElseThrow(() -> ObjectNotFoundException.byUUID(uuid, AccountEntity.class));
	}
}
