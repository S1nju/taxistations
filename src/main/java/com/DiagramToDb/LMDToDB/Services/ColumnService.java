package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Repo.ColumnRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColumnService {
    @Autowired
  private  ColumnRepo columnRepo;;
    

    public void Delete(Integer id){
        columnRepo.deleteById(id);
    }
}
