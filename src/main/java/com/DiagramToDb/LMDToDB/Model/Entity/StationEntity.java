package com.DiagramToDb.LMDToDB.Model.Entity;

import com.DiagramToDb.LMDToDB.Model.Dto.StationDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull(message ="field is required")
        private String name;
        @NotNull(message ="field is required")
        private String willaya;
        @OneToOne(mappedBy = "station")
        private UserEntity supervisor;
        @OneToMany(mappedBy = "stid",cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TaxiEntity> taxis = new ArrayList<>();
        public static StationEntity toDto(StationDto dto){

                return StationEntity.builder()
                        .id(dto.getId())
                        .name(dto.getName())
                        .willaya(dto.getWillaya())
                        .build()

                        ;

        }



}
