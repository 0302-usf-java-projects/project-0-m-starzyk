package com.revature.test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.repository.BankDao;

//import com.revature.model.Account;
//import com.revature.repository.BankDao;

public class TestConnectionsSQL {

	private static BankDao bd;
	//private static Set<Account> accounts = new HashSet<Account>();
	
	@Before
	public void setUp() {
		bd = new BankDao();
		System.out.println("begin");
	}
	
	@After 
	public void tearDown() {
		bd = null;
		System.out.println("over");
	}
	
	
	@Test
	 public void findBalanceFromUsingUserName() {
		String result = bd.findBalanceByName("mhstarz");
		System.out.println(result);

//		return account;
		assertTrue(result.equals("500"));
	}
	
	@Test
	public void 
	
	
}
