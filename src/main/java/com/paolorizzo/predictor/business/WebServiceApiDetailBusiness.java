package com.paolorizzo.predictor.business;

import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.constants.WebServiceApiDetailConstants;
import com.paolorizzo.predictor.dao.facade.WebServiceApiDetailDao;
import com.paolorizzo.predictor.dto.WebServiceApiDetailDto;
import com.paolorizzo.predictor.hibernate.model.WebServiceApiDetail;
import com.paolorizzo.xmlsoccer.data.converter.WebServiceApiDetailDataConverter;

			
public class WebServiceApiDetailBusiness {
	
	static Logger logger = LogManager.getLogger(WebServiceApiDetailBusiness.class
			.getName());

	@Autowired
	private WebServiceApiDetailDao webServiceApiDetailDao;
	
	
	

	public WebServiceApiDetailBusiness(WebServiceApiDetailDao webServiceApiDetailDao) {
		super();
		this.webServiceApiDetailDao = webServiceApiDetailDao;
	}

	public boolean isEmpty() {
		return webServiceApiDetailDao.list().size() == 0;
	}

	@Transactional(readOnly=false)
	public void addAll(List<WebServiceApiDetail> webServiceApiDetails) {
		for (WebServiceApiDetail webServiceApiDetail : webServiceApiDetails) {
			webServiceApiDetailDao.insert(webServiceApiDetail);
		}
		
	}

	
	@Transactional(readOnly=false)
	public WebServiceApiDetailDto canMakeCall(String name) {
		WebServiceApiDetailDto webServiceApiDetailDto = new WebServiceApiDetailDto();
		
		WebServiceApiDetail webServiceApiDetail = webServiceApiDetailDao.get(name);
		webServiceApiDetailDto = WebServiceApiDetailDataConverter.convert(webServiceApiDetail);
		
		if(webServiceApiDetailDto.getCanMakeCall()){
			webServiceApiDetail.setLastExecutionDate(new Date());
			webServiceApiDetail.setNumberOfCalls(webServiceApiDetail.getNumberOfCalls() + 1);
			update(webServiceApiDetail);
			logger.debug("chiamata a " + name);
			return webServiceApiDetailDto;	
		}else{
			try {
				Thread.sleep(webServiceApiDetail.getCallFrequency());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return canMakeCall(name);
//			return webServiceApiDetailDto;
		}
		
	}
	
	@Transactional(readOnly=false)
	private void update(WebServiceApiDetail webServiceApiDetail) {
		webServiceApiDetailDao.update(webServiceApiDetail);
	}

}
