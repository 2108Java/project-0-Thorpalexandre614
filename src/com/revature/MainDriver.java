package com.revature;

import com.revature.classes.User;
import com.revature.repo.BankImp;
import com.revature.repo.InsufficientFundsException;

public class MainDriver {
	
	public static void main(String[] args) {
		
		User alex = new User();
		alex.setUser("Thorpalex");
		alex.setPass("password");
		alex.setPin("6141");
		alex.setBalance(100);
		alex.setType("checking");
		
		BankImp bi = new BankImp();
//		bi.addUser(alex);
//		bi.approveUser("6141");
		bi.deposit(100, "6141", "checking");
		bi.approveUser("1111");
		bi.externalTransfer(50, "6141", "checking", "1111", "checking");
//		bi.openSavings(100, "6141");
//		try {
//			bi.withdraw(50, alex.getPin(), "savings");
//		} catch (InsufficientFundsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
		User rando = new User();
		rando.setUser("Random");
		rando.setPass("password");
		rando.setPin("1111");
		rando.setBalance(100);
		rando.setType("checking");
	
//		BankImp bi2 = new BankImp();
//		bi2.addUser(rando);
		
//		bi.deposit(100, "6141", "checking");
//		
//		try {
//			bi2.externalTransfer(100, "6141", "checking", "1111", "checking");
//		} catch (InsufficientFundsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
	}

}
