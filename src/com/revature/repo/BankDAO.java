package com.revature.repo;


import com.revature.classes.User;

public interface BankDAO {
	
	public boolean withdraw(int amount, String pin, String accountOrigin);
	public boolean deposit(int amount, String pin,String accountTarget);
	public boolean internalTransfer(int amount, String pin, String accountOrigin, String accountTarget);
	public boolean externalTransfer(int amount, String pinOrigin, String pinTarget, String accountOrigin, String accountTarget);
	
}
