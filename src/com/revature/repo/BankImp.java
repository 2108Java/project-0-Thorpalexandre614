package com.revature.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.classes.User;
import com.revature.service.Service;

public class BankImp implements BankDAO {
	
	String server = "localhost";
	String url = "jdbc:postgresql://" + server + "/postgres";
	String usernameServer = "postgres";
	String passwordServer = "Sixfourteen614";

	@Override
	public boolean withdraw(int amount, String pin, String accountOrigin) {

		Service check = new Service();
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean statusFinal = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			int balance = check.checkBalance(pin, accountOrigin);
			boolean approved = check.checkAccountStatus(pin, accountOrigin);
			
			int newBalance = 0;
			
			if(approved) {
				if(balance > amount) {
					newBalance = (balance - amount);
					status1 = true;
				}else {
					 
					System.out.println("Insufficient Funds");
					
				}
			}
			if(approved && status1) {
				
				//Use Transaction log method here
				
				String inputQuery = "INSERT INTO transaction_log(transaction_type, transfer_origin, account_origin, "
					+ "transfer_target, account_target, amount, approved) "
					+ "VALUES('withdrawl', ?, ?, 'external', 'external', ?, true)";
			
				PreparedStatement ps2 = connection.prepareStatement(inputQuery);
			
				ps2.setString(1, pin);
				ps2.setString(2, accountOrigin);
				ps2.setInt(3, amount);
			
			
				ps2.execute();
			
				status2 = true;
				//Update the balance with new amount
				String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
			
				PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
				ps3.setInt(1, newBalance);
				ps3.setString(2, pin);
				ps3.setString(3, accountOrigin);
			
				ps3.execute();
			
				status3 = true;
			}else {
				System.out.println("Account has not been approved.");
			}
			
			
			
			
			if(status1 && status2 && status3) {
				statusFinal = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return statusFinal;

	}

	@Override
	public boolean deposit(int amount, String pin, String accountTarget) {
		// TODO Auto-generated method stub
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean statusFinal = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			String balanceQuery = "SELECT balance, approved FROM user_data WHERE pin = ? AND account_type = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(balanceQuery);

			ps1.setString(1, pin);
			ps1.setString(2, accountTarget);
			
			ResultSet rs = ps1.executeQuery();
			
			status1 = true;
			
			int balance = 0;
			int newBalance = 0;
			boolean approved = false;
			
			while(rs.next()) {
				 balance = rs.getInt("balance");
				 approved = rs.getBoolean("approved");
				 newBalance = (balance + amount);
			}
			
			if(approved) {
				String inputQuery = "INSERT INTO transaction_log(transaction_type, transfer_origin, account_origin, transfer_target, account_target, amount, approved) "
					+ "VALUES('deposit', 'external', 'external', ?, ?, ?, true)";
			
				PreparedStatement ps2 = connection.prepareStatement(inputQuery);
			
				ps2.setString(1, pin);
				ps2.setString(2, accountTarget);
				ps2.setInt(3, amount);
			
			
				ps2.execute();
			
				status2 = true;
			
				String balanceUpdate = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
			
				PreparedStatement ps3 = connection.prepareStatement(balanceUpdate);
			
				ps3.setInt(1, newBalance);
				ps3.setString(2, pin);
				ps3.setString(3, accountTarget);
			
				ps3.execute();
			
				status3 = true;
			}else {
				System.out.println("Account has not been approved.");
			}
			
			
			
			if(status1 && status2 && status3) {
				statusFinal = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return statusFinal;
	}
	
	@Override
	public boolean internalTransfer(int amount, String pin, String accountOrigin, String accountTarget){

		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean statusFinal = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			String balanceQueryOrigin = "SELECT balance, approved FROM user_data WHERE pin = ? AND account_type = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(balanceQueryOrigin);

			ps1.setString(1, pin);
			ps1.setString(2, accountOrigin);
			
			ResultSet rs1 = ps1.executeQuery();
			
			String balanceQueryTarget = "SELECT balance, approved FROM user_data WHERE pin = ? AND account_type = ?";
			
			PreparedStatement ps2 = connection.prepareStatement(balanceQueryTarget);

			ps2.setString(1, pin);
			ps2.setString(2, accountTarget);
			
			ResultSet rs2 = ps2.executeQuery();
			
			int balanceOrigin = 0;
			int balanceTarget = 0;
			int newBalanceOrigin = 0;
			int newBalanceTarget = 0;
			boolean originApproved = false;
			boolean targetApproved = false;
			
			while(rs1.next()) {
				
				balanceOrigin = rs1.getInt("balance");
				originApproved = rs1.getBoolean("approved");
				
					//throw new InsufficientFundsException("Insufficient Funds.");
			}
			while(rs2.next()) {
				 balanceTarget = rs2.getInt("balance");
				 targetApproved = rs2.getBoolean("approved");
			}
			
			if(originApproved && targetApproved) {
				if(balanceOrigin >= amount) {
					newBalanceOrigin = balanceOrigin - amount;
					newBalanceTarget = balanceTarget + amount;
				}else {
						System.out.println("Insufficient funds.");
				}
				
				
				status1 = true;
				
				String inputQuery = "INSERT INTO transaction_log(transaction_type, transfer_origin, "
						+ "account_origin, transfer_target, account_target, amount, approved) "
						+ "VALUES('internal transfer', ?, ?, ?, ?, ?, false)";
				
				PreparedStatement ps3 = connection.prepareStatement(inputQuery);
				
				ps3.setString(1, pin);
				ps3.setString(2, accountOrigin);
				ps3.setString(3, pin);
				ps3.setString(4, accountTarget);
				ps3.setInt(5, amount);
				
				ps3.execute();
				
				status2 = true;
				
				String balanceUpdateOrigin = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps4 = connection.prepareStatement(balanceUpdateOrigin);
				
				ps4.setInt(1, newBalanceOrigin);
				ps4.setString(2, pin);
				ps4.setString(3, accountOrigin);
				
				ps4.execute();
				
				String balanceUpdateTarget = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps5 = connection.prepareStatement(balanceUpdateTarget);
				
				ps5.setInt(1, newBalanceTarget);
				ps5.setString(2, pin);
				ps5.setString(3, accountTarget);
				
				ps5.execute();
				
				status3 = true;
			}else {
				System.out.println("Account has not been approved.");
			}
			
			
			if(status1 && status2 && status3) {
				statusFinal = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return statusFinal;
	}

	@Override
	public boolean externalTransfer(int amount, String pinOrigin, String accountOrigin, String pinTarget, String accountTarget) {
		
		boolean status1 = false;
		boolean status2 = false;
		boolean status3 = false;
		boolean statusFinal = false;
		
		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
			
			String balanceQueryOrigin = "SELECT balance, approved FROM user_data WHERE pin = ? AND account_type = ?";
			
			PreparedStatement ps1 = connection.prepareStatement(balanceQueryOrigin);

			ps1.setString(1, pinOrigin);
			ps1.setString(2, accountOrigin);
			
			ResultSet rs1 = ps1.executeQuery();
			
			String balanceQueryTarget = "SELECT balance, approved FROM user_data WHERE pin = ? AND account_type = ?";
			
			PreparedStatement ps2 = connection.prepareStatement(balanceQueryTarget);

			ps2.setString(1, pinTarget);
			ps2.setString(2, accountTarget);
			
			ResultSet rs2 = ps2.executeQuery();
			
			int balanceOrigin = 0;
			int balanceTarget = 0;
			int newBalanceOrigin = 0;
			int newBalanceTarget = 0;
			boolean originApproved = false;
			boolean targetApproved = false;
			
			while(rs1.next()) {
				
				balanceOrigin = rs1.getInt("balance");
				originApproved = rs1.getBoolean("approved");
				
					//throw new InsufficientFundsException("Insufficient Funds.");
			}
			while(rs2.next()) {
				 balanceTarget = rs2.getInt("balance");
				 targetApproved = rs2.getBoolean("approved");
			}
			
			if(originApproved && targetApproved) {
				if(balanceOrigin >= amount) {
					newBalanceOrigin = balanceOrigin - amount;
					newBalanceTarget = balanceTarget + amount;
				}else {
						System.out.println("Insufficient funds.");
				}
				
				
				status1 = true;
				
				String inputQuery = "INSERT INTO transaction_log(transaction_type, transfer_origin, "
						+ "account_origin, transfer_target, account_target, amount, approved) "
						+ "VALUES('external transfer', ?, ?, ?, ?, ?, false)";
				
				PreparedStatement ps3 = connection.prepareStatement(inputQuery);
				
				ps3.setString(1, pinOrigin);
				ps3.setString(2, accountOrigin);
				ps3.setString(3, pinTarget);
				ps3.setString(4, accountTarget);
				ps3.setInt(5, amount);
				
				ps3.execute();
				
				status2 = true;
				
				String balanceUpdateOrigin = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps4 = connection.prepareStatement(balanceUpdateOrigin);
				
				ps4.setInt(1, newBalanceOrigin);
				ps4.setString(2, pinOrigin);
				ps4.setString(3, accountOrigin);
				
				ps4.execute();
				
				String balanceUpdateTarget = "UPDATE user_data SET balance = ? WHERE pin = ? AND account_type = ? ";
				
				PreparedStatement ps5 = connection.prepareStatement(balanceUpdateTarget);
				
				ps5.setInt(1, newBalanceTarget);
				ps5.setString(2, pinTarget);
				ps5.setString(3, accountTarget);
				
				ps5.execute();
				
				status3 = true;
			}else {
				System.out.println("Account has not been approved.");
			}
			
			
			if(status1 && status2 && status3) {
				statusFinal = true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return statusFinal;
	}

	
	//move over to the userDAO
	
//	@Override
//	public boolean addUser(User newUser) {
//		// TODO Auto-generated method stub
//		boolean status = false;
//		
//		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
//			
//			String addUser = "INSERT INTO user_data(pin, username, pass, balance, account_type) VALUES(?, ?, ?, ?, ?)";
//			
//			PreparedStatement ps = connection.prepareStatement(addUser);
//			
//			ps.setString(1, newUser.getPin());
//			ps.setString(2, newUser.getUser());
//			ps.setString(3, newUser.getPass());
//			ps.setInt(4, newUser.getBalance());
//			ps.setString(5, newUser.getType());
//			
//			ps.execute();
//			
//			status = true;
//			
//		} catch(SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return status;
//	}
//
//	@Override
//	public boolean openSavings(int openingBalance, String pin) {
//		boolean status1 = false;
//		
//		boolean statusFinal = false;
//		
//		
//		try(Connection connection = DriverManager.getConnection(url,usernameServer,passwordServer)){
//			
//			//Check if the user has an account already before they can make another
//			
//			String checkPin = "SELECT * FROM user_data WHERE pin = ?";
//			
//			PreparedStatement ps1 = connection.prepareStatement(checkPin);
//			
//			ps1.setString(1, pin);
//			
//			ResultSet rs = ps1.executeQuery();
//			
//			String username = null;
//			String password = null;
//			String account = null;
//			boolean approved = false;
//			
//			while(rs.next()) {
//				username = rs.getString("username");
//				password = rs.getString("pass");
//				account = rs.getString("account_type");
//				approved = rs.getBoolean("approved");
//			}
//			
//			if(approved) {
//				
//		
//				String openAccount = "INSERT INTO user_data(pin, username, pass, balance, account_type) VALUES(?,?,?,?,'savings')";
//				
//				PreparedStatement ps2 = connection.prepareStatement(openAccount);
//				
//				ps2.setString(1, pin);
//				ps2.setString(2, username);
//				ps2.setString(3, password);
//				ps2.setInt(4, openingBalance);
//				
//				ps2.execute();
//				
//
//			}
//
//			
//		} catch (SQLException e2) {
//			// TODO Auto-generated catch block
//			e2.printStackTrace();
//		}
//		return statusFinal;
//	}
//	
//	@Override
//	public boolean closeAccount(User username) {
//		
//		boolean status = false;
//		
//		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
//			
//			String closeAccount = "DELETE FROM user_data WHERE pin = ?";
//			
//			PreparedStatement ps = connection.prepareStatement(closeAccount);
//			
//			ps.setString(1, username.getPin());
//			
//			ps.execute();		
//			
//			status = true;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return status;
//	}
//
//	@Override
//	public boolean approveUser(String pin) {
//		
//		boolean status = false;
//		
//		try(Connection connection = DriverManager.getConnection(url, usernameServer, passwordServer)){
//			
//			String approveUser = "UPDATE user_data SET approved = true WHERE pin = ?";
//			
//			PreparedStatement ps = connection.prepareStatement(approveUser);
//			
//			ps.setString(1, pin);
//			
//			ps.execute();
//			
//			status = true;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return status;
//	}

}
