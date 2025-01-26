package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Services.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/column")
public class ColumnController  {
    @Autowired
   private  ColumnService columnService;

    @DeleteMapping("/delete")
    public void deleteColumn(@RequestParam Integer id){
        columnService.Delete(id);

    }

}
