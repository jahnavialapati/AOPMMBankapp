package com.mycompany.app.ui;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.service.SavingsAccountService;

@Component
public class AccountCUI {
	private static Scanner scanner = new Scanner(System.in);
	@Autowired
	private SavingsAccountService savingsAccountService;
	
	private static SavingsAccount savingsAccount;
	public void start() {
		
		do {
			System.out.println("****** Welcome to Money Money Bank********");
			System.out.println("1. Open New Savings Account");
			System.out.println("2. Update Account");
			System.out.println("3. Close Account");
			System.out.println("5. Withdraw");
			System.out.println("6. Deposit");
			System.out.println("7. FundTransfer");
			System.out.println("8. Check Current Balance");
			System.out.println("9. Get All Savings Account Details");
			System.out.println("10. Search account");
			System.out.println("11. Sort Accounts");
			System.out.println("12. Exit");
			System.out.println();
			System.out.println("Make your choice: ");
			
			int choice = scanner.nextInt();
			
			performOperation(choice);
			
		} while(true);
	}

	private void performOperation(int choice) {
		SavingsAccount savingsAccount=null;
		switch (choice) {
		case 1:
			acceptInput("SA");
			break;
	
		case 2:
			System.out.println("Enter your account number to update : ");
			int accountId = scanner.nextInt();
			savingsAccount = savingsAccountService.getAccountById(accountId);
			System.out.println(" Enter 1 to Update Your Name");
			System.out.println("Enter 2 to Update Your Salary Type");
			System.out.println("Enter 3 to Update Your Name and Salary Type ");
			int select = scanner.nextInt();
			update(select,savingsAccount);
			break;
		case 3:
			deleteAccount();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			deposit();
			break;
		case 7:
			fundTransfer();
			break;
		case 8:
			checkCurrentBalance();
			break;
		case 9:
			showAllAccounts();
			break;
		case 10:
			searchAccount();
			break;
		case 11:
			sortAccounts();
			break;
		case 12:
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice!");
			break;
		}
		
	}

	
	private void sortAccounts() {
		List<SavingsAccount> sortAllAccounts;
		System.out.println("1.Sort by account number \n"+"2.Sort by accountname \n" + "3.Sort by account balance");
		int choice=scanner.nextInt();
		switch(choice) {
		case 1:
			
				sortAllAccounts=savingsAccountService.sortAllSavingsAccount(choice);
				System.out.println(sortAllAccounts);
			
			break;
		case 2:
				sortAllAccounts=savingsAccountService.sortAllSavingsAccount(choice);
				System.out.println(sortAllAccounts);
			break;
		case 3:
			
				sortAllAccounts=savingsAccountService.sortAllSavingsAccount(choice);
				System.out.println(sortAllAccounts);
			break;	
		}
		
	}
	
	private void searchAccount() {
		
			System.out.println("1.Search By Account Id");
			System.out.println("2.Search By Account Holder Name");
			System.out.println("3.Redirect to start menu");
			int search = scanner.nextInt();
			switch (search) {
			case 1:
				System.out.println("Enter account number to search");
				int accountNumber = scanner.nextInt();
				SavingsAccount savingsAccount = savingsAccountService.getAccountById(accountNumber);
				System.out.println(savingsAccount);
			break;

			case 2:
				System.out.println("Enter Account Holder  to search");
				String accountHolderName = scanner.nextLine();
				accountHolderName = scanner.nextLine();
				SavingsAccount savingsAccounts= savingsAccountService.getAccountByName(accountHolderName);
				System.out.println(savingsAccounts);
				break;

			case 3:
				start();
				break;

			default:
				System.err.println("Invalid Choice!");
				break;
			}

	}
	
	
private void update(int select, SavingsAccount savingsAccount2) {
		
	switch(select){
		case 1:
			System.out.println("Enter new name change your name : ");
			String newName = scanner.nextLine();
			newName = scanner.nextLine();
			savingsAccount2.getBankAccount().setAccountHolderName(newName);
			boolean name;
			name = savingsAccountService.updateAccount(savingsAccount2);
			if(name==true){
				System.out.println("Name Changed for "+savingsAccount2.getBankAccount().getAccountNumber()+" to "+newName);
			}
			break;
			
		case 2:
			System.out.println("If your account type is salaried enter (n) to change your account type as unsalaried");
			System.out.println("If your account type is savings enter (y)to change your account type as salaried");
			boolean changeSalaryType = scanner.next().equalsIgnoreCase("n")?false:true;
			savingsAccount2.setSalary(changeSalaryType);	 
	
				boolean salary;
				salary = savingsAccountService.updateAccount(savingsAccount2);
				if(salary==true){
					System.out.println("Salary type of "+savingsAccount2.getBankAccount().getAccountNumber()+"Changed to "+changeSalaryType);
				}
			break;

		case 3:
			System.out.println("Enter new name to change your name : ");
			String changename = scanner.nextLine();
			changename = scanner.nextLine();
			savingsAccount2.getBankAccount().setAccountHolderName(changename);
			System.out.println("If your account type is salaried enter (n) to change your account type as unsalaried");
			System.out.println("If your account type is savings enter (y)to change your account type as salaried");
			boolean changeTypeOfSalary = scanner.next().equalsIgnoreCase("n")?false:true;
			savingsAccount2.setSalary(changeTypeOfSalary);
			
				boolean salaryResult;
				salaryResult = savingsAccountService.updateAccount(savingsAccount2);
				if(salaryResult==true){
					System.out.println("Name and Salary type for "+savingsAccount2.getBankAccount().getAccountNumber()+" Changed to "+changename+" and "+changeTypeOfSalary);
			}
			break;

		default:
			System.err.println("Invalid Choice!");
			break;
		}
	}

	private void checkCurrentBalance() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		double balance=0.0;
			balance=savingsAccountService.checkCurrentBalance(accountNumber);
				System.out.println("Your CurrentBalance is:"+balance);
		}

	private void deleteAccount() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		
			savingsAccountService.deleteAccount(accountNumber);
	}

	private void fundTransfer() {
		System.out.println("Enter Account Sender's Number: ");
		int senderAccountNumber = scanner.nextInt();
		System.out.println("Enter Account Receiver's Number: ");
		int receiverAccountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		
			SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
			SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
			savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
	}

	private void deposit() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
			savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.deposit(savingsAccount, amount);
	}

	private void withdraw() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
		
			savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.withdraw(savingsAccount, amount);
	}
	  
	private void showAllAccounts() {
		List<SavingsAccount> savingsAccounts;
		
			savingsAccounts = savingsAccountService.getAllSavingsAccount();
			for (SavingsAccount savingsAccount : savingsAccounts) {
				System.out.println(savingsAccount);
			}
	}

	private void acceptInput(String type) {
		if(type.equalsIgnoreCase("SA")) {
			System.out.println("Enter your Full Name: ");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			System.out.println("Enter Initial Balance(type na for Zero Balance): ");
			String accountBalanceStr = scanner.next();
			double accountBalance=0.0;
			if(!accountBalanceStr.equalsIgnoreCase("na")) {
				accountBalance = Double.parseDouble(accountBalanceStr);
			}
			System.out.println("Salaried?(y/n): ");
			boolean salary = scanner.next().equalsIgnoreCase("n")?false:true;
			createSavingsAccount(accountHolderName,accountBalance, salary);
		}
	}

	private void createSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
			savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
	}	
		
}



