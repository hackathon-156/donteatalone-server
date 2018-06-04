package com.donteatalone.serverone.app.controller;

import com.donteatalone.serverone.app.entity.BackendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.donteatalone.serverone.app.entity.SigninEntity;
import com.donteatalone.serverone.app.service.SigninService;

@RestController
public class SigninController {
    @Autowired
    private SigninService signinService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public BackendResponse signup(@RequestBody SigninEntity entity) {
        return signinService.signup(entity);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public BackendResponse signin(@RequestBody SigninEntity entity) {
        return signinService.signin(entity);
    }
}
