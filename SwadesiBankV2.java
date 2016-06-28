package com.swadesibank.transaction.client;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.swadesibank.transaction.bean.Account;
import com.swadesibank.transaction.service.AccountServiceV2;

public class SwadesiBankV2 {

	public static void main(String[] args) {


		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"spring-config.xml");
		AccountServiceV2 accountService = appContext.getBean("accountServiceV2",
				AccountServiceV2.class);

		// Create Account:
		Account anilkumar = new Account();
		anilkumar.setAccountno(10010);
		anilkumar.setBalance(2500.0);

		Account rajivverma = new Account();
		rajivverma.setAccountno(10011);
		rajivverma.setBalance(5000.0);

		Account priyagill = new Account();
		priyagill.setAccountno(10012);
		priyagill.setBalance(4500.0);

		
		 /* accountService.createAccount(anilkumar);
		  accountService.createAccount(rajivverma);
		  accountService.createAccount(priyagill);*/
		 

		// Transferring funds..
		Account fromAccount = null;
		Account toAccount = null;

		// ----------------------------User Input ------------------
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Debit Account No:");
		Integer debitAccount = scan.nextInt();
		System.out.println("Enter credit Account No:");
		Integer creditAccount = scan.nextInt();
		System.out.println("Enter Amount to transfer:");
		Double transferAmount = scan.nextDouble();
		// ---------------------------------------------------------

		/*fromAccount = accountService.getAccount(debitAccount);
		toAccount = accountService.getAccount(creditAccount);

		accountService.fundTransfer(fromAccount, toAccount, transferAmount);

		fromAccount = accountService.getAccount(debitAccount);
		toAccount = accountService.getAccount(creditAccount);

		accountService.printAccountInfo(fromAccount, toAccount);*/

		
		/* fromAccount=accountService.getAccount(debitAccount);
		 toAccount=accountService.getAccount(creditAccount);
		 
		 accountService.fundTransferWithException(fromAccount, toAccount,transferAmount);
		 
		 fromAccount=accountService.getAccount(debitAccount);
		 toAccount=accountService.getAccount(creditAccount);
		 
		 accountService.printAccountInfo(fromAccount, toAccount);*/
		 
	}

}
