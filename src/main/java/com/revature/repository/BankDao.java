package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.repository.ConnectionUtil;
import com.revature.exception.DuplicateUsernameException;
import com.revature.exception.PasswordTooShortException;
import com.revature.model.Account;

public class BankDao implements DaoContract<Account, Integer>{

	@Override
	public List<Account> findAll() {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from bank_account order by id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Account> list = new ArrayList<>();
			while(rs.next()) {
				list.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
			return list;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<String> findUsername() {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select username from bank_account";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<String> usernames = new ArrayList<>();
			while (rs.next()) {
				 usernames.add(rs.getString(2));
			}
			return usernames;
			} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

//	@Override
//	public Account findByBoolean(boolean b) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Account findByString(String s) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select * from bank_account where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
					return new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String findBalanceByName(String s) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "select balance from bank_account where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int g =rs.getInt("balance");
				String j = String.valueOf(g);
				return j;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public Account insert(Account t) {
		try(Connection conn = ConnectionUtil.connect()){
			String sql = "insert into bank_account (username, password) values (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, t.getUsername());
			ps.setString(2, t.getPassword());
			ps.execute();
			return findByString(t.getUsername());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int updateRecord(Account g) {
		try(Connection conn = ConnectionUtil.connect()){
			Statement s= conn.createStatement();
		    String sql = "update bank_account set balance = "+ g.getBalance() + " where username = " + g.getUsername();
		    int updated = s.executeUpdate(sql);
		    s.close();
		    return updated;
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Account updateBalance(String n, int i) {
		try(Connection conn = ConnectionUtil.connect()){
			Statement s = conn.createStatement();
		    String sql = "update bank_account set balance = ? where username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			//String b = String.valueOf(i);
			//ps.setString(1, b);
			ps.setInt(1, i);
			ps.setString(2, n);
			ps.execute();
		    return null;
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account update(Account t) {
		// TODO Auto-generated method stub
		return null;
	}

}
