package com.alcides.ollertservicejava.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "\"Group\"")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @JsonIgnore
    @OneToMany(mappedBy = "group")
    private List<Card> cards;
}
