package com.paolorizzo.predictor.services.response.dto;

public class DataCollectorDto {
	
	private Integer success;
	private Integer total;
	
	public DataCollectorDto() {
		// TODO Auto-generated constructor stub
	}

	public DataCollectorDto(Integer success, Integer total) {
		super();
		this.success = success;
		this.total = total;
	}

	public Integer getSuccess() {
		return success;
	}

	public void setSuccess(Integer success) {
		this.success = success;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	

}
