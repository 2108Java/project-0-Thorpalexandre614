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
		
		boolean status = false;
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
		
		
			String inputQuery = "INSERT INTO transaction_log(transaction_type, transfer_origin, type_origin, "
				+ "transfer_target, type_target, amount, approved) "
				+ "VALUES(?, ?, ?, ?, ?, ?, true)";
		
			PreparedStatement ps2 = connection.prepareStatement(inputQuery);
		
			ps2.setString(1, transaction.getTransactionType());
			ps2.setString(2, transaction.getPinOrigin());
			ps2.setString(3, transaction.getTypeOrigin());
			ps2.setString(4, transaction.getPinTarget());
			ps2.setString(5, transaction.getTypeTarget());
			ps2.setInt(6, transaction.getAmount());
		
			ps2.execute();
			
			status = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	@Override
	public void retrieveLog() {
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			String inputQuery = "SELECT * FROM transaction_log";
			
			PreparedStatement ps = connection.prepareStatement(inputQuery); 
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("transaction_id") + " " + rs.getString("transaction_type") + " " + rs.getString("transfer_origin") 
				+ " " + rs.getString("type_origin") + " " + rs.getString("transfer_target") + " " + rs.getString("type_target") 
				+ " " + rs.getInt("amount") + " " + rs.getBoolean("approved"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retrieveAccountInfo(String pin) {
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			String inputQuery = "SELECT * FROM user_data WHERE pin = ?";
			
			PreparedStatement ps = connection.prepareStatement(inputQuery); 
			
			ps.setString(1, pin);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getInt("pin") + " " + rs.getString("username") + " " + rs.getString("pass") 
				+ " " + rs.getString("balance") + " " + rs.getString("account_type") + " " + rs.getBoolean("approved"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean validate(String username) {
		
		boolean status = false;
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			String inputQuery = "SELECT username FROM user_data";
			
			PreparedStatement ps = connection.prepareStatement(inputQuery); 
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getString("username").equals(username)) {
					status = true;
					break;
				} else {
					continue;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean login(String username, String password) {

		Service service = new Service();
		
		boolean status1 = service.validate(username);
		
		boolean status2 = false;
		
		if(status1) {
			
			try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
				
				String inputQuery = "SELECT pass FROM user_data WHERE username = ?";
				
				PreparedStatement ps = connection.prepareStatement(inputQuery); 
				
				ps.setString(1, username);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					
					if(rs.getString("pass").equals(password)) {
						status2 = true;
						break;
					} else {
						System.out.println("Incorrect password");
					}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			System.out.println("Account does not exist");
		}
		
		return status2;
	}

	@Override
	public boolean authenticatePIN(String pin) {
		
		boolean status = false;
		
		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
			
			String inputQuery = "SELECT pin FROM user_data";
			
			PreparedStatement ps = connection.prepareStatement(inputQuery); 
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getString("pin").equals(pin)) {
					
					status = true;
					break;
					
				} else {
					continue;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	

	
	
	

}
