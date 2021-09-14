package com.revature;

import com.revature.classes.User;
import com.revature.repo.BankImp;

public class MainDriver {
	
	public static void main(String[] args) {
		
		User u1 = new User();
		u1.setPin("1234");
		u1.setUser("User1");
		u1.setPass("password");
		u1.setBalance(100);
		u1.setEmployee(false);
		
		BankImp bi = new BankImp();
		bi.addUser(u1);
		
		
		
		
		
	}

}
