package com.alcides.ollertservicejava.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.alcides.ollertservicejava.dto.AddGroupDTO;
import com.alcides.ollertservicejava.entity.Board;
import com.alcides.ollertservicejava.entity.Group;
import com.alcides.ollertservicejava.repository.BoardRepository;
import com.alcides.ollertservicejava.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("groups")
public class GroupController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping
    public List<Group> list(@RequestParam(value = "boardId", required = true) Integer boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new EntityNotFoundException());

        return board.getGroups();
    }

    @GetMapping("/{id}")
    public Group getGroup(@PathVariable("id") Integer id) {
        return groupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @PostMapping
    public void add(@RequestBody AddGroupDTO payload) {
        Board board = boardRepository.findById(payload.getBoardId()).orElseThrow(() -> new EntityNotFoundException());

        Group group = payload.getGroup();

        group.setBoard(board);

        board.getGroups().add(group);

        boardRepository.save(board);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable("id") Integer id) {
        groupRepository.deleteById(id);
    }

}
