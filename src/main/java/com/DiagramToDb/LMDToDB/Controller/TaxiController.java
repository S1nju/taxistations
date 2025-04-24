package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Model.Entity.StationEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TaxiEntity;
import com.DiagramToDb.LMDToDB.Services.StationService;
import com.DiagramToDb.LMDToDB.Services.TaxisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class TaxiController {
    @Autowired
    private TaxisService taxisService;
    //admins & users & supervisor can get taxis
    @GetMapping("taxis")
    public ResponseEntity<List<TaxiEntity>> getalltaxis(){
        return taxisService.getalltaxis();
    }
    //admin can create stations
    @PostMapping("taxi")
    public ResponseEntity<String> createtaxi(@RequestBody TaxiEntity taxi){
        return taxisService.createTaxi(taxi);
    }

    //admin can create taxi
    @PutMapping("taxi/{id}")
    public ResponseEntity<String> updatestations(@PathVariable("id") Long id,@RequestBody TaxiEntity taxi){
        return taxisService.updateTaxi(id,taxi);
    }


    //admin can delete taxi
    @DeleteMapping("taxi/{id}")
    public ResponseEntity<String> deletetaxi(@PathVariable("id") Long id){
        return taxisService.deletetaxi(id);
    }


}
