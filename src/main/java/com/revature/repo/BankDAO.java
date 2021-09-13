package com.revature.repo;

import com.revature.classes.TransactionObject;
import com.revature.classes.User;

public interface BankDAO {
	
	//CREATE
	
	public boolean addUser(User newUser);
	public boolean logTransaction(TransactionObject transaction);
	
	//READ(SELECT)
	
	//UPDATE
	
	//DELETE

}
