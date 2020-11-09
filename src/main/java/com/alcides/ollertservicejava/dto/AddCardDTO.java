package com.alcides.ollertservicejava.dto;

import com.alcides.ollertservicejava.entity.Card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddCardDTO {
    
    private Integer groupId;
    
    private Card card;
    
}