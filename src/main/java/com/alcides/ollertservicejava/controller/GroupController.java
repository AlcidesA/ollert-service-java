package com.alcides.ollertservicejava.controller;

import javax.persistence.EntityNotFoundException;

import com.alcides.ollertservicejava.dto.AddGroupDTO;
import com.alcides.ollertservicejava.entity.Board;
import com.alcides.ollertservicejava.entity.Group;
import com.alcides.ollertservicejava.repository.BoardRepository;
import com.alcides.ollertservicejava.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private GroupRepository groupRepository;

    @PostMapping
    public void add(@RequestBody AddGroupDTO payload) {
        Board board = boardRepository.findById(payload.getBoardId()).orElseThrow(() -> new EntityNotFoundException());

        Group group = payload.getGroup();

        group.setBoard(board);

        board.getGroup().add(group);

        boardRepository.save(board);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable("id") Integer id) {
        groupRepository.deleteById(id);
    }

}
