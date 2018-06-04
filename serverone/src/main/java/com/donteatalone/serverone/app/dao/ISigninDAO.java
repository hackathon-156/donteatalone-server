package com.donteatalone.serverone.app.dao;

import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.SigninEntity;

public interface ISigninDAO {
	BackendResponse signupUser(SigninEntity user);
}
