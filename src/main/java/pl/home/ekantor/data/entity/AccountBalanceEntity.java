package pl.home.ekantor.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;
import pl.home.ekantor.domain.model.CurrencyCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_balance")
public class AccountBalanceEntity {

	@Id
	@Column(nullable = false)
	@NonNull
	private UUID id;

	@NonNull
	@Column(name = "balance_amount", nullable = false)
	private BigDecimal balanceAmount;

	@NonNull
	@Enumerated(STRING)
	@Column(name = "currency", nullable = false, length = 3)
	private CurrencyCode currencyCode;

	@NonNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "account_id", nullable = false)
	private AccountEntity account;

	@Version
	@Column(name = "version", nullable = false)
	private Integer version;

	@NonNull
	@CreationTimestamp
	@Column(name = "created_at", nullable = false)
	private Instant createdAt;

	@Nullable
	@UpdateTimestamp
	@Column(name = "updated_at")
	private Instant updatedAt;

}
