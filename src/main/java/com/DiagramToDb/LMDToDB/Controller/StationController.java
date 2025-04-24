package com.DiagramToDb.LMDToDB.Controller;

import com.DiagramToDb.LMDToDB.Model.Entity.StationEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TaxiEntity;
import com.DiagramToDb.LMDToDB.Services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class StationController {
    @Autowired
   private StationService stationService;
    //admins & users can get stations
@GetMapping("stations")
    public ResponseEntity<List<StationEntity>> getallstations(){
    return stationService.getallStations();
}
//admin can create stations
    @PostMapping("station")
    public ResponseEntity<String> createstation(@RequestBody StationEntity station){
        return stationService.createStation(station);
    }

    //admin can create stations
    @PutMapping("station/{id}")
    public ResponseEntity<String> updatestations(@PathVariable("id") Long id,@RequestBody StationEntity station){
        return stationService.updatestation(id,station);
    }

    @GetMapping("station/{id}")
    public ResponseEntity<StationEntity> updatestations(@PathVariable("id") Long id){
        return stationService.getStationbyid(id);
    }


    //admin can delete stations
    @DeleteMapping("station/{id}")
    public ResponseEntity<String> deletestation(@PathVariable("id") Long id){
        return stationService.deletestation(id);
    }
//admin affect supervisors
    @PostMapping("station/{id}/supervisor/{supid}")
    public ResponseEntity<String> affectstation(@PathVariable("id") Long id,@PathVariable("supid") Long supid){
        return stationService.affectsupervisor(id,supid);
    }
//supervisor add taxis to the station
    @PostMapping("station/{id}/taxi/{tid}")
    public ResponseEntity<String> accepttaxis(@PathVariable("id") Long id,@PathVariable("tid") Long tid){
        return stationService.addtaxitothestation(id,tid);
    }

    //getalltaxisinthestation

    @PostMapping("station/{id}/taxis")
    public ResponseEntity<List<TaxiEntity>> createstation(@PathVariable("id") Long id){
        return stationService.getalltaxisinstation(id);
    }


}
