package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Model.Entity.StationEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TaxiEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.UserEntity;
import com.DiagramToDb.LMDToDB.Repo.StationsRepo;
import com.DiagramToDb.LMDToDB.Repo.TaxiRepo;
import com.DiagramToDb.LMDToDB.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StationService {
    @Autowired
    private StationsRepo stationsRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TaxiRepo taxiRepo;
    public ResponseEntity<List<StationEntity>> getallStations() {
        return ResponseEntity.ok( stationsRepo.findAll());
    }

    public ResponseEntity<String> createStation(StationEntity station) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }
        stationsRepo.save(station);

        return ResponseEntity.status(HttpStatus.OK).body("saved !!");
    }

    public ResponseEntity<String> updatestation(Long id, StationEntity stationbdy) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }
        Optional<StationEntity> station= stationsRepo.findById(id);
        if(station.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no station with this id");

        }
        StationEntity stationg = station.get();
        stationg.setWillaya(stationbdy.getWillaya());
        stationg.setName(stationbdy.getName());
        stationsRepo.save(stationg);

        return ResponseEntity.status(HttpStatus.OK).body("Updated !!");
    }

    public ResponseEntity<String> deletestation(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }

        Optional<StationEntity>taxi= stationsRepo.findById(id);
        if(taxi.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no stations with this id");

        }
        stationsRepo.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("deleted !!");


    }

    public ResponseEntity<String> affectsupervisor(Long id, Long supid) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }

        Optional<StationEntity>taxi= stationsRepo.findById(id);
        if(taxi.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no stations with this id");

        }
        Optional<UserEntity>supopt= userRepo.findById(supid);
        if(supopt.isEmpty()|| !Objects.equals(supopt.get().getType(), "supervisor")){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no supperviser with this id");

        }
        UserEntity sup = supopt.get();
        sup.setStation(taxi.get());
        userRepo.save(sup);
        return ResponseEntity.status(HttpStatus.OK).body("affected!!");

    }

    public ResponseEntity<String> addtaxitothestation(Long id, Long tid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "supervisor")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an superviser");

        }

        Optional<StationEntity>taxi= stationsRepo.findById(id);
        if(taxi.isEmpty()||taxi.get().getSupervisor()!=user){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no stations with this id");

        }
        Optional<TaxiEntity>tx= taxiRepo.findById(tid);
        if(tx.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no taxi with this id");

        }

        StationEntity station = taxi.get();
        List<TaxiEntity> newlist = station.getTaxis();
        newlist.add(tx.get());
        station.setTaxis(newlist);
        tx.get().setStid(station);
        taxiRepo.save(tx.get());
        stationsRepo.save(station);
        return ResponseEntity.status(HttpStatus.OK).body("affected!!");


    }

    public ResponseEntity<List<TaxiEntity>> getalltaxisinstation(Long id) {
        Optional<StationEntity>taxi= stationsRepo.findById(id);
        return taxi.map(station -> ResponseEntity.status(HttpStatus.OK).body(taxiRepo.findAllBystid(station))).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>()));


    }

    public ResponseEntity<StationEntity> getStationbyid(Long id) {
        Optional<StationEntity>taxi= stationsRepo.findById(id);
        if(taxi.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StationEntity());

        }

        return ResponseEntity.status(HttpStatus.OK).body(stationsRepo.findById(id).get());
    }
}
