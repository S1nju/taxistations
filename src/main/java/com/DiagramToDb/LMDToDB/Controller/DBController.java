package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Model.Dto.DatabadeDto;
import com.DiagramToDb.LMDToDB.Model.Entity.DatabaseEntity;
import com.DiagramToDb.LMDToDB.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/db")
public class DBController {
    @Autowired
    private DatabaseService databaseService;
    @GetMapping("/dbs")
    public List<DatabadeDto> getdatabases(){
        return databaseService.getDatabases();

    }
    @GetMapping("/db")
    public DatabadeDto getdatabase(@RequestParam Integer id){
        return databaseService.findById(id);

    }
    @PostMapping("/save")
    public ResponseEntity<?> saveTable(@RequestBody @Valid DatabaseEntity body){
      return  databaseService.save(body);

    }
    @PutMapping ("/update")
    public String updatedatabase(@RequestBody DatabadeDto body){
       return databaseService.update(body);

    }
    @DeleteMapping("/delete")
    public void deletedatabase(@RequestParam Integer id){
        databaseService.delete(id);

    }

}
