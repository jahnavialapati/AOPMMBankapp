package com.mycompany.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mycompany.app.account.SavingsAccount;

public class SavingAccountMapper implements RowMapper<SavingsAccount> {

	@Override
	public SavingsAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println(rowNum);
		int accountnumber=rs.getInt(1);
		String  accountHolderName=rs.getString(2);
		double balance=rs.getDouble(3);
		boolean salary=rs.getBoolean(4);
		SavingsAccount savingsAccount=new SavingsAccount(accountnumber, accountHolderName,balance,salary);
		return savingsAccount;
	}

}
