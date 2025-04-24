package com.DiagramToDb.LMDToDB.Repo;

import com.DiagramToDb.LMDToDB.Model.Entity.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationsRepo extends JpaRepository<StationEntity,Long> {

}
