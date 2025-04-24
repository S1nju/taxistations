package com.DiagramToDb.LMDToDB.Model.Dto;

import com.DiagramToDb.LMDToDB.Model.Entity.StationEntity;
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
public class StationDto {
    private Long id;
    private String name;
    private String willaya;
    public static StationDto toEntity(StationEntity Entity){
        return StationDto.builder()
                .id(Entity.getId())
                .name(Entity.getName())
                .willaya(Entity.getWillaya())
                .build();

    }
}
