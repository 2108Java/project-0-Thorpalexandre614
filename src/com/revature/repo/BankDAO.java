package com.revature.repo;


import com.revature.classes.User;

public interface BankDAO {
	
	//CREATE
	
	public boolean addUser(User newUser);
	public boolean openSavings(int openingBalance, String pin);
	
	//READ(SELECT)
	
	public int readBalance(String pin);
	public String retrieveLog(int transactionId);
	public String retrieveAccountInfo(String pin);
	//public ? retrieveTransactionId(? timestamp);
	
	
	//UPDATE
	
	public boolean changeUsername(String username);
	public boolean changePassword(String password);
	public boolean approveUser(String pin);
	public boolean makeEmployee(String username);
	
	public boolean withdraw(int amount, String pin, String accountOrigin) throws InsufficientFundsException;
	public boolean deposit(int amount, String pin,String accountTarget);
	public boolean internalTransfer(int amount, String pin, String accountOrigin, String accountTarget) throws InsufficientFundsException;
	public boolean externalTransfer(int amount, String pinOrigin, String pinTarget, String accountOrigin, String accountTarget) throws InsufficientFundsException;
	
	//DELETE
	
	public boolean removeUser(User username);
	public boolean closeAccount(User username);

}
