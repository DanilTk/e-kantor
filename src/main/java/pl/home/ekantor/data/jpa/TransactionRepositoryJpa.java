package pl.home.ekantor.data.jpa;

import org.springframework.data.repository.CrudRepository;
import pl.home.ekantor.data.entity.TransactionEntity;

import java.util.UUID;

public interface TransactionRepositoryJpa extends CrudRepository<TransactionEntity, UUID> {
}
