package pl.home.ekantor.web.error;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.home.ekantor.domain.exception.InsufficientFundsException;
import pl.home.ekantor.domain.exception.NoBalanceExistsException;
import pl.home.ekantor.domain.exception.ObjectNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final BaseErrorResponseDtoFactory baseErrorResponseDtoFactory;

	@ResponseStatus(INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public BaseErrorResponseDto handleAnyException(Exception exception) {
		return baseErrorResponseDtoFactory.createGenericException(exception);
	}

	@ResponseStatus(NOT_FOUND)
	@ExceptionHandler(ObjectNotFoundException.class)
	public BaseErrorResponseDto handleException(ObjectNotFoundException exception) {
		return baseErrorResponseDtoFactory.createObjectNotFound(exception);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(NoBalanceExistsException.class)
	public BaseErrorResponseDto handleException(NoBalanceExistsException exception) {
		return baseErrorResponseDtoFactory.createBadRequest(exception);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(InsufficientFundsException.class)
	public BaseErrorResponseDto handleException(InsufficientFundsException exception) {
		return baseErrorResponseDtoFactory.createBadRequest(exception);
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public BaseErrorResponseDto handleException() {
		return baseErrorResponseDtoFactory.createInvalidInputError();
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
															 HttpStatusCode statusCode, WebRequest request) {
		ResponseEntity<Object> defaultResponse = super.handleExceptionInternal(
			exception,
			body,
			headers,
			statusCode,
			request
		);
		Object responseBody = createBodyForSpringException(exception);
		return new ResponseEntity<>(responseBody, defaultResponse.getHeaders(), defaultResponse.getStatusCode());
	}

	private Object createBodyForSpringException(Exception exception) {
		if (exception instanceof HttpMessageNotReadableException) {
			return baseErrorResponseDtoFactory.createInvalidInputError();
		}

		return baseErrorResponseDtoFactory.createUnknown();
	}

}
