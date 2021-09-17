package com.revature.repo;

import com.revature.classes.User;

public interface UserDAO {
	
	public boolean addUser(User newUser);
	public boolean openSavings(int openingBalance, String pin);
	public boolean closeAccount(User username);
	public boolean changeUsername(String username);
	public boolean changePassword(String password);
	public boolean approveUser(String pin);
	public boolean makeEmployee(String username);

}
