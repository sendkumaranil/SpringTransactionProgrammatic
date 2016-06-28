package com.swadesibank.transaction.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.swadesibank.transaction.bean.Account;

public class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rownum) throws SQLException {
		Account account=new Account();
		account.setAccountno(rs.getInt("accountno"));
		account.setBalance(rs.getDouble("balance"));
		return account;
	}
}
