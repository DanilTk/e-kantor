package pl.home.ekantor.domain.provider;

import pl.home.ekantor.domain.model.CurrencyCode;

public interface FxRateProvider {

	RateDetail getRateDetail(CurrencyCode currencyCode);

}
