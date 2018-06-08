package com.donteatalone.serverone.app.service;

import com.donteatalone.serverone.app.entity.BackendResponse;
import com.donteatalone.serverone.app.entity.ProfileEntity;

public interface IProfileService {
    public BackendResponse setup(ProfileEntity profileEntity);
}
