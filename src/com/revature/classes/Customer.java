package com.revature.classes;

public class Customer extends User {
	
	private int balance; 
	
	public int getBalance() {
		return this.balance;
	}

	@Override
	public void setBalance(int amount) {
		this.balance = amount;
		
	}

}
