package com.alcides.ollertservicejava.entity;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "boards")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "board", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Group> groups;

}
