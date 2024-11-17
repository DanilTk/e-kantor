package pl.home.ekantor.web.mapper;

import pl.home.ekantor.domain.model.AccountTransaction;
import pl.home.ekantor.web.dto.FxOperationResponseDto;

public class TransactionDtoMapper {

	public static FxOperationResponseDto map(AccountTransaction transaction) {
		return FxOperationResponseDto.builder()
			.transactionId(transaction.uuid())
			.accountId(transaction.accountUuid())
			.baseCurrency(transaction.baseCurrency())
			.quoteCurrency(transaction.quoteCurrency())
			.baseAmount(transaction.fxBaseAmount())
			.quoteAmount(transaction.fxQuoteAmount())
			.exchangeRate(transaction.fxRate())
			.createdAt(transaction.createdAt())
			.build();
	}
}
