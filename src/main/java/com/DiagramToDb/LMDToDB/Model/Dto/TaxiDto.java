package com.DiagramToDb.LMDToDB.Model.Dto;

import com.DiagramToDb.LMDToDB.Model.Entity.TaxiEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Relation;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxiDto {
    private Long id;
    private String name;
    private String matricule;
    private String willaya;
    public static TaxiDto toEntity(TaxiEntity Entity){
        return TaxiDto.builder()
                .id(Entity.getId())
                .matricule(Entity.getMatricule())
                .willaya(Entity.getWillaya())
                .build()

                ;

    }
}
