package com.revature.service;

import java.util.List;

import com.revature.repository.DaoContract;
import com.revature.repository.BankDao;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordTooShortException;
import com.revature.model.Account;

public class BankService {

	
	private BankDao bdao;
	
	{
		bdao=new BankDao();
	}
	
	public List<Account> getAllAccounts(){
		return bdao.findAll();
	}
	
//	public Account getbyId(int id) {
//		return bdao.findById(id);
//	}
	
	public Account insert(String username, String password) throws PasswordTooShortException, DuplicateUsernameException {
			return bdao.insert(new Account(username, password));
	}
	
	public Account getByName(String s) {
		return bdao.findByString(s);
	}
	
	public List<String> getAllUsernames(){
		return bdao.findUsername();
	}
	
	public String getBalance(String s) {
		return bdao.findBalanceByName(s);
	}
	
	public Account changeBanlance(String n, int w) {
		return bdao.updateBalance(n, w);
	}
}
