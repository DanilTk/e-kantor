package pl.home.ekantor.domain.exception;

public class InsufficientFundsException extends RuntimeException {

	public InsufficientFundsException() {
		super("Insufficient funds");
	}
}
