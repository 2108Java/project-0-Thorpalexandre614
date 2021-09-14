package com.revature;

import com.revature.repo.Transaction;

public class MainDriver {
	
	public static void main(String[] args) {
		
		Transaction t1 = new Transaction();
		t1.withdraw(50, "1234");
		
	}

}
