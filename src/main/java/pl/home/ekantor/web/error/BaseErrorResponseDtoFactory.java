package pl.home.ekantor.web.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.home.ekantor.domain.exception.InsufficientFundsException;
import pl.home.ekantor.domain.exception.NoBalanceExistsException;
import pl.home.ekantor.domain.exception.ObjectNotFoundException;

import static pl.home.ekantor.web.error.BaseErrorType.INTERNAL_SERVER_ERROR;
import static pl.home.ekantor.web.error.BaseErrorType.INVALID_INPUT;
import static pl.home.ekantor.web.error.BaseErrorType.OBJECT_NOT_FOUND;
import static pl.home.ekantor.web.error.BaseErrorType.UNKNOWN_EXCEPTION;

@Slf4j
@Component
class BaseErrorResponseDtoFactory {

	BaseErrorResponseDto createInvalidInputError() {
		log.error("Invalid input");
		return BaseErrorResponseDto.builder()
			.message("Invalid input.")
			.errorType(INVALID_INPUT)
			.build();
	}

	BaseErrorResponseDto createObjectNotFound(ObjectNotFoundException exception) {
		log.error("Object not found", exception);
		return BaseErrorResponseDto.builder()
			.message(exception.getFriendlyMessage())
			.errorType(OBJECT_NOT_FOUND)
			.build();
	}

	BaseErrorResponseDto createUnknown() {
		log.error("Unknown Exception");
		return BaseErrorResponseDto.builder()
			.message("Unknown Exception")
			.errorType(UNKNOWN_EXCEPTION)
			.build();
	}

	BaseErrorResponseDto createGenericException(Exception exception) {
		log.error("Unhandled Exception", exception);
		return BaseErrorResponseDto.builder()
			.message(exception.getMessage())
			.errorType(INTERNAL_SERVER_ERROR)
			.build();
	}

	public BaseErrorResponseDto createBadRequest(NoBalanceExistsException exception) {
		log.error("Bad Request", exception);
		return BaseErrorResponseDto.builder()
			.message(exception.getMessage())
			.errorType(INVALID_INPUT)
			.build();
	}

	public BaseErrorResponseDto createBadRequest(InsufficientFundsException exception) {
		log.error("Bad Request", exception);
		return BaseErrorResponseDto.builder()
			.message(exception.getMessage())
			.errorType(INVALID_INPUT)
			.build();
	}
}
