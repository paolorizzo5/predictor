package com.paolorizzo.xmlsoccer.data.converter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.MoneyTransactionDto;
import com.paolorizzo.predictor.hibernate.model.MoneyTransaction;

public class MoneyTransactionConverter {
	
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	public static List<MoneyTransactionDto> convert(List<MoneyTransaction> moneyTransactions) {
		List<MoneyTransactionDto> moneyTransactionDtos = new ArrayList<MoneyTransactionDto>();
		for (MoneyTransaction moneyTransaction : moneyTransactions) {
			MoneyTransactionDto dto = new MoneyTransactionDto();
			dto.setAmount(moneyTransaction.getAmount());
			dto.setDirection(moneyTransaction.getDirection());
			dto.setInsertDate(simpleDateFormat.format(moneyTransaction.getInsertDate()));
			moneyTransactionDtos.add(dto);
		}
		return moneyTransactionDtos;
	}

}
