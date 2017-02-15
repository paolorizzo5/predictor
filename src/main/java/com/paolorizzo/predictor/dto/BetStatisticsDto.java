package com.paolorizzo.predictor.dto;

import java.util.List;

import com.paolorizzo.predictor.dto.charts.DonutDataChart;

public class BetStatisticsDto {
	
	private Integer count;
	
	private Integer win;
	
	private Integer lost;
	
	private Integer placed;
	
	private List<DonutDataChart> betDonutStats;

	public BetStatisticsDto() {
		this.count = 0;
		this.win = 0;
		this.lost = 0;
		this.placed = 0;
	}
	
	public BetStatisticsDto(Integer count, Integer win, Integer lost, Integer placed) {
		super();
		this.count = count;
		this.win = win;
		this.lost = lost;
		this.placed = placed;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public Integer getLost() {
		return lost;
	}

	public void setLost(Integer lost) {
		this.lost = lost;
	}

	public Integer getPlaced() {
		return placed;
	}

	public void setPlaced(Integer placed) {
		this.placed = placed;
	}

	public List<DonutDataChart> getBetDonutStats() {
		return betDonutStats;
	}

	public void setBetDonutStats(List<DonutDataChart> betDonutStats) {
		this.betDonutStats = betDonutStats;
	}
	
	
	

}
