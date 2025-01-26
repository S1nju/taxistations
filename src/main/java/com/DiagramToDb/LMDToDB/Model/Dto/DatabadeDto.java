package com.DiagramToDb.LMDToDB.Model.Dto;

import com.DiagramToDb.LMDToDB.Model.Entity.DatabaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatabadeDto {
    private Integer id;
    private String name;
    private String type;
    private Long user;
    private List<TableDto> tables;
    public static DatabadeDto toEntity(DatabaseEntity Entity){
        List<TableDto> list= new ArrayList<>();
        Entity.getTables().forEach(i->{
            list.add( TableDto.toEntity(i));
        });
        return DatabadeDto.builder()
                .id(Entity.getId())
                .name(Entity.getName())
                .type(Entity.getType())
                .user(Entity.getUser().getId())
                .tables(list)
                .build();

    }
}
