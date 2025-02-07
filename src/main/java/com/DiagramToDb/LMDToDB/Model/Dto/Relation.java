package com.DiagramToDb.LMDToDB.Model.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Relation {
    public Integer targetId;
    public Integer relationType;
}
