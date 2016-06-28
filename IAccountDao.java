package com.swadesibank.transaction.dao;

import com.swadesibank.transaction.bean.Account;

public interface IAccountDao {

	public void insert(Account account) throws Exception;
	public void update(Account account) throws Exception;
	public void updateWithException(Account account) throws Exception;
	public Account getAccount(Integer accountno);
}
