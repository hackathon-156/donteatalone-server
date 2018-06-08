package com.donteatalone.serverone.app.dao;

import com.donteatalone.serverone.app.entity.ProfileEntity;
import com.donteatalone.serverone.app.entity.SigninEntity;

public interface IProfileDAO {
    boolean setupProfile(ProfileEntity profileEntity);
    SigninEntity getUserByEmail(String email);
}
