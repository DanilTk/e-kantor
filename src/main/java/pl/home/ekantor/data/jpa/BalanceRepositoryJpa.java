package pl.home.ekantor.data.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.home.ekantor.data.entity.AccountBalanceEntity;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BalanceRepositoryJpa extends CrudRepository<AccountBalanceEntity, UUID> {

	List<AccountBalanceEntity> findAllByAccount_Id(UUID uuid);

	Optional<AccountBalanceEntity> findByCurrencyCodeAndAccount_Id(CurrencyCode currencyCode, UUID uuid);

}
