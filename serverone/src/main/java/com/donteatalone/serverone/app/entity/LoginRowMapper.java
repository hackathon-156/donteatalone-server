package com.donteatalone.serverone.app.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRowMapper implements org.springframework.jdbc.core.RowMapper<SigninEntity>{

	@Override
	public SigninEntity mapRow(ResultSet row, int rowNum) throws SQLException {
		SigninEntity user = new SigninEntity();
		user.setEmailId(row.getString(0));
		user.setPasskey(row.getString(1));
		return user;
	}	
}
