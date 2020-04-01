package com.revature.repository;

import java.util.List;

import com.revature.model.Account;

/**
 * 
 * @author Revature
 *
 *The dao is the data access object. This object is responsible for gathering information
 *		from the db. We need to represent our tables with our model classes and 
 *		the fields in the class will represent the columns. the objects we need
 *		to save will be the individual records.
 *
 * @param <T> the type of model for the dao objects
 * @param <I> the primary key of the model
 */
public interface DaoContract<T, I> {

	List<T> findAll();
	List<String> findUsername();
	T findByString(String s);
	String findBalanceByName(String s);
	T insert(T t);
	T update(T t);
	//T updateBalance
	Account updateBalance(String s, int i);
	
}
