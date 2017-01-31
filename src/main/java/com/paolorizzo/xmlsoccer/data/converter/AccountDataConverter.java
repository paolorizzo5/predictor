package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.AccountDto;
import com.paolorizzo.predictor.hibernate.model.Account;

public class AccountDataConverter {

	public static List<AccountDto> convert(List<Account> accounts) {
		List<AccountDto>  list = new ArrayList<AccountDto>();
		for (Account account : accounts) {
			AccountDto accountDto = new AccountDto();
			accountDto.setDescription(account.getDescription());
			accountDto.setInsertDate(account.getInsertDate().getTime());
			accountDto.setMoneyTransactionDtos(MoneyTransactionConverter.convert(account.getMoneyTransactions()));
			accountDto.setName(account.getName());
			accountDto.setBets(BetDataConverter.convert(account.getBets()));
			if(account.getProspect() != null){
				accountDto.setProspect(ProspectDataConverter.convert(account.getProspect()));
			}
			list.add(accountDto);
		}
		
		return list;
	}

	public static AccountDto convert(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setDescription(account.getDescription());
		accountDto.setInsertDate(account.getInsertDate().getTime());
		accountDto.setMoneyTransactionDtos(MoneyTransactionConverter.convert(account.getMoneyTransactions()));
		accountDto.setName(account.getName());
		accountDto.setBets(BetDataConverter.convert(account.getBets()));
		if(account.getProspect() != null){
			accountDto.setProspect(ProspectDataConverter.convert(account.getProspect()));
		}
		return accountDto;
	}

}
