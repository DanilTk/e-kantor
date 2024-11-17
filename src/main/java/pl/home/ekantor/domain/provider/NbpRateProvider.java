package pl.home.ekantor.domain.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pl.home.ekantor.domain.model.CurrencyCode;

import static pl.home.ekantor.domain.provider.RateDetail.FxRate;

@Component
@RequiredArgsConstructor
public class NbpRateProvider implements FxRateProvider {

	private static final String NBP_API_URL = "/api/exchangerates/rates/a/{currency}/?format=json";

	private final RestClient restClient;

	@Override
	public RateDetail getRateDetail(CurrencyCode currencyCode) {

		RateDetail rateDetail = restClient.get()
			.uri(NBP_API_URL, currencyCode.name())
			.retrieve()
			.body(RateDetail.class);

		if (rateDetail.getFxRates().isEmpty()) {
			throw new IllegalStateException("No rates found for currency: " + currencyCode);
		}

		return rateDetail;
	}
}
