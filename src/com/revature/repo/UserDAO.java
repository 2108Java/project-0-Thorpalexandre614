package com.revature.repo;

import com.revature.classes.Customer;
import com.revature.classes.Employee;
import com.revature.classes.User;

public interface UserDAO {
	
	public boolean addUser(Customer newUser);
	public boolean openSavings(int openingBalance, String pin);
	public boolean closeAccount(Customer username);
	public boolean changeUsername(String newUsername, String pin);
	public boolean changePassword(String newPassword, String pin);
	public boolean makeEmployee(Employee employee);
	public boolean approveUser(String pin);

}
