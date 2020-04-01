package com.revature.controller;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.service.BankService;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordTooShortException;

public class Controller {

  //private static Set<Account> accounts = new HashSet<Account>();
  
  final static Logger logger = Logger.getLogger(Controller.class);
  public static boolean  LoggedIn = false;
  
  private static BankService bs;
  {
	  bs = new BankService();
  }
  
  List<Account> accounts = bs.getAllAccounts();
  
  private static Scanner input = new Scanner(System.in);
  private static String currentUser;
  
 // private static List<Account> allAccounts = bs.getAllAccounts();
  
  public static void welcome() {
	  
	while(!LoggedIn) {
		System.out.println("\n");
	    System.out.println("Welcome to your local Bank");
	    System.out.println("Press 1 to Login");
	    System.out.println("Press 2 to Create an Account");
	    System.out.println("Press 0 to Quit");
	    
	    String option = input.nextLine();
	    
	    switch(option) {
	      case "1":
	        runLogin();
	        break;
	      case"2":
	        createAccount();
	        break;
	      case "0":
	        System.out.println("Please come again soon!");
	        System.exit(0);
	        break;
	      default:
	        System.out.println("Failed to recognize option");
	        break;
	    }
	while(LoggedIn) {
		System.out.println(" ");
		System.out.println("Press \"Enter\" to continue ");
		String enter = input.nextLine();
		System.out.println("Hello " + currentUser + "!");
		System.out.println("What would you like to do today?");
		System.out.println("Press 1 to View Balance");
		System.out.println("Press 2 to Deposit Money");
		System.out.println("Press 3 to Withdraw Money");
		System.out.println("Press 4 to Transfer Money");
		System.out.println("Press 5 to Log Out");
		option = input.nextLine();
		switch(option) {
	      case "1":
	        viewBalance();
	        break;
	      case"2":
	        depositMoney();
	        break;
	      case "3":
	    	withdrawMoney();
	    	break;
	      case "4":
	    	transferFunds();
	    	break;
	      case "5":
	    	LoggedIn = false;
	    	currentUser = null;
	    	break;
	      case "0":
	        System.out.println("Please come again soon!");
	        System.exit(0);
	        break;
	      default:
	        System.out.println("Failed to recognize option");
	        break;
	    }
	}
	    
    }
  }
    
  public static void runLogin() {
	  System.out.println("Please enter your username?");
	  String username = input.nextLine();
	  System.out.println("Please enter your password");
	  String password = input.nextLine();
	  for(Account a : bs.getAllAccounts()) {
	      if(a.authenticate(username,  password)) {
	    	  Controller.LoggedIn = true;
	    	  currentUser = username;
	        System.out.println("You have been logged in");
	      }
	    }
	    if(!Controller.LoggedIn) {
	        System.out.println("Failed to log in with your username and password.");
	      }
  }

  
  public static void createAccount() {
	    System.out.println("Welcome to Account creation.");
	    //just one retry count, we'll let them retry until 3 retries then boot them
	    int retryCount = 0;
	    while(retryCount < 3) {
	      System.out.println("Please provide a username: ");
	      String username = input.nextLine();
	      System.out.println("Please provide a password");
	      String password = input.nextLine();
	      try {
	        Account account = new Account(username, password);
	    	bs.insert(username, password);
	    	//accounts.add(account);
	        break;
	      } catch (PasswordTooShortException e) {
	        System.out.println("Password too short, please retry with password of 8 more characters");
	        logger.error("This is an error" + e);
	        retryCount++;
	      } catch (DuplicateUsernameException e) {
	        System.out.println("Username already exists in our system, please choose another");
	        logger.error("This is an error" + e);
	        retryCount++;
	      }
	    }
	    //for better UX:
	    if(retryCount >= 3) {
	      System.out.println("Retries exceded, exiting account creation.");
	    }
	  }
  public static void viewBalance() {
	  System.out.println("Your current balance is " + bs.getBalance(currentUser));
  }
  
  public static void depositMoney() {
	  int deposit;
	  System.out.println("How much are you depositing?");
	  do {
		  try {
			  deposit = input.nextInt();
			  break;
		  } catch (InputMismatchException e) {
			  input.next();
			  System.out.println("Unrecognized Input");
		  }
	  }while (true); 
	  String garbage = input.nextLine();
	  if(deposit < 0) {
		  System.out.println("Error, unable to withdraw funds from this current selection");
	  } else {
		  int currentBalance = Integer.parseInt(bs.getBalance(currentUser));
		  currentBalance = currentBalance + deposit;
		  bs.changeBanlance(currentUser, currentBalance);
	  }
	  
  }
  
  public static void depositMoney(int deposit, String reciever) {
	  if(deposit < 0) {
		  System.out.println("Error, unable to withdraw funds from this current selection");
	  } else {
		  int currentBalance = Integer.parseInt(bs.getBalance(reciever));
		  currentBalance = currentBalance + deposit;
		  bs.changeBanlance(reciever, currentBalance);
	  }
	  
  }
  
  public static void withdrawMoney() {
	  int withdraw;
	  int currentBalance = Integer.parseInt(bs.getBalance(currentUser));
	  System.out.println("How much are you withdrawing?");
	  do {
		  try {
			  withdraw = input.nextInt();
			  break;
		  } catch (InputMismatchException e) {
			  input.next();
			  System.out.println("Unrecognized Input");
		  }
	  }while (true); 
	  String garbage = input.nextLine();
	  if(withdraw < 0) {
		  System.out.println("Error, unable to deposit funds from this current selection");
	  } else if(withdraw > currentBalance){
		  System.out.println("Insufficient funds");
	  } else {
		  currentBalance = currentBalance - withdraw;
		  bs.changeBanlance(currentUser, currentBalance);
	  }
	  
  }
  
  public static void withdrawMoney(int withdraw) {
	  int currentBalance = Integer.parseInt(bs.getBalance(currentUser));
		  currentBalance = currentBalance - withdraw;
		  bs.changeBanlance(currentUser, currentBalance);
  }
  
  
  public static void transferFunds() {
	  System.out.println("Who are you transfering funds with?");
	  String transferee = input.nextLine();
	  int transferTotal = 0;
	  boolean nonExistantUser = true;
	  for(Account a : bs.getAllAccounts()) {
	      if(a.validTransferUser(transferee)) {
	        System.out.println("How much will you be transfering?");
	        do {
	        	try {
	        		transferTotal = input.nextInt();
	        		break;
	        	} catch (InputMismatchException e) {
	        		input.next();
	        		System.out.println("Unrecognized Input");
	        	}
	        }while (true); 
	        String garbage = input.nextLine();
	        nonExistantUser = false;
	      int currentBalance = Integer.parseInt(bs.getBalance(currentUser));
	  	  if(transferTotal < 0) {
	  		  System.out.println("Error, unable to take funds from other users");
	  	  } else if(transferTotal > currentBalance){
	  		  System.out.println("Insufficient funds");
	  	  } else {
	  		  withdrawMoney(transferTotal);
	  		  depositMoney(transferTotal, transferee);
	  		  System.out.println("Successfully transfered" + transferTotal + "funds");
	  	  }
	      } 
	      if(nonExistantUser) {
	    	  System.out.println("That user is not registered in our system.");
	    	  System.out.println("Please try again");
	    	  nonExistantUser = false;
	      }
	      
	    }
	  
  }
  
    
  
} 
