package com.donteatalone.serverone.app.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.donteatalone.serverone.app.entity.SigninEntity;


@Repository
@Transactional
public class SigninDAO implements ISigninDAO{
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public SigninDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean signupUser(SigninEntity user) {
		if(existsUser(user))
			throw new IllegalArgumentException("User already exists");
		String sql = "INSERT into Login(email, password) values(?, ?)";
		try{
			MessageDigest m = MessageDigest.getInstance("SHA-512");
			byte[] bytes = m.digest(user.getPasskey().getBytes());
			StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        jdbcTemplate.update(sql, user.getEmailId(), sb.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Unable to hash password using SHA-512", e);
		}
		sql = "SELECT id FROM Login where email = ? AND password = ?";
		int user_id = jdbcTemplate.queryForObject(sql, Integer.class, user.getEmailId(), user.getPasskey());
		user.setUser_id(user_id);
		return true;
	}

	@Override
	public boolean existsUser(SigninEntity user) {
		String sql = "SELECT count(*) FROM Login where email = ? AND password = ?";
		int count = -1;
		try{
			MessageDigest m = MessageDigest.getInstance("SHA-512");
			byte[] bytes = m.digest(user.getPasskey().getBytes());
			StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        count = jdbcTemplate.queryForObject(sql, Integer.class, user.getEmailId(), sb.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Unable to hash password using SHA-512", e);
		}
		if(count == -1) {
			throw new IllegalArgumentException("Unable to execute query on the table");
		} else if(count == 0)
			return false;
		else
			return true;
	}
	
	
}
