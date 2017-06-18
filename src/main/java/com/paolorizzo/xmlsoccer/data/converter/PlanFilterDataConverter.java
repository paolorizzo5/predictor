package com.paolorizzo.xmlsoccer.data.converter;

import java.util.ArrayList;
import java.util.List;

import com.paolorizzo.predictor.hibernate.model.MasanielloPlan;
import com.paolorizzo.predictor.hibernate.model.PlanFilter;
import com.paolorizzo.predictor.services.request.PlanFilterDto;

public class PlanFilterDataConverter {

	public static PlanFilter convert(PlanFilterDto filters,Integer step,MasanielloPlan plan) {
		PlanFilter filter = new PlanFilter();
		filter.setAmount(filters.getAmount());
		filter.setAwayTeam(filters.getAwayTeam());
		filter.setBetType(filters.getBetType());
		filter.setCompetition(filters.getCompetition());
		filter.setDateFrom(filters.getDateFrom());
		filter.setDateTo(filters.getDateTo());
		filter.setHomeTeam(filters.getHomeTeam());
		filter.setQuota1From(filters.getQuota1From());
		filter.setQuotaXFrom(filters.getQuotaXFrom());
		filter.setQuota2From(filters.getQuota2From());
		
		filter.setQuota1To(filters.getQuota1To());
		filter.setQuotaXTo(filters.getQuotaXTo());
		filter.setQuota2To(filters.getQuota2To());
		filter.setStep(step);
		filter.setMasanielloPlan(plan);
		
		// TODO Auto-generated method stub
		return filter;
	}

	public static List<PlanFilter> convert(ArrayList<PlanFilterDto> filters,MasanielloPlan plan) {
		List<PlanFilter> planFilters = new ArrayList<PlanFilter>();
		int count = 1;
		for (PlanFilterDto dto : filters) {
			planFilters.add(convert(dto,count,plan));
			count = count + 1;
			
		}
		return planFilters;
	}

	public static List<PlanFilterDto> convert(List<PlanFilter> planFilters) {
		List<PlanFilterDto> dtos = new ArrayList<PlanFilterDto>();
		
		for (PlanFilter filter : planFilters) {
			dtos.add(convert(filter));
		}
		
		return dtos;
	}

	private static PlanFilterDto convert(PlanFilter data) {
		PlanFilterDto filter = new PlanFilterDto();
		filter.setAmount(data.getAmount());
		filter.setAwayTeam(data.getAwayTeam());
		filter.setBetType(data.getBetType());
		filter.setCompetition(data.getCompetition());
		filter.setDateFrom(data.getDateFrom());
		filter.setDateTo(data.getDateTo());
		filter.setHomeTeam(data.getHomeTeam());
		filter.setQuota1From(data.getQuota1From());
		filter.setQuotaXFrom(data.getQuotaXFrom());
		filter.setQuota2From(data.getQuota2From());
		
		filter.setQuota1To(data.getQuota1To());
		filter.setQuotaXTo(data.getQuotaXTo());
		filter.setQuota2To(data.getQuota2To());
		filter.setStep(data.getStep());
		
		// TODO Auto-generated method stub
		return filter;
	}

}
