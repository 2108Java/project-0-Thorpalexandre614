package com.revature.repo;

import com.revature.classes.TransactionObject;
import com.revature.classes.User;

public interface BankDAO {
	
	//CREATE
	
	public boolean addUser(User newUser);
	public boolean logTransaction(TransactionObject transaction);
	public boolean openAccount(int openingBalance, int pin);
	
	//READ(SELECT)
	
	public int readBalance(int pin);
	public String retrieveLog(int transactionId);
	public String retrieveAccountInfo(int pin);
	//public ? retrieveTransactionId(? timestamp);
	
	
	//UPDATE
	
	public boolean changeUsername(String username);
	public boolean changePassword(String password);
	public boolean makeEmployee(String username);
	
	//DELETE
	
	public boolean removeUser(User username);
	public boolean closeAccount(User username);

}
