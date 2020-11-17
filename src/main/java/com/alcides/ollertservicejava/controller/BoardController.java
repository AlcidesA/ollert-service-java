package com.alcides.ollertservicejava.controller;

import com.alcides.ollertservicejava.entity.Board;
import com.alcides.ollertservicejava.entity.User;
import com.alcides.ollertservicejava.repository.BoardRepository;
import com.alcides.ollertservicejava.repository.UserRepository;
import com.alcides.ollertservicejava.service.impl.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @GetMapping
    public List<Board> list() {
        User user = authenticationFacade.getAuthenticatedUser();
        
        return user.getBoards();
    }

    @GetMapping("/{id}")
    public Board getBoard(@PathVariable("id") Integer id) {
        return boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping
    public void add(@RequestBody Board board) {
        User user = authenticationFacade.getAuthenticatedUser();
        
        user.getBoards().add(board);

        userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

        User user = authenticationFacade.getAuthenticatedUser();

        user.removeBoard(board);

        boardRepository.delete(board);
        userRepository.save(user);
    }

}
