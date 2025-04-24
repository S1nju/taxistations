package com.DiagramToDb.LMDToDB.Repo;

import com.DiagramToDb.LMDToDB.Model.Entity.StationEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TaxiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiRepo extends JpaRepository<TaxiEntity,Long> {
    List<TaxiEntity> findAllBystid(StationEntity stid);
}
