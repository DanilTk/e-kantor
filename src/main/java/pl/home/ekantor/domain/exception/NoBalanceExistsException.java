package pl.home.ekantor.domain.exception;

import pl.home.ekantor.domain.model.CurrencyCode;

public class NoBalanceExistsException extends RuntimeException {

	public NoBalanceExistsException(CurrencyCode currencyCode) {
		super("No balance exists for currency: " + currencyCode);
	}
}
