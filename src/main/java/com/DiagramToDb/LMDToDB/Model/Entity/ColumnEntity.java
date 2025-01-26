package com.DiagramToDb.LMDToDB.Model.Entity;

import com.DiagramToDb.LMDToDB.Model.Dto.ColumnDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message ="field is required")
    private String name;
    @NotNull(message ="field is required")
    private String type;

    @ManyToOne
    @JoinColumn(name="tablel")
    private TableEntity tablel;

    public static ColumnEntity toEntity(ColumnDto dto){
        return ColumnEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .type(dto.getType())
                .build();

    }
}
