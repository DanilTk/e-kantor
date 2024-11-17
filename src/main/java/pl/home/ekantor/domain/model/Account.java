package pl.home.ekantor.domain.model;

import lombok.Builder;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record Account(

	@NonNull
	UUID uuid,

	@NonNull
	String userName,

	@NonNull
	String userSurname,

	@NonNull
	Instant createdAt

) {
}
