package com.DiagramToDb.LMDToDB.Model.Entity;

import com.DiagramToDb.LMDToDB.Model.Dto.DatabadeDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class DatabaseEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @NotNull(message ="field is required")
        private String name;
        @NotNull(message ="field is required")
        @Pattern(regexp = "ORACLE|MYSQL|SQLITE|POSTGESQL" , message = "invalid type databse should be ORACLE , MYSQL,SQLITE,POSTGESQL")
        private String type;
        @ManyToOne
        @JoinColumn
        private UserEntity user;
        @OneToMany(mappedBy = "dbid",cascade = CascadeType.ALL, orphanRemoval = true)
        private List<TableEntity> tables = new ArrayList<>();
        public static DatabaseEntity toDto(DatabadeDto dto){
                List<TableEntity> list= new ArrayList<>();
                dto.getTables().forEach(i->{
                        list.add(  TableEntity.toDto(i));
                });
                return DatabaseEntity.builder()
                        .id(dto.getId())
                        .name(dto.getName())

                        .type(dto.getType())
                        .tables(list)
                        .build()

                        ;

        }



}
