package com.revature.service;

import com.revature.classes.TransactionObject;

public interface BankService {
	
	public boolean checkAccountStatus(String pin, String accountTarget);
	public int checkBalance(String pin, String accountTarget);
	public boolean logTransaction(TransactionObject transaction);
	public String retrieveLog(int transactionId);
	public String retrieveAccountInfo(String pin);
	

}
