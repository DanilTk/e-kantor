package pl.home.ekantor.web.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.NonNull;

@Builder(toBuilder = true)
public record BaseErrorResponseDto(

	@Schema(required = true)
	@NonNull
	String message,

	@Schema(required = true)
	@NonNull
	BaseErrorType errorType

) {

}
