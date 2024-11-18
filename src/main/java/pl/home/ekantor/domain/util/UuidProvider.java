package pl.home.ekantor.domain.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidProvider {

	public UUID generateUuid() {
		return UUID.randomUUID();
	}
}
