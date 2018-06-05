package com.donteatalone.serverone.app.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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
		if(getUserByEmail(user.getEmailId()) != null)
			throw new IllegalArgumentException("User already exists");
		
		String sql = "INSERT into Login(email, password) values(?, ?)";
		StringBuilder sb = new StringBuilder();
		try{
			MessageDigest m = MessageDigest.getInstance("SHA-512");
			byte[] bytes = m.digest(user.getPasskey().getBytes());
	        for(int i=0; i< bytes.length ;i++){
	        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        jdbcTemplate.update(sql, user.getEmailId(), sb.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Unable to hash password using SHA-512", e);
		}
		sql = "SELECT id FROM Login where email = ?";
		int user_id = jdbcTemplate.queryForObject(sql, Integer.class, user.getEmailId());
		user.setUser_id(user_id);
		return true;
	}
	
	@Override
	public SigninEntity getUserByEmail(String email) {
		String sql = "SELECT id, email FROM Login where email = ?";
		List<SigninEntity> user = null;
		user = jdbcTemplate.query(sql, new RowMapper<SigninEntity>() {
			
			@Override
			public SigninEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
				SigninEntity s = new SigninEntity();
				s.setUser_id(rs.getInt("id"));
				s.setEmailId(rs.getString("email"));
				return s;
			}
		
		}, email);
		return user.size() == 0?null:user.get(0);
	}

	@Override
	public boolean existsUser(SigninEntity user) {
		String sql = "SELECT id, email FROM Login where email = ? AND password = ?";
		List<SigninEntity> userList = null;
		try{
			MessageDigest m = MessageDigest.getInstance("SHA-512");
			byte[] bytes = m.digest(user.getPasskey().getBytes());
			StringBuilder sb = new StringBuilder();
	        for(int i=0; i< bytes.length ;i++){
	        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        userList = jdbcTemplate.query(sql, new RowMapper<SigninEntity>() {

				@Override
				public SigninEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
					SigninEntity s = new SigninEntity();
					s.setUser_id(rs.getInt("id"));
					s.setEmailId(rs.getString("email"));
					return s;
				}
	        	
			}, user.getEmailId(), sb.toString());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Unable to hash password using SHA-512", e);
		}
		if(userList.size() == 0)
			return false;
		else {
			user.setUser_id(userList.get(0).getUser_id());
			return true;
		}
	}
	
	
}
