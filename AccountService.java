package com.swadesibank.transaction.service;

import com.swadesibank.transaction.bean.Account;
import com.swadesibank.transaction.dao.IAccountDao;

public class AccountService {

	private IAccountDao accountDao;

	public void setAccountDao(IAccountDao accountDao) {
		this.accountDao = accountDao;
	}
	
	public void createAccount(Account account){
		try {
			accountDao.insert(account);
			System.out.println("Insertion Success!!");
		} catch (Exception e) {
			System.out.println("Insertion failed!!");
			e.printStackTrace();
		}
	}
	
	public Account getAccount(Integer accountno){
		
		return accountDao.getAccount(accountno);
	}
	
	public void fundTransfer(final Account fromAccount,final Account toAccount,Double transferAmount){

			System.out.println("tranferring amount:"+transferAmount+" to the accountno:"+toAccount.getAccountno());
			fromAccount.debit(transferAmount);
			toAccount.credit(transferAmount);
			
			try {
				accountDao.update(fromAccount);
				accountDao.update(toAccount);
				System.out.println("Tranfer completed...");
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void fundTransferWithException(final Account fromAccount,final Account toAccount,Double transferAmount){

		System.out.println("tranferring amount:"+transferAmount+" to the accountno:"+toAccount.getAccountno());
		fromAccount.debit(transferAmount);
		toAccount.credit(transferAmount);
		
		try {
			accountDao.updateWithException(fromAccount);
			accountDao.updateWithException(toAccount);
			System.out.println("Tranfer completed...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printAccountInfo(Account fromAccount,Account toAccount){
		
		System.out.println("Debited Account No:"+fromAccount.getAccountno()+" Balance:"+fromAccount.getBalance());
		System.out.println("Credited Account No:"+toAccount.getAccountno()+" Balance:"+toAccount.getBalance());
	}
}
