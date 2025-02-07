package com.DiagramToDb.LMDToDB.Model.Dto;

import com.DiagramToDb.LMDToDB.Model.Entity.TableEntity;
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
public class TableDto {
    private Integer id;
    private String name;
    private String pkey;
    private String type;
    private List<ColumnDto> columns;
    private  List<Relation> relationto = new ArrayList<>();

    public static TableDto toEntity(TableEntity Entity){
        List<ColumnDto> list= new ArrayList<>();
        Entity.getColumns().forEach(i->{
         list.add(   ColumnDto.toEntity(i));
        });
        return TableDto.builder()
                .id(Entity.getId())
                .type(Entity.getType())
                .name(Entity.getName())
                .relationto(Entity.getRelationto())
                .pkey(Entity.getPkey())
                .columns(list)
                .build()

                ;

    }
}
