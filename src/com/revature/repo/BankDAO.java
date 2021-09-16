package com.revature.repo;


import com.revature.classes.User;

public interface BankDAO {
	
	//CREATE
	
	public boolean addUser(User newUser);
	public boolean openSavings(int openingBalance, String pin);
	
	public int checkBalance(String pin);
	public String retrieveLog(int transactionId);
	public String retrieveAccountInfo(String pin);
	
	//UPDATE
	
	public boolean changeUsername(String username);
	public boolean changePassword(String password);
	public boolean approveUser(String pin);
	public boolean makeEmployee(String username);
	
	public boolean withdraw(int amount, String pin, String accountOrigin);
	public boolean deposit(int amount, String pin,String accountTarget);
	public boolean internalTransfer(int amount, String pin, String accountOrigin, String accountTarget);
	public boolean externalTransfer(int amount, String pinOrigin, String pinTarget, String accountOrigin, String accountTarget);
	
	//DELETE
	public boolean closeAccount(User username);

}
