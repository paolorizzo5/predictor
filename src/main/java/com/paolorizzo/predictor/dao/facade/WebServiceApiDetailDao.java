package com.paolorizzo.predictor.dao.facade;

import java.util.List;

import com.paolorizzo.predictor.hibernate.model.WebServiceApiDetail;

public interface WebServiceApiDetailDao {
	
	void insert(WebServiceApiDetail webServiceApiDetail);

	List<WebServiceApiDetail> list();

	void delete(WebServiceApiDetail webServiceApiDetail);

	void update(WebServiceApiDetail webServiceApiDetail);

	WebServiceApiDetail get(String name);

}
