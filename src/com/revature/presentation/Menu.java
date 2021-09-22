package com.revature.presentation;

import java.util.Scanner;

import com.revature.classes.Customer;
import com.revature.repo.BankImp;
import com.revature.repo.UserImp;
import com.revature.service.Service;

public class Menu {
	
	private void mainMenu() {
		System.out.println("1) Log in");
		System.out.println("2) Employee log in");
		System.out.println("3) New user");
		
	}
	
	private void employeeMenu() {
		System.out.println("1) View user data");
		System.out.println("2) Approve user");
		System.out.println("3) View transaction log");
		System.out.println("4) Log out");
	}
	
	public void customerMenu() {
		System.out.println("1) Check balance");
		System.out.println("2) Withdraw");
		System.out.println("3) Deposit");
		System.out.println("4) Internal transfer");
		System.out.println("5) External transfer");
		System.out.println("6) Open savings");
		System.out.println("7) Close account");
		System.out.println("8) Change username");
		System.out.println("9) Change password");
		System.out.println("10) Log out");
			
	}
	
	public void displayMain() {
		
		Service service = new Service();
		BankImp bi = new BankImp();
		UserImp ui = new UserImp();
		Scanner scanner = new Scanner(System.in);
		boolean runningMain = true;
		boolean runningLogin = true;
		
		mainMenu();
		
		while(runningMain) {
			
			String input1 = scanner.nextLine();
			
			switch(input1) {
			
			case "1":
				
				boolean loggedIn = false;
				
				while(runningLogin && !loggedIn) {
				
					System.out.println("Username");
					String usernameInput = scanner.nextLine(); 
					
					System.out.println("Password");
					String passwordInput = scanner.nextLine();
					
					
					
					try {
						loggedIn = service.login(usernameInput, passwordInput);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(loggedIn) {
						break;
					} else {
						System.out.println("Login insuccessful");
					}
				}
				
				customerMenu();
				
				while(loggedIn) {
					
					String input2 = scanner.nextLine();
				
					switch(input2) {
					
					case "1":
						
						System.out.println("Input your pin");
						String pinCb = scanner.nextLine();
						
						System.out.println("Account type");
						String typeTargetCb = scanner.nextLine();
						
						service.checkBalance(pinCb, typeTargetCb);
						
						break;
					
					case "2":
						
						System.out.println("Input your pin");
						String pinWithdraw = scanner.nextLine();
						
						System.out.println("Input amount");
						int amountWithdraw = scanner.nextInt();
						
						System.out.println("Input account type");
						String typeOriginWithdraw = scanner.nextLine();
						
						boolean statusWithdraw = bi.withdraw(amountWithdraw, pinWithdraw, typeOriginWithdraw);
						
						if(statusWithdraw) {
							System.out.println("Withdrawl successful");
						} else {
							System.out.println("Withdrawl unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "3": 
						
						System.out.println("Input your pin");
						String pinDeposit = scanner.nextLine();
						
						System.out.println("Input amount");
						int amountDeposit = scanner.nextInt();
						
						System.out.println("Input account type");
						String typeTargetDeposit = scanner.nextLine();
						
						boolean statusDeposit = bi.deposit(amountDeposit, pinDeposit, typeTargetDeposit);
						
						if(statusDeposit) {
							System.out.println("Deposit successful");
						} else {
							System.out.println("Deposit unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "4":
						
						System.out.println("Input your pin");
						String pinIt = scanner.nextLine();
						
						System.out.println("Input amount");
						int amountIt = scanner.nextInt();
						
						System.out.println("Input account type to transfer from");
						String typeOriginIt = scanner.nextLine();
						
						System.out.println("Input account type to transfer to");
						String typeTargetIt = scanner.nextLine();
						
						boolean statusIt = bi.internalTransfer(amountIt, pinIt, typeOriginIt, typeTargetIt);
						
						if(statusIt) {
							System.out.println("Transfer successful");
						} else {
							System.out.println("Transfer unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "5":
						
						System.out.println("Input your pin");
						String pinEt = scanner.nextLine();
						
						System.out.println("Input amount");
						int amountEt = scanner.nextInt();
				
						System.out.println("Input the username of the account to be transfered into");
						String usernameTargetEt = scanner.nextLine();
						
						System.out.println("Input account type to transfer from");
						String typeOriginEt = scanner.nextLine();
						
						System.out.println("Input account type to transfer to");
						String typeTargetEt = scanner.nextLine();
						
						boolean status = bi.externalTransfer(amountEt, pinEt, typeOriginEt, usernameTargetEt, typeTargetEt);
						
						if(status) {
							System.out.println("Transfer successful");
						} else {
							System.out.println("Transfer unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "6":
						
						System.out.println("Input your pin");
						String pinOs = scanner.nextLine();
						
						System.out.println("Input opening balance");
						int openingBalance = scanner.nextInt();
						
						boolean statusOs = ui.openSavings(openingBalance, pinOs);
						
						if(statusOs) {
							System.out.println("Open savings successful");
						} else {
							System.out.println("Open savings unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "7":
						
						System.out.println("Input your pin");
						String pinCa = scanner.nextLine();
						
						System.out.println("Input username");
						String usernameInput = scanner.nextLine();
						
						System.out.println("Input Password");
						String passwordInput = scanner.nextLine();
						
						System.out.println("Account type to close");
						String typeTargetCo = scanner.nextLine();
						
						System.out.println("Type 'Confirm' to close account or 'Cancel' to go back");
						String confirmation = scanner.nextLine();
						
						if(confirmation.equals("Confirm")) {
							
							Customer customer = new Customer();
						
							customer.setPin(pinCa);
							customer.setUser(usernameInput);
							customer.setPass(passwordInput);
							customer.setBalance(0);
							customer.setType(typeTargetCo);
							
							ui.closeAccount(customer);
							
							loggedIn = false;
						} else {
							System.out.println("Account closure cancelled");
						}
						break;
						
					case "8":
						
						System.out.println("Input your pin");
						String pinCu = scanner.nextLine();
						
						System.out.println("Input new username");
						String newUsername = scanner.nextLine();
						
						boolean statusCu = ui.changeUsername(newUsername, pinCu);
						
						if(statusCu) {
							System.out.println("Change username successful");
						} else {
							System.out.println("Change username unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "9":
						
						System.out.println("Input your pin");
						String pinCp = scanner.nextLine();
						
						System.out.println("Input new password");
						String newPassword = scanner.nextLine();
						
						boolean statusCp = ui.changePassword(newPassword, pinCp);
						
						if(statusCp) {
							System.out.println("Change password successful");
						} else {
							System.out.println("Change password unsuccessful");
						}
						
						customerMenu();
						
						break;
						
					case "10":
						
						loggedIn = false;
						mainMenu();
						break;				
				}
				
				}
				break;
				
			case "2":
				
				boolean loggedInEmployee = false;
				
				while(runningLogin && !loggedInEmployee) {
				
					System.out.println("Username");
					String usernameInputEmployee = scanner.nextLine(); 
					
					System.out.println("Password");
					String passwordInputEmployee = scanner.nextLine();
					
					
					
					try {
						loggedInEmployee = service.login(usernameInputEmployee, passwordInputEmployee);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(loggedInEmployee) {
						
						employeeMenu();
						break;
						
					} else {
						System.out.println("Login insuccessful");
					}
				}
				
				
				
				while(loggedInEmployee) {
					
					String input3 = scanner.nextLine();
					
					switch(input3) {
				
					case "1":
						
						System.out.println("Input customer pin");
						String pinCustomerRai = scanner.nextLine();
						
						service.retrieveAccountInfo(pinCustomerRai);
						
						break;
						
					case "2":
						
						System.out.println("Input customer pin");
						String pinCustomerApprove = scanner.nextLine();
						
						boolean statusCa = ui.approveUser(pinCustomerApprove);
				
						if(statusCa) {
							System.out.println("Approval successful");
						} else {
							System.out.println("Approval unsuccessful");
						}
						
						employeeMenu();
						
						break;
						
					case "3":
						
						service.retrieveLog();
						
						employeeMenu();
						
						break;
						
					case "4":
						
						loggedInEmployee = false;
						mainMenu();						
						break;
					
				
				}
				}
				break;
				
			case "3":
				
			boolean status1 = false;
			String newPin = null;
			String newUsername = null;
			
			while(!status1) {
				
				System.out.println("Choose a pin");
				newPin = scanner.nextLine();
				if(service.authenticatePIN(newPin)) {
					System.out.println("Pin already in use");
				} else {
					status1 = true;
				}
				
			}
			
			boolean status2 = false;
			
			while(!status2) {
				
				System.out.println("Choose a username");
				newUsername = scanner.nextLine();
				
				if(service.validate(newUsername)) {
					System.out.println("Pin already in use");
				} else {
					status2 = true;
				}
				
			}
		
			System.out.println("Choose a password");
			String newPassword = scanner.nextLine();
				
			System.out.println("Enter starting balance");
			int startingBalance = scanner.nextInt();
			
			
			Customer customer = new Customer();
			
			customer.setPin(newPin);
			customer.setUser(newUsername);
			customer.setPass(newPassword);
			customer.setBalance(startingBalance);
			customer.setType("checking");
			
			if(status1 && status2) {
				ui.addUser(customer);
				System.out.println("Account successfully opened");
				mainMenu();
			}
	
			}
			
			
			
		}
		
	}

}
