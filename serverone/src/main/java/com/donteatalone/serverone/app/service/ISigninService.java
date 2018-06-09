package com.donteatalone.serverone.app.service;

import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.SigninEntity;

public interface ISigninService {
    public BackendResponse signup(SigninEntity signinEntity);

}
