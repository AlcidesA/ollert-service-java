package com.alcides.ollertservicejava.service;

import com.alcides.ollertservicejava.entity.User;

public interface IAuthenticationFacade {

    User getAuthenticatedUser();

}
