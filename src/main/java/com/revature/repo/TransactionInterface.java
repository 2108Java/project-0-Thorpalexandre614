package com.revature.repo;

public interface TransactionInterface {
	
	public boolean openAccount(int openingBalance, int pin);
	public boolean withdrawl(int amount, int pin);
	public boolean deposit(int amount, int pin);
	public boolean transfer(int amount, int transactionOrigin, int transactionTarget);
}
