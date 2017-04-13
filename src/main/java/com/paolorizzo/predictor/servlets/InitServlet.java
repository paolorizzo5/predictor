package com.paolorizzo.predictor.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.paolorizzo.predictor.business.BetTypeBusiness;
import com.paolorizzo.predictor.business.JobConfigurationBusiness;
import com.paolorizzo.predictor.business.UserBusiness;
import com.paolorizzo.predictor.hibernate.model.JobConfiguration;
import com.paolorizzo.predictor.jobs.AccountStatsJob;
import com.paolorizzo.predictor.spring.AppContext;
import com.paolorizzo.predictor.utils.MD5;
import com.paolorizzo.xmlsoccer.XmlSoccerClient;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet("/InitServlet")
public class InitServlet extends HttpServlet {

	Logger logger = LogManager.getLogger("root");

	private static final long serialVersionUID = 1L;

	private XmlSoccerClient xmlSoccerClient;

	private UserBusiness userBusiness;

	private JobConfigurationBusiness jobConfigurationBusiness;
	
	private BetTypeBusiness betTypeBusiness;

	//
	// private AreaBusiness areaBusiness;

	public InitServlet() {

		logger.debug("INIT");
		

		xmlSoccerClient = AppContext.getApplicationContext().getBean(
				"xmlSoccerClientBean", XmlSoccerClient.class);

		userBusiness = AppContext.getApplicationContext().getBean(
				"userBusinessBean", UserBusiness.class);

		jobConfigurationBusiness = AppContext.getApplicationContext().getBean(
				"jobConfigurationBusinessBean", JobConfigurationBusiness.class);
		
		betTypeBusiness = AppContext.getApplicationContext().getBean(
				"betTypeBusinessBean", BetTypeBusiness.class);
		
		

		if (userBusiness.isEmpty()) {
			userBusiness
					.insert("paolorizzo5@gmail.com", MD5.getMD5("Anno2010"));
		}
		
		if (betTypeBusiness.isEmpty()) {
			betTypeBusiness
					.populate();
		}

		try {
			quartzInit();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			xmlSoccerClient.execute();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	private void quartzInit() throws SchedulerException {

		List<JobConfiguration> jobConfigurations = new ArrayList<JobConfiguration>();

		// jobConfigurations.add(new JobConfiguration("DailyScoresUpdaterJob",
		// "0 0/55 * 1/1 * ? *", "scores", DailyScoresUpdaterJob.class
		// .getName()));
		// jobConfigurations.add(new JobConfiguration("StatsEngineJob",
		// "0 0/5 * 1/1 * ? *", "scores", StatsEngineJob.class.getName()));

//		jobConfigurations.add(new JobConfiguration("XmlSoccerUpdateJob",
//				"0 0/10 * 1/1 * ? *", "scores", XmlSoccerUpdateJob.class
//						.getName(), new Date()));

//		jobConfigurations.add(new JobConfiguration("AccountStatsJob",
//				"0 0/1 * 1/1 * ? *", "scores", AccountStatsJob.class
//						.getName(), new Date()));
//		
		jobConfigurations.add(new JobConfiguration("AccountStatsJob",
				"	0 0 0/6 1/1 * ? *", "scores", AccountStatsJob.class
						.getName(), new Date()));
		
		
		jobConfigurationBusiness.clear();
		
		if (jobConfigurationBusiness.isEmpty()) {
			jobConfigurationBusiness.insertAll(jobConfigurations);
		}

		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();

		List<JobConfiguration> jobConfigurationsList = jobConfigurationBusiness
				.list();
		for (JobConfiguration jobConfiguration : jobConfigurationsList) {
			try {
				JobDetail jobDetail = JobBuilder
						.newJob((Class<? extends Job>) Class
								.forName(jobConfiguration.getClassName()))
						.withIdentity(jobConfiguration.getName(),
								jobConfiguration.getJobGroup()).build();

				Trigger trigger = TriggerBuilder
						.newTrigger()
						.withIdentity(jobConfiguration.getName() + "Trigger",
								jobConfiguration.getJobGroup())
						.withSchedule(
								CronScheduleBuilder
										.cronSchedule(jobConfiguration
												.getCronExpression())).build();

				scheduler.scheduleJob(jobDetail, trigger);

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public UserBusiness getUserBusiness() {
		return userBusiness;
	}

	public void setUserBusiness(UserBusiness userBusiness) {
		this.userBusiness = userBusiness;
	}

}
