package com.alcides.ollertservicejava.controller;

import com.alcides.ollertservicejava.entity.Board;
import com.alcides.ollertservicejava.entity.User;
import com.alcides.ollertservicejava.repository.UserRepository;
import com.alcides.ollertservicejava.service.impl.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PostMapping
    public void add(@RequestBody Board board) {
        User user = authenticationFacade.getAuthenticatedUser();
        
        user.getBoards().add(board);

        userRepository.save(user);
    }

}
