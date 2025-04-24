package com.DiagramToDb.LMDToDB.Model.Entity;

import com.DiagramToDb.LMDToDB.Model.Dto.TaxiDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaxiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message ="field is required")
    private String name;
    @NotNull(message ="field is required")
    @Column(unique = true)
    private String matricule;
    @NotNull(message ="field is required")
    private String willaya;
    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private StationEntity stid;


    public static TaxiEntity toDto(TaxiDto dto){

        return TaxiEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build()

                ;

    }

}
