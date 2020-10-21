package com.alcides.ollertservicejava.service.impl;

import com.alcides.ollertservicejava.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository applicationUserRepository) {
        this.userRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.alcides.ollertservicejava.entity.User applicationUser = userRepository.findByEmail(email);

        if (applicationUser == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(applicationUser.getEmail(), applicationUser.getPassword(), emptyList());
    }
}
