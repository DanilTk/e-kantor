package pl.home.ekantor.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.NonNull;

import java.time.Instant;
import java.util.UUID;

@Builder(toBuilder = true)
public record AccountDetailResponseDto(

	@NonNull
	UUID accountId,

	@NonNull
	String name,

	@NonNull
	String surname,

	@NonNull
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
	Instant createdAt

) {
}
