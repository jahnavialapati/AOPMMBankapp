package com.mycompany.app.dao;

import java.util.List;

import com.mycompany.app.account.SavingsAccount;

public interface SavingsAccountDAO {
	
	SavingsAccount createNewAccount(SavingsAccount account);
	SavingsAccount getAccountById(int accountNumber);
	SavingsAccount deleteAccount(int accountNumber);
	List<SavingsAccount> getAllSavingsAccount();
	void updateBalance(int accountNumber, double currentBalance);
	double checkCurrentBalance(int accountNumber);
	boolean updateAccountType(SavingsAccount account);
	SavingsAccount getAccountByName(String accountName);		
	List<SavingsAccount> sortAllSavingsAccount(int choice);
}
