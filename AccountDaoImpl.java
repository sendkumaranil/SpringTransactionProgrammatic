package com.swadesibank.transaction.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.swadesibank.transaction.bean.Account;
import com.swadesibank.transaction.dao.IAccountDao;

public class AccountDaoImpl extends JdbcDaoSupport implements IAccountDao {
	PlatformTransactionManager platformTxManager;

	public void setPlatformTxManager(PlatformTransactionManager platformTxManager) {
		this.platformTxManager = platformTxManager;
	}

	@Override
	public void insert(Account account) throws Exception {
		TransactionDefinition txDef=new DefaultTransactionDefinition();
		TransactionStatus status=platformTxManager.getTransaction(txDef);
		
		try {
			
			String sql="insert into swadesiaccount(accountno,balance)values(?,?)";
			getJdbcTemplate().update(sql, new Object[]{account.getAccountno(),account.getBalance()});
			platformTxManager.commit(status);
			
		} catch (DataAccessException e) {
			System.out.println("Transaction has been rollbacked by transaction manager!!");
			platformTxManager.rollback(status);
			throw e;
		}
	}

	@Override
	public void update(Account account) throws Exception{
		TransactionDefinition txDef=new DefaultTransactionDefinition();
		TransactionStatus status=platformTxManager.getTransaction(txDef);
		
		try {
			String sql="update swadesiaccount set balance=? where accountno=?";
			getJdbcTemplate().update(sql, new Object[]{account.getBalance(),account.getAccountno()});
			platformTxManager.commit(status);
		} catch (Exception e) {
			System.out.println("Transaction has been rollbacked by transaction manager!!");
			platformTxManager.rollback(status);
			throw e;
		}
	}

	@Override
	public Account getAccount(Integer accountno) {
		String sql="select * from swadesiaccount where accountno=?";
		Account account=getJdbcTemplate().queryForObject(sql,new Object[]{accountno} ,new AccountRowMapper());
		return account;
	}

	private void dummyTransaction(Account account){
		String sql="update swadesiaccount set balance=? where accountno=?";
		getJdbcTemplate().update(sql, new Object[]{"err","10002"});
	}

	@Override
	public void updateWithException(Account account) throws Exception {
		TransactionDefinition txDef=new DefaultTransactionDefinition();
		TransactionStatus status=platformTxManager.getTransaction(txDef);
		
		try {
			String sql="update swadesiaccount set balance=? where accountno=?";
			getJdbcTemplate().update(sql, new Object[]{account.getBalance(),account.getAccountno()});
			//--------------------Exception occurs in below line------------------------------------
				dummyTransaction(account);
			//--------------------------------------------------------------------------------------
				platformTxManager.commit(status);
		} catch (Exception e) {
			System.out.println("Transaction has been rollbacked by transaction manager!!");
			platformTxManager.rollback(status);
			throw e;
		}
		
	}
}
class AccountRowMapper implements RowMapper<Account> {

	@Override
	public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
		Account account=new Account();
		account.setAccountno(rs.getInt("accountno"));
		account.setBalance(rs.getDouble("balance"));
		return account;
	}
}