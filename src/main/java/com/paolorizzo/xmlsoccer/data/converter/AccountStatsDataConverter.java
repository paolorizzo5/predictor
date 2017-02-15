package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.AccountStatsDto;
import com.paolorizzo.predictor.hibernate.model.AccountStats;

public class AccountStatsDataConverter {

	public static List<AccountStatsDto> convert(List<AccountStats> accountStats) {
		List<AccountStatsDto> accountStatsDtos = new ArrayList<AccountStatsDto>();
		for (AccountStats accountStat : accountStats) {
			AccountStatsDto dto = new AccountStatsDto();
			dto.setAmount(accountStat.getAmount());
			dto.setLogDate(accountStat.getLogDate().getTime());
			accountStatsDtos.add(dto);
		}
		return accountStatsDtos;
	}

}
