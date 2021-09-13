package com.revature.repo;

public interface TransactionInterface {
	
	public boolean withdraw(int amount, String pin);
	public boolean deposit(int amount, String pin);
	public boolean transfer(int amount, String transactionOrigin, String transactionTarget);
}

