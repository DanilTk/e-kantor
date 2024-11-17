package pl.home.ekantor.web.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record CreateAccountRequestDto(

	@NotBlank
	String name,

	@NotBlank
	String surname,

	@NotNull
	@DecimalMin("0.01")
	BigDecimal depositAmount

) {
}
