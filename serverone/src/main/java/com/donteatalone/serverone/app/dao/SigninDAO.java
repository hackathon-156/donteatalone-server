package com.donteatalone.serverone.app.dao;

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
		jdbcTemplate.update(sql, user.getEmailId(), user.getPasskey());
		
		sql = "SELECT user_id FROM Login where email = ? AND password = ?";
		int user_id = jdbcTemplate.queryForObject(sql, Integer.class, user.getEmailId(), user.getPasskey());
		user.setUser_id(user_id);
		return true;
	}

	@Override
	public boolean existsUser(SigninEntity user) {
		String sql = "SELECT count(*) FROM Login where email = ? AND password = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, user.getEmailId(), user.getPasskey());
		if(count == 0)
			return false;
		else
			return true;
	}
	
	
}
