package com.DiagramToDb.LMDToDB.Services;

import com.DiagramToDb.LMDToDB.Model.Dto.DatabadeDto;
import com.DiagramToDb.LMDToDB.Model.Entity.DatabaseEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.TableEntity;
import com.DiagramToDb.LMDToDB.Model.Entity.UserPrinsipals;
import com.DiagramToDb.LMDToDB.Repo.DatabaseRepo;
import com.DiagramToDb.LMDToDB.Repo.TableRepo;
import com.DiagramToDb.LMDToDB.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DatabaseService {
    @Autowired
    private DatabaseRepo databaseRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TableRepo Tablerepo;
    public List<DatabadeDto> getDatabases() {
        List<DatabadeDto> list = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object user = authentication.getPrincipal();
            if (user instanceof UserDetails) {

                databaseRepo.findByuser(((UserPrinsipals) user).getUser()).forEach(i -> {
                    list.add(DatabadeDto.toEntity(i));
                });
                return list;
            }

        }
return list;
    }
    public ResponseEntity<?> save(@Valid DatabaseEntity body){
if(!body.getName().isEmpty()&&!body.getType().isEmpty()) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if(authentication!=null && authentication.isAuthenticated()){
        Object user = authentication.getPrincipal();
        if(user instanceof UserDetails){
            body.setUser(((UserPrinsipals) user).getUser());
        }
    databaseRepo.save(body);
    body.getTables().forEach(i -> {
        i.setDbid(body);
        i.getColumns().forEach(k ->

                   k.setTablel(i)

    );
    });}
}else{
   return ResponseEntity.ok("please fill all fields");
}
    Tablerepo.saveAll(body.getTables());
return ResponseEntity.ok(body);

    }
    public String update( DatabadeDto body){
        Optional<DatabaseEntity> l = databaseRepo.findById(body.getId());
        if(l.isPresent()){
            if(!body.getName().isBlank()||!body.getName().isEmpty()){
            l.get().setName(body.getName());}
            if (!body.getType().isBlank()||!body.getType().isEmpty())
            { l.get().setType(body.getType());}
            if(body.getTables() != null){
                l.get().getTables().clear();
            body.getTables().forEach(i->{
                TableEntity a = TableEntity.toDto(i);
                a.setDbid(l.get());

                l.get().getTables().add(a);

                a.getColumns().forEach(k ->

                        k.setTablel(a)

                );
            });}else{
                l.get().setTables(l.get().getTables());
            }
l.get().setUser(l.get().getUser());
       databaseRepo.save(l.get());
            return "Succses";
        }
        return "Update Failed";
    }
    public void delete(Integer id){
        databaseRepo.deleteById(id);
    }
    public DatabadeDto findById(Integer id){
        List<DatabadeDto> list = new ArrayList<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object user = authentication.getPrincipal();
            if (user instanceof UserDetails) {

                databaseRepo.findByuser(((UserPrinsipals) user).getUser()).forEach(i -> {

                    list.add(DatabadeDto.toEntity(i));
                });
            }

        }

     Optional<DatabadeDto> l =  list.stream().filter(i->i.getId().equals(id)).findFirst();
        return l.orElse(new DatabadeDto());
    }
}
