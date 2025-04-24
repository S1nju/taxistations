package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Model.Entity.TaxiEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.UserEntity;
import com.DiagramToDb.LMDToDB.Repo.TaxiRepo;
import com.DiagramToDb.LMDToDB.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TaxisService {
@Autowired
private TaxiRepo taxiRepo;
@Autowired
private UserRepo userRepo;
    public ResponseEntity<List<TaxiEntity>> getalltaxis() {
       return ResponseEntity.ok( taxiRepo.findAll());
    }

    public ResponseEntity<String> createTaxi(TaxiEntity taxi) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

     UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }
        taxiRepo.save(taxi);

        return ResponseEntity.status(HttpStatus.OK).body("saved !!");
    }

    public ResponseEntity<String> updateTaxi(Long id, TaxiEntity taxibody) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }
     Optional<TaxiEntity>taxi= taxiRepo.findById(id);
        if(taxi.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no taxi with this id");

        }
        TaxiEntity taxig = taxi.get();
        taxig.setWillaya(taxibody.getWillaya());
        taxig.setName(taxibody.getName());
        taxig.setMatricule(taxibody.getMatricule());
        taxiRepo.save(taxig);

        return ResponseEntity.status(HttpStatus.OK).body("Updated !!");
    }

    public ResponseEntity<String> deletetaxi(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user= userRepo.findByUsername(authentication.getName());
        if (!Objects.equals(user.getType(), "admin")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("you are not an admin");

        }

        Optional<TaxiEntity>taxi= taxiRepo.findById(id);
        if(taxi.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("there is no taxi with this id");

        }
        taxiRepo.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("deleted !!");


    }
}

