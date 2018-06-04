package com.donteatalone.serverone.app.service;

import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.SigninEntity;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.donteatalone.serverone.app.dao.ISigninDAO;

import java.util.logging.Logger;

@Service
public class SigninService implements ISigninService{

    @Autowired
    ISigninDAO signinDAO;
    Logger logger = Logger.getLogger("SigninService");

    /**
     * Service to signup new user.
     * @param signinEntity
     * @return BackendResponse which encapsulates what had happened signup is successful.
     */
    public BackendResponse signup(SigninEntity signinEntity) {
        //call dao with the method.
        BackendResponse response = new BackendResponse();
        boolean isSuccessful;
        try {
            isSuccessful = signinDAO.signupUser(signinEntity)
        } catch (IllegalArgumentException e) {
            logger.info("Illegal Argument Exception during signup, User already exists.");
            response.setStatusCode(200);
        }
        return isSuccessful;
    }

    public boolean signin(String login, String password) {
        //call dao with the method.
        return true;
    }
}
