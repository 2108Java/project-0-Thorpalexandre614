package com.revature.classes;

public class TransactionObject {
	
	private String transactionType;
	private String pinOrigin;
	private String typeOrigin;
	private String pinTarget;
	private String typetarget;
	private int amount;
	
	public TransactionObject() {
		
	}
	
	public TransactionObject(String transactionType, String pinOrigin, String typeOrigin, String pinTarget, String typetarget, int amount) {
		
	}
	
	//Getters
	
	//Setters
	public void setType(String transactionType){
		this.transactionType = transactionType;
	}

}
