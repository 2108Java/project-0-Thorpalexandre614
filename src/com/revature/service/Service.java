package com.revature.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.classes.TransactionObject;

public class Service implements BankService{
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String usernameServer = "postgres";
	String passwordServer = "Sixfourteen614";

	@Override
	public boolean checkAccountStatus(String pin, String accountTarget) {
		
		boolean approved = false;
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
		
			String checkBalance = "SELECT approved FROM user_data WHERE pin = ? AND account_type = ?";
			
			PreparedStatement ps = connection.prepareStatement(checkBalance);
			
			ps.setString(1, pin);
			ps.setString(2, accountTarget);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				approved = rs.getBoolean("approved");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return approved;
	}

	@Override
	public int checkBalance(String pin, String accountTarget) {
		int displayBalance = 0;
		
		Service approved = new Service();
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			if(approved.checkAccountStatus(pin, accountTarget)) {
				String checkBalance = "SELECT balance FROM user_data WHERE pin = ? AND account_type = ?";
			
				PreparedStatement ps = connection.prepareStatement(checkBalance);
				
				ps.setString(1, pin);
				ps.setString(2, accountTarget);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					displayBalance = rs.getInt("balance");
				}
			}else {
				System.out.println("Account has not been approved.");
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return displayBalance;
	}
	
	@Override
	public boolean logTransaction(TransactionObject transaction) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String retrieveLog(int transactionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String retrieveAccountInfo(String pin) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
