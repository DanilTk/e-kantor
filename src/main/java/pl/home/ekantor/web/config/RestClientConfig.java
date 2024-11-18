package pl.home.ekantor.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Value("${application.rate-provider-url}")
	private String fxSourceUrl;

	@Bean
	public RestClient restClient() {
		return RestClient.builder()
			.baseUrl(fxSourceUrl)
			.build();
	}
}
