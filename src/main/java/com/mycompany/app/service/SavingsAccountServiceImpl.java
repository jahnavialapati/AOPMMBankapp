package com.mycompany.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.dao.SavingsAccountDAO;
import com.mycompany.app.exception.InsufficientFundsException;
import com.mycompany.app.exception.InvalidInputException;
import com.mycompany.app.factory.AccountFactory;
@Service
public class SavingsAccountServiceImpl implements SavingsAccountService {

	private AccountFactory factory;
	@Autowired
	private SavingsAccountDAO savingsAccountDAO;

	public SavingsAccountServiceImpl(SavingsAccountDAO savingsAccountDAO) {
		factory = AccountFactory.getInstance();
		this.savingsAccountDAO=savingsAccountDAO;
	}

	
	public SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary){
		SavingsAccount account = factory.createNewSavingsAccount(accountHolderName, accountBalance, salary);
		savingsAccountDAO.createNewAccount(account);
		return null;
	}

	
	public List<SavingsAccount> getAllSavingsAccount() {
		return savingsAccountDAO.getAllSavingsAccount();
	}

	
	public void deposit(SavingsAccount account, double amount) {
	//	if (amount > 0) {
			double currentBalance = account.getBankAccount().getAccountBalance();
			currentBalance += amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
		//}else {
		//	throw new InvalidInputException("Invalid Input Amount!");
		//}
	}
	
	public void withdraw(SavingsAccount account, double amount){
		double currentBalance = account.getBankAccount().getAccountBalance();
		//if (amount > 0 && currentBalance >= amount) {
			currentBalance -= amount;
			savingsAccountDAO.updateBalance(account.getBankAccount().getAccountNumber(), currentBalance);
			//savingsAccountDAO.commit();
	//	} else {
		//	throw new InsufficientFundsException("Invalid Input or Insufficient Funds!");
		//}
	}

	@Transactional(rollbackForClassName= {"Throwable"})
	public void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount){
			
			withdraw(sender, amount);
			deposit(receiver, amount);
	}

	
	public boolean updateAccount(SavingsAccount account) {
		return savingsAccountDAO.updateAccountType(account);
	}
	
	
	public SavingsAccount getAccountById(int accountNumber){
		return savingsAccountDAO.getAccountById(accountNumber);
	}
	public SavingsAccount getAccountByName(String accountName)  {
		return savingsAccountDAO.getAccountByName(accountName);
	}

	
	public SavingsAccount deleteAccount(int accountNumber){
		return savingsAccountDAO.deleteAccount(accountNumber);
	}

	
	public double checkCurrentBalance(int accountNumber){
		return savingsAccountDAO.checkCurrentBalance(accountNumber);
	}


	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice){
		
		return savingsAccountDAO.sortAllSavingsAccount(choice);
	}




}
