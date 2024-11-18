package pl.home.ekantor.domain.provider;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateDetail {

	private String code;

	@JsonProperty("rates")
	private List<FxRate> fxRates;

	@Getter
	@Setter
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class FxRate {

		private String effectiveDate;

		@JsonProperty("mid")
		private BigDecimal avgRate;

	}

}
