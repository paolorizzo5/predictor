package com.paolorizzo.predictor.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.constants.DbConstants;
import com.paolorizzo.predictor.dao.facade.AccountDao;
import com.paolorizzo.predictor.enums.BetStatus;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.AccountStats;
import com.paolorizzo.predictor.hibernate.model.Bet;
import com.paolorizzo.predictor.hibernate.model.MoneyTransaction;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.response.DepositAccountResponse;
import com.paolorizzo.predictor.utils.SimpleUtils;
import com.paolorizzo.xmlsoccer.data.converter.AccountDataConverter;
import com.paolorizzo.xmlsoccer.data.converter.UserDataConverter;

public class AccountBusiness {
	
	static Logger logger = LogManager.getLogger(JobConfigurationBusiness.class
			.getName());

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private UserBusiness userBusiness;

	@Autowired
	private MoneyTransactionBusiness moneyTransactionBusiness;
	
	
	public AccountBusiness(AccountDao accountDao, UserBusiness userBusiness,
			MoneyTransactionBusiness moneyTransactionBusiness) {
		super();
		this.accountDao = accountDao;
		this.userBusiness = userBusiness;
		this.moneyTransactionBusiness = moneyTransactionBusiness;
	}

	@Transactional(readOnly=false)
	public Account add(String name, String description, String email) {
		Account account = new Account();
		account.setName(name);
		account.setDescription(description);
		account.setInsertDate(new Date());
		account.setUser(new User(email, null));
		account.setLiveAmount(new BigDecimal(0));
		accountDao.insert(account);
		return account;
	}

	public List<Account> list(String email) {
		return accountDao.list(email);
	}

	@Transactional(readOnly=false)
	public DepositAccountResponse deposit(String accountName, String amount, String email) {
		Account account = get(accountName,email);
		account.setUser(new User(email, null));
		
		//effettuo la transazione diretta su conto di gioco
		MoneyTransaction moneyTransaction = new MoneyTransaction();
		moneyTransaction.setAmount(new BigDecimal(amount));
		moneyTransaction.setDirection(DbConstants.MONEYTRANSACTION_INCOMING);
		moneyTransaction.setInsertDate(new Date());
		moneyTransaction.setAccount(account);
		moneyTransaction.setReason(DbConstants.MONEYTRANSACTION_REASON_DEPOSIT);
		
		
		account.getMoneyTransactions().add(moneyTransaction);
		account.setLiveAmount(account.getLiveAmount().add(moneyTransaction.getAmount()));
		accountDao.update(account);
		
		//effettuo transazione su portafoglio utente
		User user = userBusiness.findByEmail(email);
		user.setPortFolioAmount(user.getPortFolioAmount().subtract(moneyTransaction.getAmount()));
		userBusiness.update(user);
		
		return new DepositAccountResponse(true,AccountDataConverter.convert(account),UserDataConverter.convert(user));
		
	}

	public Account get(String accountName, String email) {
		return accountDao.get(accountName,email);
	}

	@Transactional(readOnly=false)
	public DepositAccountResponse withdraw(String accountName, String amount, String email) {
		Account account = get(accountName,email);
		account.setUser(new User(email, null));
		
		MoneyTransaction moneyTransaction = new MoneyTransaction();
		moneyTransaction.setAmount(new BigDecimal(amount));
		moneyTransaction.setDirection("-");
		moneyTransaction.setInsertDate(new Date());
		moneyTransaction.setAccount(account);
		moneyTransaction.setReason(DbConstants.MONEYTRANSACTION_REASON_WITHDRAW);
		
		
		account.getMoneyTransactions().add(moneyTransaction);
		account.setLiveAmount(account.getLiveAmount().subtract(moneyTransaction.getAmount()));
		
		accountDao.update(account);
		
		//effettuo transazione su portafoglio utente
		User user = userBusiness.findByEmail(email);
		user.setPortFolioAmount(user.getPortFolioAmount().add(moneyTransaction.getAmount()));
		userBusiness.update(user);
				
		return new DepositAccountResponse(true,AccountDataConverter.convert(account),UserDataConverter.convert(user));
	}

	@Transactional(readOnly=false)
	public Account delete(String accountName, String email) {
		Account account = get(accountName, email);
		
		for (MoneyTransaction moneyTransaction  : account.getMoneyTransactions()) {
			moneyTransactionBusiness.delete(moneyTransaction);
		}
		
		account.setMoneyTransactions(null);
		account.setName(accountName);
		account.setMoneyTransactions(null);
		account.setUser(new User(email, null));
		accountDao.delete(account);
		return account;
	}

