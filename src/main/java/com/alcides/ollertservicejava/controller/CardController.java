package com.alcides.ollertservicejava.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

import com.alcides.ollertservicejava.dto.AddCardDTO;
import com.alcides.ollertservicejava.entity.Card;
import com.alcides.ollertservicejava.entity.Group;
import com.alcides.ollertservicejava.repository.CardRepository;
import com.alcides.ollertservicejava.repository.GroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("cards")
public class CardController {
    
    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CardRepository cardRepository;

    @PostMapping
    public void add(@RequestBody AddCardDTO payload) {
        Group group = groupRepository.findById(payload.getGroupId()).orElseThrow(() -> new EntityNotFoundException());
        
        Card card = payload.getCard();

        card.setGroup(group);

        cardRepository.save(card);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Integer id) {
        cardRepository.deleteById(id);
    }
    

}
