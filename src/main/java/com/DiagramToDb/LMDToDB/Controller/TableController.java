package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Model.Dto.TableDto;
import com.DiagramToDb.LMDToDB.Services.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/table")
public class TableController {
    @Autowired
    private TableService tableService;
    @GetMapping("/tables")
    public List<TableDto> gettables(@RequestParam Integer dbid){


        return tableService.getTables(dbid);

    }
    @PostMapping("/table")
    public TableDto getColumn(@RequestParam Integer dbid,@RequestParam Integer id){
        return tableService.findById(dbid,id);

    }
    @PutMapping  ("/update")
    public String updatetable(@RequestBody TableDto body){
      return  tableService.update(body);

    }
    @DeleteMapping("/delete")
    public void deletetable(@RequestParam Integer id){
        tableService.delete(id);

    }
}
