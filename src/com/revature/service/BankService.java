package com.revature.service;

import com.revature.classes.TransactionObject;

public interface BankService {
	
	public boolean checkAccountStatus(String pin, String typeTarget);
	public int checkBalance(String pin, String typeTarget);
	public boolean logTransaction(TransactionObject transaction);
	public String retrieveLog(int transactionId);
	public String retrieveAccountInfo(String pin);
	

}
