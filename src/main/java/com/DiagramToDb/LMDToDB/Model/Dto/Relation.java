package com.DiagramToDb.LMDToDB.Model.Dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
public class Relation {
    public Integer targetId;
    public Integer relationType;
}
