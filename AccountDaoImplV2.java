package com.swadesibank.transaction.daoImpl;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.swadesibank.transaction.bean.Account;
import com.swadesibank.transaction.dao.IAccountDao;

public class AccountDaoImplV2 extends JdbcDaoSupport implements IAccountDao {
	TransactionTemplate transactionTemplate;

	
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	@Override
	public void insert(Account account) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				String sql="insert into swadesiaccount(accountno,balance)values(?,?)";
				getJdbcTemplate().update(sql, new Object[]{account.getAccountno(),account.getBalance()});
			}
		});
	}

	@Override
	public void update(Account account) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				String sql="update swadesiaccount set balance=? where accountno=?";
				getJdbcTemplate().update(sql, new Object[]{account.getBalance(),account.getAccountno()});
			}
		});
	}

	@Override
	public void updateWithException(Account account) throws Exception {
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				String sql="update swadesiaccount set balance=? where accountno=?";
				getJdbcTemplate().update(sql, new Object[]{account.getBalance(),account.getAccountno()});
				//--------------------Exception occurs in below line------------------------------------
					dummyTransaction(account);
				//--------------------------------------------------------------------------------------
			}
		});
	}

	private void dummyTransaction(Account account){
		String sql="update swadesiaccount set balance=? where accountno=?";
		getJdbcTemplate().update(sql, new Object[]{"err","10002"});
	}
	
	@Override
	public Account getAccount(Integer accountno) {
		String sql="select * from swadesiaccount where accountno=?";
		Account account=getJdbcTemplate().queryForObject(sql,new Object[]{accountno} ,new AccountRowMapper());
		return account;
	}
}

