package com.revature.repo;

public interface BankDAO {
	
	//CREATE
	
	public boolean addUser(String username, String password, int pin);
	public boolean logTransaction(int amount, String transactionType, int originPin, int targetPin);
	
	//READ(SELECT)
	
	//UPDATE
	
	//DELETE

}
