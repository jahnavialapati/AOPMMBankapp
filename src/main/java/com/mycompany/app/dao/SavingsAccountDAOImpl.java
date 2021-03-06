/*package com.mycompany.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.app.account.SavingsAccount;
import com.mycompany.app.exception.AccountNotFoundException;
import com.mycompany.app.util.DBUtil;
@Repository
public class SavingsAccountDAOImpl implements SavingsAccountDAO {

	public SavingsAccount createNewAccount(SavingsAccount account) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ACCOUNT VALUES(?,?,?,?,?,?)");
		preparedStatement.setInt(1, account.getBankAccount().getAccountNumber());
		preparedStatement.setString(2, account.getBankAccount().getAccountHolderName());
		preparedStatement.setDouble(3, account.getBankAccount().getAccountBalance());
		preparedStatement.setBoolean(4, account.isSalary());
		preparedStatement.setObject(5, null);
		preparedStatement.setString(6, "SA");
		preparedStatement.executeUpdate();
		preparedStatement.close();
		DBUtil.commit();
		return account;
	}

	public List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException {
		List<SavingsAccount> savingsAccounts = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM ACCOUNT");
		while (resultSet.next()) {// Check if row(s) is present in table
			int accountNumber = resultSet.getInt(1);
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salaried");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance, salary);
			savingsAccounts.add(savingsAccount);
		}
		DBUtil.commit();
		return savingsAccounts;
	}
	
	public void updateBalance(int accountNumber, double currentBalance) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		connection.setAutoCommit(false);
		PreparedStatement preparedStatement = connection.prepareStatement
				("UPDATE ACCOUNT SET account_bal=? where account_id=?");
		preparedStatement.setDouble(1, currentBalance);
		preparedStatement.setInt(2, accountNumber);
		preparedStatement.executeUpdate();
	}
	
	
	public SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			String accountHolderName = resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salaried");
			savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			return savingsAccount;
		}
		throw new AccountNotFoundException("Account with account number "+accountNumber+" does not exist.");
	}
	@Override
	public SavingsAccount getAccountByName(String accountName) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("SELECT * FROM account where account_hn=?");
		preparedStatement.setString(1, accountName);
		ResultSet resultSet = preparedStatement.executeQuery();
		SavingsAccount savingsAccount = null;
		if(resultSet.next()) {
			int accountNumber = resultSet.getInt("account_id");
			double accountBalance = resultSet.getDouble(3);
			boolean salary = resultSet.getBoolean("salaried");
			savingsAccount = new SavingsAccount(accountNumber, accountName, accountBalance,
					salary);
			return savingsAccount;
		}
		throw new AccountNotFoundException("Account with account number "+accountName+" does not exist.");
	}

	
	public SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException {
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement
				("delete from account where account_id=?");
		preparedStatement.setInt(1, accountNumber);
		preparedStatement.execute();
		DBUtil.commit();
		return null;
	}
	
	
	public double checkCurrentBalance(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException{
		SavingsAccountDAO savingsAccount=new SavingsAccountDAOImpl();
		if(savingsAccount.getAccountById(accountNumber).getBankAccount().getAccountNumber()==accountNumber){
			Connection connection = DBUtil.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement
					("select account_bal from account where account_id=?");
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				double accountBalance = resultSet.getDouble("account_bal");
				return accountBalance;
			}
			DBUtil.commit();
		}
		throw new AccountNotFoundException("Account with account number "+accountNumber+" does not exist.");
		
	}
	
	public boolean updateAccountType(SavingsAccount account) throws SQLException, ClassNotFoundException{
				Connection connection = DBUtil.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement
						("UPDATE ACCOUNT SET account_hn=?,salaried=? where account_id=?");
				preparedStatement.setString(1, account.getBankAccount().getAccountHolderName());
				preparedStatement.setBoolean(2,account.isSalary() );
				preparedStatement.setInt(3, account.getBankAccount().getAccountNumber());
				int count  = preparedStatement.executeUpdate();
				boolean result = false;
				if(count!=0){
					result = true;
				}
				DBUtil.commit();
			return result;
	}

	@Override
	public List<SavingsAccount> sortAllAccounts(int choice) throws ClassNotFoundException, SQLException {
		List<SavingsAccount> listSA=new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		PreparedStatement preparedStatement;
		ResultSet resultSet = null;
		switch(choice) {
		case 1:
			preparedStatement = connection.prepareStatement("select * from account order by account_id");
			resultSet=preparedStatement.executeQuery();
			break;
		case 2:
			preparedStatement = connection.prepareStatement("select * from account order by account_hn");
			resultSet=preparedStatement.executeQuery();
			break;
		case 3:
			preparedStatement = connection.prepareStatement("select * from account order by account_bal");
			resultSet=preparedStatement.executeQuery();
			break;
			
		}
		while(resultSet.next()) {
			int accountNumber = resultSet.getInt("account_id");
			String accountHolderName=resultSet.getString("account_hn");
			double accountBalance = resultSet.getDouble("account_bal");
			boolean salary = resultSet.getBoolean("salaried");
			SavingsAccount savingsAccount = new SavingsAccount(accountNumber, accountHolderName, accountBalance,
					salary);
			listSA.add(savingsAccount);
		}
		return listSA;
		
	}

	
	
}*/
