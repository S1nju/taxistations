package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Model.Dto.TableDto;
import com.DiagramToDb.LMDToDB.Model.Entity.ColumnEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TableEntity;
import com.DiagramToDb.LMDToDB.Repo.TableRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TableService {
    @Autowired
   private TableRepo tableRepo;
    @Autowired
    private ColumnService columnService;
    @Autowired
    private DatabaseService databaseService;
    public List<TableDto> getTables(Integer dbid){

        return databaseService.findById(dbid).getTables();
    }
    public String update(@Valid TableDto body){
        Optional<TableEntity> l = tableRepo.findById(body.getId());
        if(l.isPresent()){
            if(!body.getName().isBlank()||!body.getName().isEmpty()){
                l.get().setName(body.getName());}
            if(!body.getColumns().isEmpty()){
                body.getColumns().forEach(i->{
                    ColumnEntity a = ColumnEntity.toEntity(i);
                    a.setTablel(l.get());
                    l.get().getColumns().add(a);
                });}else{
                l.get().setColumns(l.get().getColumns());
            }

            tableRepo.save(l.get());
            return "Succses";
        }
        return "FAILED update please fill the fields correctly";

    }
    public void delete(Integer id){
        tableRepo.deleteById(id);
    }

    public TableDto findById(Integer dbid,Integer id){

        List<TableDto> list = getTables(dbid);;

        Optional<TableDto> l =  list.stream().filter(i->i.getId().equals(id)).findFirst();
        return l.orElse(new TableDto());
    }
    }

