package com.paolorizzo.xmlsoccer.data.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.dto.AccountDto;
import com.paolorizzo.predictor.dto.BetStatisticsDto;
import com.paolorizzo.predictor.dto.charts.DonutDataChart;
import com.paolorizzo.predictor.enums.BetStatus;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.Bet;

public class AccountDataConverter {

	public static List<AccountDto> convert(List<Account> accounts) {
		List<AccountDto>  list = new ArrayList<AccountDto>();
		for (Account account : accounts) {
			list.add(convert(account));
		}
		
		return list;
	}

	public static AccountDto convert(Account account) {
		AccountDto accountDto = new AccountDto();
		accountDto.setDescription(account.getDescription());
		accountDto.setInsertDate(account.getInsertDate().getTime());
		accountDto.setMoneyTransactionDtos(MoneyTransactionConverter.convert(account.getMoneyTransactions()));
		accountDto.setName(account.getName());
		accountDto.setLiveAmount(account.getLiveAmount());
		accountDto.setBets(BetDataConverter.convert(account.getBets()));
		if(account.getProspect() != null){
			accountDto.setProspect(ProspectDataConverter.convert(account.getProspect(),account.getLiveAmount()));
		}
		
		BetStatisticsDto betStatisticsDto = new BetStatisticsDto();
		
		for (Bet bet : account.getBets()) {
			if(BetStatus.ARCHIVED_LOST.name().equals(bet.getBetStatus()) || BetStatus.LOSING.name().equals(bet.getBetStatus())){
				betStatisticsDto.setLost(betStatisticsDto.getLost() + 1);
			}else if(BetStatus.ARCHIVED_WIN.name().equals(bet.getBetStatus()) || BetStatus.WINNING.name().equals(bet.getBetStatus())){
				betStatisticsDto.setWin(betStatisticsDto.getWin() + 1);
			}else if(BetStatus.PLACED.name().equals(bet.getBetStatus())){
				betStatisticsDto.setPlaced(betStatisticsDto.getPlaced() + 1);
			}
			betStatisticsDto.setCount(betStatisticsDto.getCount() + 1);
		}
		
		List<DonutDataChart> donutDataCharts = new ArrayList<>();
		if(betStatisticsDto.getWin() > 0){
			donutDataCharts.add(new DonutDataChart(BetStatus.ARCHIVED_WIN.name(),betStatisticsDto.getWin()));
		}
		if(betStatisticsDto.getLost() > 0){
			donutDataCharts.add(new DonutDataChart(BetStatus.ARCHIVED_LOST.name(),betStatisticsDto.getLost()));
		}
		if(betStatisticsDto.getPlaced() > 0){
			donutDataCharts.add(new DonutDataChart(BetStatus.PLACED.name(),betStatisticsDto.getPlaced()));
		}
		betStatisticsDto.setBetDonutStats(donutDataCharts);
		
		accountDto.setBetStatistics(betStatisticsDto);
		accountDto.setAccountStats(AccountStatsDataConverter.convert(account.getAccountStats()));
		return accountDto;
	}

}
