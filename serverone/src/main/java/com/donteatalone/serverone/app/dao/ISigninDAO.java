package com.donteatalone.serverone.app.dao;

import com.donteatalone.serverone.app.entity.SigninEntity;

public interface ISigninDAO {
	boolean signupUser(SigninEntity user);
	boolean existsUser(SigninEntity user);
	SigninEntity getUserByEmail(String email);
}
