package com.donteatalone.serverone.app.controller;

import com.donteatalone.serverone.app.service.SigninService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.donteatalone.serverone.app.entity.SigninEntity;

@RestController
public class SigninController {
    @Autowired
    private SigninService signinService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public String signup(@RequestBody SigninEntity entity) {
        return "Got "+entity.getEmailId()+" "+entity.getPasskey();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public SigninEntity signin(@RequestBody SigninEntity signinEntity) {
        if(signinService.signup(signinEntity.getEmailId(), signinEntity.getPasskey())) {
            return signinEntity;
        } else {
            //confirm what should be returned dor failed signin.
            return null;
        }
    }


}
