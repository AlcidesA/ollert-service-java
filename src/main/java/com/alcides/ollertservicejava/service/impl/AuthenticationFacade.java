package com.alcides.ollertservicejava.service.impl;

import com.alcides.ollertservicejava.entity.User;
import com.alcides.ollertservicejava.repository.UserRepository;
import com.alcides.ollertservicejava.service.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade implements IAuthenticationFacade {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(authentication.getPrincipal().toString());
    }



}
