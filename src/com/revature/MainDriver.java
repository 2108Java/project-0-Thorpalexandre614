package com.revature;

import com.revature.classes.TransactionObject;
import com.revature.classes.User;
import com.revature.presentation.Menu;
import com.revature.repo.BankImp;
import com.revature.repo.UserImp;
import com.revature.service.Service;

public class MainDriver {
	
	public static void main(String[] args) {
		
		UserImp ui = new UserImp();
		Service service= new Service();
		Menu start = new Menu();
		start.displayMain();
		//System.out.println(service.validate("Thorpalex"));
		//System.out.println(ui.changeUsername("Thorpalex", "6141"));
		//service.retrieveAccountInfo("6141");
	}

}
