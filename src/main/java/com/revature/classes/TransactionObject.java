package com.revature.classes;


public class TransactionObject  {
	
	private int amount;
	private String transactionType;
	private int transactionOrigin;
	private int transactionTarget;
	private int balanceOrigin;
	private int balanceTarget;
	
	public TransactionObject(int amount, String transactionType, int transactionOrigin, int transactionTarget, int balanceOrigin, int balanceTarget) {
		this.amount = amount;
		this.transactionType = transactionType;
		this.transactionOrigin = transactionOrigin;
		this.transactionTarget = transactionTarget;
		this.balanceOrigin = balanceOrigin;
		this.balanceTarget = balanceTarget;
	}
	
	//Getters 
	
	public int getAmount() {
		return this.amount;
	}
	
	public String getType() {
		return this.transactionType;
	}
	
	public int getOrigin() {
		return this.transactionOrigin;
	}
	
	public int getTarget() {
		return this.transactionTarget;
	}
	
	public int getOriginBalance() {
		return this.balanceOrigin;
	}
	
	public int getTargetBalance() {
		return this.balanceTarget;
	}
	
	//Setters
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setType(String transactionType) {
		//don't forget to restrict the input type
		this.transactionType = transactionType;
	}
	
	public void setTransactionOrigin(int pin) {
		this.transactionOrigin = pin;
	}
	
	public void setTransactionTarget(int pin) {
		this.transactionTarget = pin;
	}
	
	public void setOriginBalance(int amount) {
		this.transactionOrigin = amount;
	}
	
	public void setTargetBalance(int amount) {
		this.balanceTarget = amount;
	}
	
	
	

}
