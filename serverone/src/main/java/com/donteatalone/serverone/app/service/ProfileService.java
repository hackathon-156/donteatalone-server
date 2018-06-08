package com.donteatalone.serverone.app.service;

import com.donteatalone.serverone.app.dao.IProfileDAO;
import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.ProfileEntity;
import com.donteatalone.serverone.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProfileService implements IProfileService{
    @Autowired
    IProfileDAO profileDAO;
    Logger logger = Logger.getLogger("ProfileService");

    public BackendResponse setup(ProfileEntity profileEntity) {
        BackendResponse response = new BackendResponse();
        boolean isSuccessful;
        try{
            isSuccessful = profileDAO.setupProfile(profileEntity);
        }
        catch (IllegalArgumentException e)
        {
            String msg = "Illegal Argument Exception during signup, please try again.";
            logger.info(msg);
            response.setStatusCode(ErrorCodes.USER_ALREADY_EXISTS);
            response.setMessage(msg);
            response.setResponseObject(profileEntity);
            return response;
        }
        response.setMessage("success");
        response.setStatusCode(200);
        response.setResponseObject(profileEntity);
        return response;
    }
}
