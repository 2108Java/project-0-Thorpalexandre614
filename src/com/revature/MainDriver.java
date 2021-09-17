package com.revature;

import com.revature.classes.TransactionObject;
import com.revature.classes.User;
import com.revature.repo.BankImp;
import com.revature.repo.UserImp;
import com.revature.service.Service;

public class MainDriver {
	
	public static void main(String[] args) {
		
		User alex = new User();
		alex.setUser("Thorpalex");
		alex.setPass("password");
		alex.setPin("6141");
		alex.setBalance(100);
		alex.setType("checking");
		
		UserImp ui = new UserImp();
		BankImp bi = new BankImp();
		Service service = new Service();
		
		TransactionObject transaction = new TransactionObject("deposit", "external", "external", "1234", "checking", 100);
		
//		System.out.println(transaction.getAmount());
		
//		service.logTransaction(transaction);
		
//		ui.addUser(alex);
//		ui.approveUser("6141");
		bi.withdraw(100, "6141", "checking");
//		bi.approveUser("1111");
//		bi.externalTransfer(50, "6141", "checking", "1111", "checking");
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
		
//		service.retrieveLog();
		
	}

}
