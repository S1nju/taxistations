package com.DiagramToDb.LMDToDB.Repo;

import com.DiagramToDb.LMDToDB.Model.Entity.ColumnEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColumnRepo extends JpaRepository<ColumnEntity,Integer> {
}
