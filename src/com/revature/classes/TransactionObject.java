package com.revature.classes;

public class TransactionObject {
	
	private String transactionType;
	private String pinOrigin;
	private String typeOrigin;
	private String pinTarget;
	private String typeTarget;
	private int amount;
	
	public TransactionObject() {
		
	}
	
	public TransactionObject(String transactionType, String pinOrigin, String typeOrigin, String pinTarget, String typeTarget, int amount) {
		
	}
	
	//Getters
	
	public String getTransactionType() {
		return this.transactionType;
	}
	
	public String getPinOrigin() {
		return this.pinOrigin;
	}
	
	public String getTypeOrigin() {
		return this.typeOrigin;
	}
	public String getPinTarget() {
		return this.pinTarget;
	}
	
	public String getTypeTarget() {
		return this.typeTarget;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	//Setters
	public void setTransactionType(String transactionType){
		this.transactionType = transactionType;
	}
	
	public void setPinOrigin(String pinOrigin) {
		 this.pinOrigin = pinOrigin;
	}
	
	public void setTypeOrigin(String typeOrigin) {
		this.typeOrigin = typeOrigin;
	}
	public void setPinTarget(String pinTarget) {
		this.pinTarget = pinTarget;
	}
	
	public void setTypeTarget(String typeTarget) {
		this.typeTarget = typeTarget;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
