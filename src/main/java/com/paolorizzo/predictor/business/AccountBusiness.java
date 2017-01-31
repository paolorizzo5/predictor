package com.paolorizzo.predictor.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.paolorizzo.predictor.dao.facade.AccountDao;
import com.paolorizzo.predictor.enums.BetStatus;
import com.paolorizzo.predictor.hibernate.model.Account;
import com.paolorizzo.predictor.hibernate.model.Bet;
import com.paolorizzo.predictor.hibernate.model.MoneyTransaction;
import com.paolorizzo.predictor.hibernate.model.ProspectElement;
import com.paolorizzo.predictor.hibernate.model.User;
import com.paolorizzo.predictor.services.request.DepositAccountRequest;
import com.paolorizzo.predictor.services.response.DepositAccountResponse;
import com.paolorizzo.xmlsoccer.data.converter.AccountDataConverter;

public class AccountBusiness {
	
	static Logger logger = LogManager.getLogger(JobConfigurationBusiness.class
			.getName());

	@Autowired
	private AccountDao accountDao;
	

	@Autowired
	private MoneyTransactionBusiness moneyTransactionBusiness;
	
	public AccountBusiness(AccountDao accountDao) {
		super();
		this.accountDao = accountDao;
	}

	@Transactional(readOnly=false)
	public Account add(String name, String description, String email) {
		Account account = new Account();
		account.setName(name);
		account.setDescription(description);
		account.setInsertDate(new Date());
		account.setUser(new User(email, null));
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
		
		MoneyTransaction moneyTransaction = new MoneyTransaction();
		moneyTransaction.setAmount(new BigDecimal(amount));
		moneyTransaction.setDirection("+");
		moneyTransaction.setInsertDate(new Date());
		moneyTransaction.setAccount(account);
		
		account.getMoneyTransactions().add(moneyTransaction);
		accountDao.update(account);
		return new DepositAccountResponse(true,AccountDataConverter.convert(account));
		
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
		
		account.getMoneyTransactions().add(moneyTransaction);
		accountDao.update(account);
		return new DepositAccountResponse(true,AccountDataConverter.convert(account));
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
		Date now = new Date();
		String betStatus = null;
		BigDecimal possibleWin = null;
		BigDecimal expense = null;
		
		Account account = get(accountName, email);
		for (Bet bet : account.getBets()) {
			if(bet.getInsertDate().getTime() == insertDate){
				betStatus = bet.getBetStatus();
				if(BetStatus.WINNING.getText().equals(betStatus)){
					bet.setBetStatus(BetStatus.ARCHIVED_WIN.getText());
				}else{
					bet.setBetStatus(BetStatus.ARCHIVED_LOST.getText());
				}
				
				possibleWin = bet.getMoltiplicator().multiply(new BigDecimal(bet.getAmount())).setScale(2, RoundingMode.DOWN);
				expense = new BigDecimal(bet.getAmount());
			}
		}
		
		//aggiorno il prospetto
		for (ProspectElement prospectElement : account.getProspect().getProspectElements()) {
			if(prospectElement.getEndDate().compareTo(now) > 0 && prospectElement.getStartDate().compareTo(now) < 0){
				prospectElement.setLiveAmount(prospectElement.getLiveAmount().subtract(expense));
				if(BetStatus.WINNING.getText().equals(betStatus)){
					prospectElement.setLiveAmount(prospectElement.getLiveAmount().add(possibleWin));
				}
			}else{
				
			}
		}
		
		
		accountDao.update(account);
		
		
		
		return account;
	}

	public List<Account> list() {
		return accountDao.list();
		
	}

}
