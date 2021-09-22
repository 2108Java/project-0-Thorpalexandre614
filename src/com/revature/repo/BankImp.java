package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.classes.TransactionObject;
import com.revature.service.Service;

public class BankImp implements BankDAO {
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String usernameServer = "postgres";
	String passwordServer = "Sixfourteen614";

	@Override
	public boolean withdraw(int amount, String pin, String typeOrigin) {

		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;		
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			int balance = service.checkBalance(pin, typeOrigin);
			boolean approved = service.checkAccountStatus(pin, typeOrigin);
			
			int newBalance = 0;
			
			if(approved) {
				if(balance >= amount) {
					newBalance = (balance - amount);
					status1 = true;
				}else {
					 
					System.out.println("Insufficient Funds");
					
				}
			}
			
			if(status1) {
				
				TransactionObject transaction = new TransactionObject("withdrawl", pin, typeOrigin, "external", "external", amount);
			
				status2 = service.logTransaction(transaction);
				//Update the balance with new amount
				String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
				ps3.setInt(1, newBalance);
				ps3.setString(2, pin);
				ps3.setString(3, typeOrigin);
			
				ps3.execute();

			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status2;

	}

	@Override
	public boolean deposit(int amount, String pin, String typeTarget) {

		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			int balance = service.checkBalance(pin, typeTarget);
			boolean approved = service.checkAccountStatus(pin, typeTarget);			
			int newBalance = 0;
			
			if(approved) {
				newBalance = (balance + amount);	
			
				status1 = true;
			}else {
				System.out.println("Account has not been approved.");
			}
			
			if(status1) {
				
				TransactionObject transaction = new TransactionObject("deposit", "external", "external", pin, typeTarget, amount);
				
				status2 = service.logTransaction(transaction);
			
				String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
			
				PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
				ps3.setInt(1, newBalance);
				ps3.setString(2, pin);
				ps3.setString(3, typeTarget);
			
				ps3.execute();
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status2;
	}
	
	@Override
	public boolean internalTransfer(int amount, String pin, String typeOrigin, String typeTarget){

		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			boolean originApproved = service.checkAccountStatus(pin, typeOrigin);
			int originBalance = service.checkBalance(pin, typeOrigin);
			boolean targetApproved = service.checkAccountStatus(pin, typeTarget);
			int targetBalance = service.checkBalance(pin, typeTarget);
			
			int newBalanceOrigin = 0;
			int newBalanceTarget = 0;
				
			if(originApproved && targetApproved) {
				if(originBalance >= amount) {
					newBalanceOrigin = originBalance - amount;
					newBalanceTarget = targetBalance + amount;
					status1 = true;
				}else {
						System.out.println("Insufficient funds.");
				}
				status2 = true;
			}else {
				System.out.println("Account has not been approved.");
			}	
				
				
			if(status2) {
					
				TransactionObject transaction = new TransactionObject("internal transfer", pin, typeOrigin, "internal", typeTarget, amount);
				service.logTransaction(transaction);
				
				String balanceUpdateOrigin = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
					
				PreparedStatement ps4 = connection.prepareStatement(balanceUpdateOrigin);
					
				ps4.setInt(1, newBalanceOrigin);
				ps4.setString(2, pin);
				ps4.setString(3, typeOrigin);
					
				ps4.execute();
					
				String balanceUpdateTarget = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
					
				PreparedStatement ps5 = connection.prepareStatement(balanceUpdateTarget);
					
				ps5.setInt(1, newBalanceTarget);
				ps5.setString(2, pin);
				ps5.setString(3, typeTarget);
					
				ps5.execute();
					
				status3 = true;
					
				
			}
			

			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status3;
	}

	@Override
	public boolean externalTransfer(int amount, String pinOrigin, String typeOrigin, String usernameTarget, String typeTarget) {
		
		Service service = new Service();
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean status4 = false;
		
		String pinTarget = null;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			String getPin = "SELECT pin FROM user_data WHERE username = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(getPin);
			
			ps1.setString(1, usernameTarget);
			
			ResultSet rs = ps1.executeQuery();
			
			while(rs.next()) {
				pinTarget = rs.getString("pin");
				status1 = true;
			}
			if(status1) {
				boolean originApproved = service.checkAccountStatus(pinOrigin, typeOrigin);
				int originBalance = service.checkBalance(pinOrigin, typeOrigin);
				boolean targetApproved = service.checkAccountStatus(pinTarget, typeTarget);
				int targetBalance = service.checkBalance(pinTarget, typeTarget);
	
	
				int newBalanceOrigin = 0;
				int newBalanceTarget = 0;
					
				if(originApproved && targetApproved) {
					if(originBalance >= amount) {
						newBalanceOrigin = originBalance - amount;
						newBalanceTarget = targetBalance + amount;
						status2 = true;
					}else {
							System.out.println("Insufficient funds.");
					}
					status3 = true;
				}else {
					System.out.println("Account has not been approved.");
				}	
					
				if(status1 && status2) {	
					
					TransactionObject transaction = new TransactionObject("external transfer", pinOrigin, typeOrigin, pinTarget, typeTarget, amount);
					status4 = service.logTransaction(transaction);
				
					String balanceUpdateOrigin = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
					
					PreparedStatement ps2 = connection.prepareStatement(balanceUpdateOrigin);
					
					ps2.setInt(1, newBalanceOrigin);
					ps2.setString(2, pinOrigin);
					ps2.setString(3, typeOrigin);
					
					ps2.execute();
					
					String balanceUpdateTarget = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
					
					PreparedStatement ps3 = connection.prepareStatement(balanceUpdateTarget);
					
					ps3.setInt(1, newBalanceTarget);
					ps3.setString(2, pinTarget);
					ps3.setString(3, typeTarget);
					
					ps3.execute();
		
					}

		
			} else {
				System.out.println("Target account does not exist");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return status4;
	}

}
