package com.DiagramToDb.LMDToDB.Model.Entity;

import com.DiagramToDb.LMDToDB.Model.Dto.Relation;
import com.DiagramToDb.LMDToDB.Model.Dto.TableDto;
import com.DiagramToDb.LMDToDB.Repo.TableRepo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message ="field is required")
    private String name;
    @NotNull(message ="field is required")
    private String pkey;
    @NotNull(message ="field is required")
    private String type;
    @ElementCollection
    private   List<Relation> relationto = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private DatabaseEntity dbid;
    @OneToMany(mappedBy = "tablel",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColumnEntity> columns = new ArrayList<>();
@Autowired
private static TableRepo tableRepo;
    public static TableEntity toDto(TableDto dto){


        List<ColumnEntity> list= new ArrayList<>();

        dto.getColumns().forEach(i->{
            list.add(ColumnEntity.toEntity(i));
        });
        return TableEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .type(dto.getType())
                .pkey(dto.getPkey())

                .relationto(dto.getRelationto())
                .columns(list)
                .build()

                ;

    }

}
