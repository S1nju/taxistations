package com.DiagramToDb.LMDToDB.Repo;

import com.DiagramToDb.LMDToDB.Model.Entity.DatabaseEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepo extends JpaRepository<TableEntity,Integer> {
    List<TableEntity> findAllBydbid(DatabaseEntity dbid);
}
