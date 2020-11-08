package com.alcides.ollertservicejava.dto;

import com.alcides.ollertservicejava.entity.Group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddGroupDTO {
    
    private Integer boardId;
    private Group group;

}
