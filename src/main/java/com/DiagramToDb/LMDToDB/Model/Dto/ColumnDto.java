package com.DiagramToDb.LMDToDB.Model.Dto;

import com.DiagramToDb.LMDToDB.Model.Entity.ColumnEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnDto {

    private Integer id;
    private String name;
    private String type;

    public static ColumnDto toEntity(ColumnEntity Entity){
        return ColumnDto.builder()
                .id(Entity.getId())
                .name(Entity.getName())
                .type(Entity.getType())
                .build();



    }
}
