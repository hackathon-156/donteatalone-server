package com.donteatalone.serverone.app.controller;


import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.ProfileEntity;
import com.donteatalone.serverone.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @RequestMapping(method = RequestMethod.POST, value = "/setprofile")
    public BackendResponse signup(@RequestBody ProfileEntity entity) {
        return profileService.setup(entity);
    }
}
