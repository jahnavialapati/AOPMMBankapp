package com.mycompany.app.service;

import java.util.List;

import com.mycompany.app.account.SavingsAccount;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary);

	boolean updateAccount(SavingsAccount account);

	SavingsAccount getAccountById(int accountNumber);
	SavingsAccount getAccountByName(String accountName);

	SavingsAccount deleteAccount(int accountNumber);
	
	List<SavingsAccount> getAllSavingsAccount();

	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount);
	
	void deposit(SavingsAccount account, double amount);
	
	void withdraw(SavingsAccount account, double amount);

	double checkCurrentBalance(int accountNumber);

	List<SavingsAccount> sortAllSavingsAccount(int choice);

	
}











