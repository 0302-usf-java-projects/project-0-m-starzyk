package com.revature.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordTooShortException;
import com.revature.service.BankService;

public class Account {

	 private static BankService bs;
	  {
		  bs = new BankService();
	  }
	private static Set<String> usernames = new HashSet<String>();
	//private static List<String> usernames = bs.getAllUsernames();
	

	private static final int REQUIRED_PASSWORD_LENGTH = 8;
	
	private int id;
	private String username;
	private String password;
	private int balance;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String username, String password) throws PasswordTooShortException, DuplicateUsernameException {
		super();
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public Account(int id, String username, String password) throws PasswordTooShortException, DuplicateUsernameException {
		super();
		this.id = id;
		this.setUsername(username);
		this.setPassword(password);
	}
	
	public Account(int id, String username, String password, int balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.setBalance(balance);
		Account.usernames.add(username);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) throws DuplicateUsernameException {
	    if(Account.usernames.contains(username)) {
	        throw new DuplicateUsernameException();
	      }
	    //Account.usernames.add(username);
		this.username = username;
	}
//	public void setUsername(String username) throws DuplicateUsernameException {
//		if(bs.getByName(username) != null) {
//			throw new DuplicateUsernameException();
//		}
//		Account.usernames.add(username);
//		this.username = username;
//	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws PasswordTooShortException {
	    if(password.length() < REQUIRED_PASSWORD_LENGTH) {
	    	throw new PasswordTooShortException();
	    }
		this.password = password;
	}
	
	public boolean authenticate(String username, String password) {
		return this.username.equals(username) && this.password.equals(password);
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public boolean validTransferUser(String username) {
		return this.username.equals(username);
	}

	
}
