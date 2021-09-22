package com.revature;


import org.junit.Test;

import com.revature.classes.Customer;
import com.revature.repo.BankImp;

public class WithdrawTest {
	
	Customer u1 = new Customer();
	
	BankImp bi = new BankImp();
	
	@Test
	public void testWithdraw() {
		
		u1.setPin("1234");
		//assertTrue();
		
	}

}
