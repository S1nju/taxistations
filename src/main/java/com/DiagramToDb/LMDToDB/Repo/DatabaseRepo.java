package com.DiagramToDb.LMDToDB.Repo;

import com.DiagramToDb.LMDToDB.Model.Entity.DatabaseEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DatabaseRepo extends JpaRepository<DatabaseEntity,Integer> {
    List<DatabaseEntity> findByuser(UserEntity user);
}
