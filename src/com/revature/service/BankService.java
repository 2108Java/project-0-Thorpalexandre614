package com.revature.service;

import com.revature.classes.TransactionObject;

public interface BankService {
	
	public boolean checkAccountStatus(String pin, String typeTarget);
	public int checkBalance(String pin, String typeTarget);
	public boolean logTransaction(TransactionObject transaction);
	public void retrieveLog();
	public String retrieveAccountInfo(String pin);	

}