	@Transactional(readOnly = false)
	public Account addBet(String accountName, Integer amount, String bettypeDescription, String email,
			String eventDescription, BigDecimal moltiplicator) {
		Account account = get(accountName, email);
		Bet bet = new Bet();
		bet.setAccount(account);
		bet.setAmount(amount);
		bet.setBetStatus(com.paolorizzo.predictor.enums.BetStatus.PLACED.getText());
		bet.setBettypeDescription(bettypeDescription);
		bet.setEventDescription(eventDescription);
		bet.setMoltiplicator(moltiplicator);
		bet.setInsertDate(new Date());
		account.getBets().add(bet);
		
		MoneyTransaction moneyTransaction = new MoneyTransaction();
		moneyTransaction.setAmount(new BigDecimal(amount));
		moneyTransaction.setDirection(DbConstants.MONEYTRANSACTION_OUTGOING);
		moneyTransaction.setInsertDate(new Date());
		moneyTransaction.setAccount(new Account(accountName, new User(email)));
		moneyTransaction.setReason(DbConstants.MONEYTRANSACTION_REASON_BET);
		
		account.getMoneyTransactions().add(moneyTransaction);
		account.setLiveAmount(account.getLiveAmount().subtract(moneyTransaction.getAmount()));
		
		accountDao.update(account);
		return account;
		
	}
	
	@Transactional(readOnly = false)
	public Account addStatistic(Account account) {
		AccountStats accountStats = new AccountStats();
		accountStats.setAccount(account);
		accountStats.setAmount(account.getLiveAmount());
		accountStats.setLogDate(new Date());
		account.getAccountStats().add(accountStats);
		accountDao.update(account);
		return account;
		
	}

	@Transactional(readOnly = false)
	public Account markBet(String accountName, String email, Long insertDate, Boolean isWinning) {
		Account account = get(accountName, email);
		for (Bet bet : account.getBets()) {
			if(bet.getInsertDate().getTime() == insertDate){
			if (isWinning){
				bet.setBetStatus(BetStatus.WINNING.getText());
			}else{
				bet.setBetStatus(BetStatus.LOSING.getText());
			}
			}
		}
		accountDao.update(account);
		return account;
	}
	
	@Transactional(readOnly = false)
	public Account archiveBet(String accountName, String email, Long insertDate) {
		String betStatus = null;
		BigDecimal possibleWin = null;
		BigDecimal expense = null;
		Boolean terminationDateUpdated = false;
		Date operationDate = new Date();
		Date today = new Date();
		Date tomorrow = null;
		
		
		Account account = get(accountName, email);
		for (Bet bet : account.getBets()) {
			if(bet.getInsertDate().getTime() == insertDate){
				betStatus = bet.getBetStatus();
				possibleWin = bet.getMoltiplicator().multiply(new BigDecimal(bet.getAmount())).setScale(2, RoundingMode.DOWN);
				expense = new BigDecimal(bet.getAmount());
				if(BetStatus.WINNING.getText().equals(betStatus)){
					bet.setBetStatus(BetStatus.ARCHIVED_WIN.getText());
					account.setLiveAmount(account.getLiveAmount().add(possibleWin));
					
					MoneyTransaction moneyTransaction = new MoneyTransaction();
					moneyTransaction.setAmount(possibleWin);
					moneyTransaction.setDirection(DbConstants.MONEYTRANSACTION_INCOMING);
					moneyTransaction.setInsertDate(new Date());
					moneyTransaction.setAccount(account);
					moneyTransaction.setReason(DbConstants.MONEYTRANSACTION_REASON_WINNINGBET);
					account.getMoneyTransactions().add(moneyTransaction);
					
				}else{
					bet.setBetStatus(BetStatus.ARCHIVED_LOST.getText());
				}
			}
		}
		
		//aggiorno il prospetto
		for (ProspectElement prospectElement : account.getProspect().getProspectElements()) {
			if(prospectElement.getExpectedGoal().compareTo(account.getLiveAmount()) > 0){
				if(terminationDateUpdated){
					prospectElement.setStartDate(today);
					prospectElement.setEndDate(SimpleUtils.tomorrow(today));
					today = SimpleUtils.tomorrow(today);
				}
				
				if(prospectElement.getTerminationDate() != null){
					if(terminationDateUpdated){
						prospectElement.setEndDate(today);
					}else{
						prospectElement.setEndDate(SimpleUtils.tomorrow(today));
					}
					prospectElement.setTerminationDate(null);
					
					today = SimpleUtils.tomorrow(today);
					terminationDateUpdated = true;
				}
			}else{
				if(terminationDateUpdated){
					prospectElement.setStartDate(today);
					prospectElement.setEndDate(today);
					
				}
				if(prospectElement.getTerminationDate() == null){
					prospectElement.setTerminationDate(operationDate);
					terminationDateUpdated = true;
				}
			}
		}
		accountDao.update(account);
		return account;
	}

	public List<Account> list() {
		return accountDao.list();
		
	}

}
