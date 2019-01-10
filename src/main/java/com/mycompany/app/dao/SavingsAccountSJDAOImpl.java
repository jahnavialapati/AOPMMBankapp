package com.mycompany.app.dao;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mycompany.app.account.SavingsAccount;

@Repository
@Primary
public class SavingsAccountSJDAOImpl implements SavingsAccountDAO{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	  @Override 
	  public SavingsAccount createNewAccount(SavingsAccount account) {
		  Logger logger=Logger.getLogger(SavingsAccountSJDAOImpl.class.getName());
		  logger.info("congrats!! Your account opened");
		  jdbcTemplate.update("insert into account values(?,?,?,?,?,?)",
				  new Object[] {account.getBankAccount().getAccountNumber(),
						  account.getBankAccount().getAccountHolderName(),
						  account.getBankAccount().getAccountBalance(),
						  account.isSalary(),
						  null,
		  					"SA"});
	  return null;
	  }
	 

	@Override
	public SavingsAccount getAccountById(int accountNumber){	
		return jdbcTemplate.queryForObject("SELECT * FROM ACCOUNT WHERE account_id=?", new Object[] {accountNumber},new SavingAccountMapper());			  
	}
	
	@Override
	public SavingsAccount getAccountByName(String accountName){
		return jdbcTemplate.queryForObject("SELECT * FROM ACCOUNT WHERE account_hn=?", new Object[] {accountName},new SavingAccountMapper());
				  
	}

	@Override
	public SavingsAccount deleteAccount(int accountNumber)  {
		  jdbcTemplate.update("DELETE FROM ACCOUNT where account_id=?",new Object[] {accountNumber});
		return null;
	}

	@Override
	public List<SavingsAccount> getAllSavingsAccount() {
		List<SavingsAccount> sa=jdbcTemplate.query("SELECT * FROM ACCOUNT", new SavingAccountMapper());
		return sa;
	}

	@Override
	public void updateBalance(int accountNumber, double currentBalance) {
		jdbcTemplate.update("UPDATE ACCOUNT SET account_bal=? where account_id=?",new Object[] { currentBalance, accountNumber });
	}

	@Override
	public double checkCurrentBalance(int accountNumber){		
		return jdbcTemplate.queryForObject("select account_bal from account where account_id=?",new Object[] {accountNumber},Double.class);
	}

	@Override
	public boolean updateAccountType(SavingsAccount account){
		jdbcTemplate.update("UPDATE ACCOUNT SET account_hn=?,salaried=? where account_id=?",
				new Object[] { account.getBankAccount().getAccountHolderName(), account.isSalary(),
						account.getBankAccount().getAccountNumber() });

		return false;
	}


	@Override
	public List<SavingsAccount> sortAllSavingsAccount(int choice) {
		String query=null;
		switch(choice) {
		case 1:
			query="select * from account order by account_id";
			break;
		case 2:
			query="select * from account order by account_hn";
			break;
		case 3:
			query="select * from account order by account_bal";
			break;
		}
		return jdbcTemplate.query(query,new SavingAccountMapper());
	}

}
