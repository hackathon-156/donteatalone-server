package com.donteatalone.serverone.app.service;

import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.SigninEntity;
import com.donteatalone.serverone.utils.ErrorCodes;
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
            isSuccessful = signinDAO.signupUser(signinEntity);
        } catch (IllegalArgumentException e) {
            String msg = "Illegal Argument Exception during signup, User already exists.";
            logger.info(msg);
            response.setStatusCode(ErrorCodes.USER_ALREADY_EXISTS);
            response.setMessage(msg);
            response.setResponseObject(signinEntity);
            return response;
        }
        response.setMessage("success");
        response.setStatusCode(200);
        response.setResponseObject(signinEntity);
        return response;
    }

    public BackendResponse signin(SigninEntity signinEntity) {
        BackendResponse response = new BackendResponse();
        boolean isSuccessful = signinDAO.existsUser(signinEntity);
        if(isSuccessful) {
            logger.info("Login Successful for userId:"+signinEntity.getEmailId());
            response.setMessage("Success.");
        } else {
            response.setMessage("Invalid User name and/or password.");
        }
        response.setStatusCode(200);
        response.setResponseObject(signinEntity);
        return response;
    }
}
